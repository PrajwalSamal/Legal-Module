package org.egov.lm.util;

import java.util.Collections;
import java.util.List;

import org.egov.common.contract.request.RequestInfo;
import org.egov.lm.models.Assessment;
import org.egov.lm.models.Property;
import org.egov.lm.models.PropertyCriteria;
import org.egov.lm.service.PropertyService;
import org.egov.lm.web.contracts.AssessmentRequest;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class AssessmentUtils extends CommonUtils {


    private PropertyService propertyService;

    @Autowired
    public AssessmentUtils(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    public Property getPropertyForAssessment(AssessmentRequest assessmentRequest){
    	
        RequestInfo requestInfo = assessmentRequest.getRequestInfo();
        Assessment assessment = assessmentRequest.getAssessment();
        PropertyCriteria criteria = PropertyCriteria.builder()
                .tenantId(assessment.getTenantId())
                .propertyIds(Collections.singleton(assessment.getPropertyId()))
                .build();
        List<Property> properties = propertyService.searchProperty(criteria, requestInfo);

        if(CollectionUtils.isEmpty(properties))
            throw new CustomException("PROPERTY_NOT_FOUND","The property with id: "+assessment.getPropertyId()+" is not found");

        return properties.get(0);
    }


}
