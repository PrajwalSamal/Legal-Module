package org.egov.legal.service;

import org.egov.legal.dto.request.CaseFilingRequest;

public interface CaseService {
	
	String fileCase(CaseFilingRequest request);

}

