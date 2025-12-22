package org.egov.pt.util;

import java.util.Arrays;
import java.util.List;

import org.egov.pt.config.CaseConfiguration;
import org.egov.pt.models.Case;
import org.egov.pt.models.Property;
import org.egov.pt.models.enums.CaseAction;
import org.egov.pt.models.enums.CreationReason;
import org.egov.pt.models.workflow.ProcessInstance;
import org.egov.pt.models.workflow.ProcessInstanceRequest;
import org.egov.pt.web.contracts.CaseRequest;
import org.egov.pt.web.contracts.PropertyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CaseUtil extends CommonUtils {
	
	@Autowired
	private CaseConfiguration configs;
	
	
	
public ProcessInstanceRequest initiateCaseWorkFlow(CaseRequest request) {
		
	   Case legalCase = request.getCases();

	    ProcessInstance wf = legalCase.getWorkflow() != null
	            ? legalCase.getWorkflow()
	            : new ProcessInstance();;
	
	            wf.setBusinessId(legalCase.getCaseId()); 
	            wf.setTenantId(legalCase.getTenantId());
	            wf.setBusinessService(configs.getCreateLMWfName());
	            wf.setModuleName(configs.getLegalModuleName());
	            wf.setAction(CaseAction.INITIATE.getValue());

	            legalCase.setWorkflow(wf);

	            return ProcessInstanceRequest.builder()
	                    .processInstances(Arrays.asList(wf))
	                    .requestInfo(request.getRequestInfo())
	                    .build();
	        }

}
