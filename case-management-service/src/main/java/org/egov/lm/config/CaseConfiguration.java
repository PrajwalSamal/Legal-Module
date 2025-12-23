package org.egov.lm.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class CaseConfiguration {

	
	 //PERSISTER
    @Value("${persister.save.case.topic}")
    private String saveCaseTopic;
    
    
// Workflow
	
    @Value("${pt.business.codes}")
    private List<String> businessServiceList;

    @Value("${workflow.host}")
    private String wfHost;

    @Value("${workflow.transition.path}")
    private String wfTransitionPath;

    @Value("${workflow.businessservice.search.path}")
    private String wfBusinessServiceSearchPath;

    @Value("${workflow.processinstance.search.path}")
    private String wfProcessInstanceSearchPath;

    @Value("${is.workflow.enabled}")
    private Boolean isWorkflowEnabled;
    
    @Value("${lm.search.pagination.default.limit}")
    private Long defaultLimit;

    @Value("${lm.search.pagination.default.offset}")
    private Long defaultOffset;
    
    @Value("${lm.search.pagination.max.search.limit}")
    private Long maxSearchLimit;
 
    
    @Value("${legal.module.name}")
  	private String legalModuleName;  
    
    @Value("${legal.create.workflow.name}")
    private String createLMWfName;

}
