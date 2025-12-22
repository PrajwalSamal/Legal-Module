package org.egov.pt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.egov.common.contract.request.RequestInfo;
import org.egov.pt.config.CaseConfiguration;
import org.egov.pt.models.Case;
import org.egov.pt.models.CaseCriteria;
import org.egov.pt.models.enums.CreationReason;
import org.egov.pt.producer.Producer;
import org.egov.pt.repository.CaseRepository;
import org.egov.pt.web.contracts.CaseRequest;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CaseService {

	@Autowired
	private CaseConfiguration caseConfiguration;

	@Autowired
	private Producer producer;
	
	@Autowired
	private CaseRepository caseRepository;

	@Autowired
	private WorkflowService wfService;

	public Case fileCase(@Valid CaseRequest caseRequest) {

		if (caseConfiguration.getIsWorkflowEnabled()) {
			wfService.updateCaseWorkflow(caseRequest);
		}

		producer.push(caseConfiguration.getSaveCaseTopic(), caseRequest);
		caseRequest.getCases().setWorkflow(null);
		return caseRequest.getCases();
	}

	public List<Case> searchCases(@Valid CaseCriteria criteria, RequestInfo requestInfo) {
         
		List<Case> cases = new ArrayList<>();
 
		if (criteria.isAudit() && (CollectionUtils.isEmpty(criteria.getCaseIds()))) {

			throw new CustomException("EG_LM_CASE_AUDIT_ERROR", "Case Ids are null");
		}
		
//		Boolean shouldReturnEmptyList = caseRepository.enrichCriteriaFromUser(criteria, requestInfo);
//
//		if (shouldReturnEmptyList)
//			return Collections.emptyList();
		
		Set<String> caseIds = criteria.getCaseIds();
		
		String userTenant = criteria.getTenantId();
		if(criteria.getTenantId() == null)
			userTenant = requestInfo.getUserInfo().getTenantId();
		
		cases = caseRepository.getAllRegisterdCases(userTenant,criteria);
		
		return null;
	}

}
