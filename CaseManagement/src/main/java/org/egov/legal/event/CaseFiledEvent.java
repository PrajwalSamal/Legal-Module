package org.egov.legal.event;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.egov.legal.enums.ScrutinyStatus;

public record CaseFiledEvent(
        UUID eventId,

        String tenantId,

        String diaryNumber,          // generated or passed
        String caseType,
        String caseCategory,
        String courtDetails,

        String title,
        String description,
        String department,

        List<String> advocateIds,
        String ulbOfficerId,

        List<DocumentPayload> documents,

        ScrutinyStatus scrutinyStatus,       // PENDING
        boolean registered,          // false

        OffsetDateTime occurredAt,
        int version
) {}
