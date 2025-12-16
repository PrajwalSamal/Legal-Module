package org.egov.legal.service.impl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.egov.legal.dto.request.CaseFilingRequest;
import org.egov.legal.enums.ScrutinyStatus;
import org.egov.legal.event.CaseFiledEvent;
import org.egov.legal.event.DocumentPayload;
import org.egov.legal.kafka.CaseEventProducer;
import org.egov.legal.service.CaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaseServiceImpl implements CaseService {

	private static final Logger log = LoggerFactory.getLogger(CaseService.class);

	private final CaseEventProducer producer;

	

	public CaseServiceImpl(CaseEventProducer producer) {
		this.producer = producer;
	}

	public String fileCase(CaseFilingRequest req) {
		 String diaryNumber = null;
		try {
			/*
			 * Add NUll check for Ids
			 *
			 *
			 *
			 *
			 */
			List<String> advocateIds = req.advocate().stream().map(a -> a.advocateId()).toList();
			 diaryNumber = UUID.randomUUID().toString();
			CaseFiledEvent event = new CaseFiledEvent(UUID.randomUUID(), req.tenantId(),
					diaryNumber, // diary number --should have genrator service
					req.caseType(), req.caseCategory(), req.courtDetails(), req.caseDetails().title(),
					req.caseDetails().description(), req.caseDetails().department(), advocateIds,
					req.ulbOfficer().officerId(),
					req.documents().stream().map(
							d -> new DocumentPayload(d.fileName(), d.documentUid(), d.fileStoreId(), d.documentType()))
							.toList(),
					ScrutinyStatus.PENDING, false, OffsetDateTime.now(), 1);

			producer.publishCaseFiled(event);

			log.info("Case filed successfully. Tenant={}, Diary={}", req.tenantId(), event.diaryNumber());
//			 return diaryNumber;
		} catch (Exception ex) {
			log.error("System error while filing case", ex);
			ex.printStackTrace();
		}
		return diaryNumber;
		
	}
}
