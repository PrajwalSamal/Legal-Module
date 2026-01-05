package org.egov.lm.web.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.egov.common.contract.response.ResponseInfo;
import org.egov.lm.models.Case;
import org.egov.lm.models.CaseCriteria;
import org.egov.lm.models.Property;
import org.egov.lm.models.PropertyCriteria;
import org.egov.lm.models.oldProperty.OldPropertyCriteria;
import org.egov.lm.service.CaseService;
import org.egov.lm.service.MigrationService;
import org.egov.lm.service.PropertyService;
import org.egov.lm.util.ResponseInfoFactory;
import org.egov.lm.validator.PropertyValidator;
import org.egov.lm.web.contracts.CaseRequest;
import org.egov.lm.web.contracts.CaseResponse;
import org.egov.lm.web.contracts.PropertyRequest;
import org.egov.lm.web.contracts.PropertyResponse;
import org.egov.lm.web.contracts.RequestInfoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/case")
public class CaseController {

	@Autowired
	private CaseService caseService;
	
	@Autowired
	private PropertyService propertyService;

	@Autowired
	private ResponseInfoFactory responseInfoFactory;

	@Autowired
	private MigrationService migrationService;
	
	@Autowired
    private PropertyValidator propertyValidator;

	@PostMapping("/_create")
	public ResponseEntity<CaseResponse> create(@Valid @RequestBody CaseRequest caseRequest) {

		Case cases = caseService.fileCase(caseRequest);
		ResponseInfo resInfo = responseInfoFactory.createResponseInfoFromRequestInfo(caseRequest.getRequestInfo(), true);
		CaseResponse response = CaseResponse.builder()
				.cases(Arrays.asList(cases))
				.responseInfo(resInfo)
				.build();
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	
	@PostMapping("/_update")
	public ResponseEntity<CaseResponse> update(@Valid @RequestBody CaseRequest caseRequest) {
		
		Case cases = caseService.updateCase(caseRequest);
		ResponseInfo resInfo = responseInfoFactory.createResponseInfoFromRequestInfo(caseRequest.getRequestInfo(), true);
		CaseResponse response = CaseResponse.builder()
				.cases(Arrays.asList(cases))
				.responseInfo(resInfo)
				.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/_search")
	public ResponseEntity<CaseResponse> search(@Valid @RequestBody RequestInfoWrapper requestInfoWrapper,
			@Valid @ModelAttribute CaseCriteria caseCriteria) {
		
//		propertyValidator.validatePropertyCriteria(caseCriteria, requestInfoWrapper.getRequestInfo());
		List<Case> cases = caseService.searchCases(caseCriteria,requestInfoWrapper.getRequestInfo());
		CaseResponse response = CaseResponse.builder().cases(cases).responseInfo(
				responseInfoFactory.createResponseInfoFromRequestInfo(requestInfoWrapper.getRequestInfo(), true))
				.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

//	@PostMapping("/_migration")
//	public ResponseEntity<?> propertyMigration(@Valid @RequestBody RequestInfoWrapper requestInfoWrapper,
//											   @Valid @ModelAttribute OldPropertyCriteria propertyCriteria) {
//		long startTime = System.nanoTime();
//		Map<String, String> resultMap = null;
//		Map<String, String> errorMap = new HashMap<>();
//
//		resultMap = migrationService.initiateProcess(requestInfoWrapper,propertyCriteria,errorMap);
//
//		long endtime = System.nanoTime();
//		long elapsetime = endtime - startTime;
//		System.out.println("Elapsed time--->"+elapsetime);
//
//		return new ResponseEntity<>(resultMap, HttpStatus.OK);
//	}

	@RequestMapping(value = "/_plainsearch", method = RequestMethod.POST)
	public ResponseEntity<PropertyResponse> plainsearch(@Valid @RequestBody RequestInfoWrapper requestInfoWrapper,
														@Valid @ModelAttribute PropertyCriteria propertyCriteria) {
		List<Property> properties = propertyService.searchPropertyPlainSearch(propertyCriteria, requestInfoWrapper.getRequestInfo());
		PropertyResponse response = PropertyResponse.builder().properties(properties).responseInfo(
				responseInfoFactory.createResponseInfoFromRequestInfo(requestInfoWrapper.getRequestInfo(), true))
				.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
//	@RequestMapping(value = "/_cancel", method = RequestMethod.POST)
//	public ResponseEntity<PropertyResponse> cancel(@Valid @RequestBody RequestInfoWrapper requestInfoWrapper,
//												   @Valid @ModelAttribute PropertyCancelCriteria propertyCancelCriteria) {
//		
//		List<Property> properties = propertyService.cancelProperty(propertyCancelCriteria,requestInfoWrapper.getRequestInfo());
//		PropertyResponse response = PropertyResponse.builder().properties(properties).responseInfo(
//				responseInfoFactory.createResponseInfoFromRequestInfo(requestInfoWrapper.getRequestInfo(), true))
//				.build();
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}

}
