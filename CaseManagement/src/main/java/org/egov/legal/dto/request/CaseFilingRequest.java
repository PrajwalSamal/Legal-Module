package org.egov.legal.dto.request;

import java.time.LocalDate;
import java.util.List;


public record CaseFilingRequest(
        String tenantId,
        String caseType,
        String caseCategory,
        String courtDetails,
        CaseDetails caseDetails,
        List<AdvocateRef> advocate,
        UlbOfficerRef ulbOfficer,
        List<DocumentRequest> documents
) {}