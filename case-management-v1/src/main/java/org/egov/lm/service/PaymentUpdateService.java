package org.egov.lm.service;

import java.util.HashMap;
import java.util.List;

import org.egov.common.contract.request.RequestInfo;
import org.egov.common.contract.request.Role;
import org.egov.lm.config.PropertyConfiguration;
import org.egov.lm.models.Property;
import org.egov.lm.models.PropertyCriteria;
import org.egov.lm.models.collection.Bill;
import org.egov.lm.models.collection.PaymentDetail;
import org.egov.lm.models.collection.PaymentRequest;
import org.egov.lm.models.enums.Status;
import org.egov.lm.models.workflow.ProcessInstanceRequest;
import org.egov.lm.models.workflow.State;
import org.egov.lm.producer.Producer;
import org.egov.lm.repository.PropertyRepository;
import org.egov.lm.util.PropertyUtil;
import org.egov.lm.web.contracts.PropertyRequest;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;

@Service
public class PaymentUpdateService {

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private PropertyConfiguration config;

	@Autowired
	private WorkflowService wfIntegrator;

	@Autowired
	private Producer producer;

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private PropertyUtil util;
	
	@Autowired
	private NotificationService notifService;

	/**
	 * Process the message from kafka and updates the status to paid
	 * 
	 * @param record The incoming message from receipt create consumer
	 */
	public void process(HashMap<String, Object> record) {

		try {

			PaymentRequest paymentRequest = mapper.convertValue(record, PaymentRequest.class);
			RequestInfo requestInfo = paymentRequest.getRequestInfo();

			List<PaymentDetail> paymentDetails = paymentRequest.getPayment().getPaymentDetails();
			String tenantId = paymentRequest.getPayment().getTenantId();

			for (PaymentDetail paymentDetail : paymentDetails) {
				
				Boolean isModuleMutation = paymentDetail.getBusinessService().equalsIgnoreCase(config.getMutationWfName());
				
				if (isModuleMutation) {

					updateWorkflowForMutationPayment(requestInfo, tenantId, paymentDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * method to do workflow update for Property
	 * 
	 * @param requestInfo
	 * @param tenantId
	 * @param paymentDetail
	 */
	private void updateWorkflowForMutationPayment(RequestInfo requestInfo, String tenantId, PaymentDetail paymentDetail) {
		
		Bill bill  = paymentDetail.getBill();
		
		PropertyCriteria criteria = PropertyCriteria.builder()
				.acknowledgementIds(Sets.newHashSet(bill.getConsumerCode()))
				.tenantId(tenantId)
				.build();
				
		List<Property> properties = propertyRepository.getPropertiesWithOwnerInfo(criteria, requestInfo, true);

		if (CollectionUtils.isEmpty(properties))
			throw new CustomException("INVALID RECEIPT",
					"No Properties found for the comsumerCode " + criteria.getPropertyIds());

		Role role = Role.builder().code("SYSTEM_PAYMENT").build();
		requestInfo.getUserInfo().getRoles().add(role);
		
		properties.forEach( property -> {
			
			PropertyRequest updateRequest = PropertyRequest.builder().requestInfo(requestInfo)
					.property(property).build();
			
			ProcessInstanceRequest wfRequest = util.getProcessInstanceForMutationPayment(updateRequest);
			
			State state = wfIntegrator.callWorkFlow(wfRequest);
			property.setWorkflow(wfRequest.getProcessInstances().get(0));
			property.getWorkflow().setState(state);
			updateRequest.getProperty().setStatus(Status.fromValue(state.getApplicationStatus()));
			producer.push(config.getUpdatePropertyTopic(), updateRequest);			
			notifService.sendNotificationForMtPayment(updateRequest, bill.getTotalAmount());
		});
	}

}
