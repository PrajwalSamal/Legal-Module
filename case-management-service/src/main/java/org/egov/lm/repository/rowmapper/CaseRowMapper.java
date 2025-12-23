package org.egov.lm.repository.rowmapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.egov.lm.models.*;
import org.egov.lm.models.enums.Status;
import org.egov.tracer.model.CustomException;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CaseRowMapper implements ResultSetExtractor<List<Case>> {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<Case> extractData(ResultSet rs) throws SQLException, DataAccessException {

        Map<String, Case> caseMap = new LinkedHashMap<>();

        while (rs.next()) {

            String caseId = rs.getString("caseid");
            Case currentCase = caseMap.get(caseId);

            if (currentCase == null) {

                AuditDetails auditDetails = getAuditDetails(rs);

                Judgement judgement = null;
                String judgementId = rs.getString("judgementid");
                if (judgementId != null) {
                    judgement = Judgement.builder()
                            .judgementId(judgementId)
                            .remark(rs.getString("remark"))
                            .orderDetail(rs.getString("orderdetail"))
                            .build();
                }

                currentCase = Case.builder()
                        .caseId(caseId)
                        .tenantId(rs.getString("tenantid"))
                        .caseType(rs.getString("casetype"))
                        .caseCategory(rs.getString("casecategory"))
                        .title(rs.getString("title"))
                        .description(rs.getString("description"))
                        .department(rs.getString("department"))
                        .courtType(rs.getString("courttype"))
                        .courtName(rs.getString("courtname"))
                        .nextHearingDate(rs.getLong("nexthearingdate"))
                        .status(Status.fromValue(rs.getString("casestatus")))
                        .additionalDetails(getAdditionalDetails(rs, "additionaldetails"))
                        .auditDetails(auditDetails)
                        .judgement(judgement)
                        .build();

                caseMap.put(caseId, currentCase);
            }

            addAdvocate(rs, currentCase);
            addPetitioner(rs, currentCase);
            addRespondent(rs, currentCase);
            addDocument(rs, currentCase);
        }

        return new ArrayList<>(caseMap.values());
    }

    /* -------------------- Children -------------------- */

    private void addAdvocate(ResultSet rs, Case currentCase) throws SQLException {

        String advocateId = rs.getString("advocateid");
        if (advocateId == null) return;

        List<Advocate> advocates = currentCase.getAdvocates();
        if (!CollectionUtils.isEmpty(advocates)) {
            for (Advocate adv : advocates) {
                if (advocateId.equals(adv.getAdvocateId())) return;
            }
        }

        Advocate advocate = Advocate.builder()
                .advocateId(advocateId)
                .name(rs.getString("name"))
                .role(rs.getString("role"))
                .build();

        currentCase.addAdvocatesItem(advocate);
    }

    private void addPetitioner(ResultSet rs, Case currentCase) throws SQLException {

        String petitionerId = rs.getString("petitionerid");
        if (petitionerId == null) return;

        List<Petitioner> petitioners = currentCase.getPetitioners();
        if (!CollectionUtils.isEmpty(petitioners)) {
            for (Petitioner p : petitioners) {
                if (petitionerId.equals(p.getPetitionerId())) return;
            }
        }

        Petitioner petitioner = Petitioner.builder()
                .petitionerId(petitionerId)
                .name(rs.getString("name"))
                .mobileNumber(rs.getString("mobilenumber"))
                .build();

        currentCase.addPetitionersItem(petitioner);
    }

    private void addRespondent(ResultSet rs, Case currentCase) throws SQLException {

        String respondentId = rs.getString("respondentid");
        if (respondentId == null) return;

        List<Respondent> respondents = currentCase.getRespondents();
        if (!CollectionUtils.isEmpty(respondents)) {
            for (Respondent r : respondents) {
                if (respondentId.equals(r.getRespondentId())) return;
            }
        }

        Respondent respondent = Respondent.builder()
                .respondentId(respondentId)
                .name(rs.getString("name"))
                .mobileNumber(rs.getString("mobilenumber"))
                .build();

        currentCase.addRespondentsItem(respondent);
    }

    private void addDocument(ResultSet rs, Case currentCase) throws SQLException {

        String docId = rs.getString("id");
        if (docId == null) return;

        List<Document> documents = currentCase.getDocuments();
        if (!CollectionUtils.isEmpty(documents)) {
            for (Document doc : documents) {
                if (docId.equals(doc.getId())) return;
            }
        }

        Document document = Document.builder()
                .id(docId)
                .documentType(rs.getString("documenttype"))
                .fileStoreId(rs.getString("filestoreid"))
                .documentUid(rs.getString("documentuid"))
                .build();

        currentCase.addDocumentsItem(document);
    }

    /* -------------------- Audit -------------------- */

    private AuditDetails getAuditDetails(ResultSet rs) throws SQLException {

        Long lastModifiedTime = rs.getLong("lastmodifiedtime");
        if (rs.wasNull()) lastModifiedTime = null;

        return AuditDetails.builder()
                .createdBy(rs.getString("createdby"))
                .createdTime(rs.getLong("createdtime"))
                .lastModifiedBy(rs.getString("lastmodifiedby"))
                .lastModifiedTime(lastModifiedTime)
                .build();
    }

    /* -------------------- JSONB -------------------- */

    private JsonNode getAdditionalDetails(ResultSet rs, String key) {

        try {
            PGobject obj = (PGobject) rs.getObject(key);
            return obj == null ? null : mapper.readTree(obj.getValue());
        } catch (IOException | SQLException e) {
            throw new CustomException("CASE_ADDITIONAL_DETAILS_PARSE_ERROR",
                    "Failed to parse case additionalDetails");
        }
    }
}

