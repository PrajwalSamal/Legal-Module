package org.egov.legal.controller;

import java.util.Map;
import java.util.function.Function;

import org.egov.legal.dto.request.CaseFilingRequest;
import org.egov.legal.dto.response.ApiResponse;
import org.egov.legal.service.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cases")
public class CaseController {

	private final CaseService caseService;

	public CaseController(CaseService caseService) {
		this.caseService = caseService;
	}

	/**
	 * STEP 1: Case Filing (Diary creation)
	 */
	@PostMapping("/fileCase")
	public ResponseEntity<ApiResponse<Map<String, String>>> fileCase(@RequestBody CaseFilingRequest request) {
	    Function<CaseFilingRequest, ResponseEntity<ApiResponse<Map<String, String>>>> fileCaseFunction = req -> {
	        String diaryNumber = null;
	        try {
	            diaryNumber = caseService.fileCase(req);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        
	        return ResponseEntity.accepted()
	            .body(ApiResponse.success("Case filed successfully", 
	                Map.of("diaryNumber", diaryNumber != null ? diaryNumber : "")));
	    };
	    
	    return fileCaseFunction.apply(request);
	}


}
