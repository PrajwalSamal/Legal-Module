package org.egov.lm.service;


import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.egov.common.contract.request.RequestInfo;
import org.egov.lm.config.CaseConfiguration;
import org.egov.lm.models.Advocate;
import org.egov.lm.models.AuditDetails;
import org.egov.lm.models.Case;
import org.egov.lm.models.CaseCriteria;
import org.egov.lm.models.enums.Status;
import org.egov.lm.models.workflow.State;
import org.egov.lm.producer.Producer;
import org.egov.lm.repository.CaseRepository;
import org.egov.lm.web.contracts.CaseRequest;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	private final SecureRandom random = new SecureRandom();

	public Case fileCase(@Valid CaseRequest caseRequest) {

		// validate
		
		//enrich
		
		//
		
		AuditDetails auditDetails=new AuditDetails();
		auditDetails.setCreatedBy("Ram");
		auditDetails.setCreatedTime(System.currentTimeMillis());
		auditDetails.setLastModifiedBy("ram");
		auditDetails.setLastModifiedTime(System.currentTimeMillis());
		
		caseRequest.getCases().setAuditDetails(auditDetails);
	
		if (caseConfiguration.getIsWorkflowEnabled()) {
			wfService.updateCaseWorkflow(caseRequest);
		}
		producer.push(caseConfiguration.getSaveCaseTopic(), caseRequest);
		//caseRequest.getCases().setWorkflow(null);
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

//		String userTenant = criteria.getTenantId();
//		if(criteria.getTenantId() == null)
//			userTenant = requestInfo.getUserInfo().getTenantId();

		return caseRepository.getAllRegisterdCases(criteria);

	}

	public Case updateCase(@Valid CaseRequest caseRequest) {

		State state = null;

		if (caseConfiguration.getIsWorkflowEnabled()) {
			state = wfService.updateCaseWorkflow(caseRequest);
		}

		if (state.getApplicationStatus().equalsIgnoreCase(Status.REGISTERED.toString())) {
			Advocate advocate = getAdvocateToAllocate(caseRequest);
			caseRequest.getCases().setAdvocates(Arrays.asList(advocate));
		}

		producer.push(caseConfiguration.getSaveCaseTopic(), caseRequest);
		caseRequest.getCases().setWorkflow(null);
		return caseRequest.getCases();
	}

	@Transactional
	private Advocate getAdvocateToAllocate(@Valid CaseRequest caseRequest) {

		List<Advocate> eligible = caseRepository.getAdvocates(caseRequest);

		int minLoad = eligible.stream().mapToInt(Advocate::getActiveCaseCount).min().orElse(0);

		List<Advocate> leastLoaded = eligible.stream().filter(a -> a.getActiveCaseCount() == minLoad)
				.collect(Collectors.toList());

		return leastLoaded.get(random.nextInt(leastLoaded.size()));

	}

}
