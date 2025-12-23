package org.egov.lm.repository.builder;

import java.util.List;
import java.util.Set;

import org.egov.lm.config.CaseConfiguration;
import org.egov.lm.models.CaseCriteria;
import org.egov.tracer.model.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

@Component
public class CaseQueryBuilder {
	
	private final CaseConfiguration config;
	
	public CaseQueryBuilder(CaseConfiguration config) {
		this.config = config ;
	}

	private static final String SELECT = "SELECT ";
	private static final String INNER_JOIN = "INNER JOIN";
	private static final String LEFT_JOIN = "LEFT OUTER JOIN";
	private static final String AND_QUERY = " AND ";
	
	private static String REPLACE_STRING = "{replace}";

	private static final String CASE_ID_QUERY = SELECT + " caseid FROM eg_lm_case_advocate WHERE advocateid IN {replace}";

	private static final String WITH_CLAUSE_QUERY = " WITH caseresult AS ({replace}) " + " SELECT * FROM caseresult "
			+ " INNER JOIN ( " + "   SELECT caseid, MIN(statusorder) AS minorder "
			+ "   FROM caseresult GROUP BY caseid " + " ) AS minresult "
			+ " ON minresult.caseid = caseresult.caseid"  + AND_QUERY + " minresult.minorder = caseresult.statusorder ";

	private static final String caseSelectValues =  "c.caseid, " + "c.tenantid, "
			+ "c.casetype, " + "c.casecategory, " + "c.title, " + "c.description, " + "c.department, "
			+ "c.courttype, " + "c.courtname, " + "c.nexthearingdate, " + "c.status, "
			+ "c.createdby, " + "c.createdtime, "
			+ "c.lastmodifiedby, " + "c.lastmodifiedtime, "
			+ "c.additionaldetails, " + " (CASE " + "WHEN c.status='ACTIVE' THEN 0 "
			+ "   WHEN c.status='INWORKFLOW' THEN 1 " + "   WHEN c.status='CLOSED' THEN 2 "
			+ "   ELSE 3 END) AS statusorder, ";

	private static final String advocateSelectValues = "adv.id AS advocatemapid, " + "adv.caseid AS advcaseid, "
			+ "adv.advocateid, " + "adv.role AS advocaterole, ";

	private static final String petitionerSelectValues = "pet.id AS petitionerid, " + "pet.caseid AS petcaseid, "
			+ "pet.name AS petitionername, " + "pet.contactno AS petitionercontact, ";

	private static final String respondentSelectValues = "res.id AS respondentid, " + "res.caseid AS rescaseid, "
			+ "res.name AS respondentname, " + "res.contactno AS respondentcontact, ";

	private static final String caseDocSelectValues = "doc.id AS cdocid, " + "doc.caseid AS cdoccaseid, "
			+ "doc.documenttype AS cdoctype, " + "doc.filestoreid AS cdocfilestore, "
			+ "doc.documentuid AS cdocuid, ";

	private static final String QUERY = SELECT 
			+ caseSelectValues 
			
			+ advocateSelectValues
			
			+ petitionerSelectValues
			
			+ respondentSelectValues
			
			+ caseDocSelectValues
			
			+ " FROM eg_lm_case c "
			
			+  INNER_JOIN + " eg_lm_case_advocate adv ON c.caseid = adv.caseid "
			
			+ LEFT_JOIN + " eg_lm_case_petitioner pet ON c.caseid = pet.caseid "
			
			+ LEFT_JOIN + " eg_lm_case_respondent res ON c.caseid = res.caseid "
			
			+ LEFT_JOIN + " eg_lm_case_document doc ON c.caseid = doc.caseid "
			
			+ " WHERE ";

	private static final String paginationWrapper = "SELECT * FROM ( "
			+ " SELECT *, DENSE_RANK() OVER (ORDER BY clastmodifiedtime DESC, cid) offset_ " + " FROM ({}) result "
			+ ") result_offset " + "WHERE offset_ > ?" + AND_QUERY + "offset_ <= ?";

	public String getCasesSearchQuery(CaseCriteria criteria, List<Object> preparedStmtList) {

	    Boolean isEmpty =
	            CollectionUtils.isEmpty(criteria.getCaseIds())
	            && CollectionUtils.isEmpty(criteria.getCaseTypes())
	            && CollectionUtils.isEmpty(criteria.getCaseCategories())
	            && CollectionUtils.isEmpty(criteria.getAdvocateIds())
	            && null == criteria.getCourtType()
	            && null == criteria.getCourtName();
	           
//
//	    if (isEmpty) {
//	        throw new CustomException(
//	                "EG_LM_CASE_SEARCH_ERROR",
//	                "No criteria given for case search"
//	        );
//	    }

	    StringBuilder builder = new StringBuilder(QUERY);
	    Boolean appendAndQuery = false;

	    /* -------------------- Tenant -------------------- */
	    if (!ObjectUtils.isEmpty(criteria.getTenantId())) {
	        builder.append(" c.tenantid = ?");
	        preparedStmtList.add(criteria.getTenantId());
	        appendAndQuery = true;
	    }

	    /* -------------------- Case IDs -------------------- */
	    Set<String> caseIds = criteria.getCaseIds();
	    if (!CollectionUtils.isEmpty(caseIds)) {
	        if (appendAndQuery) builder.append(AND_QUERY);
	        builder.append(" c.caseid IN (")
	               .append(createQuery(caseIds))
	               .append(")");
	        addToPreparedStatement(preparedStmtList, caseIds);
	        appendAndQuery = true;
	    }

	    /* -------------------- Case Types -------------------- */
	    Set<String> caseTypes = criteria.getCaseTypes();
	    if (!CollectionUtils.isEmpty(caseTypes)) {
	        if (appendAndQuery) builder.append(AND_QUERY);
	        builder.append(" c.casetype IN (")
	               .append(createQuery(caseTypes))
	               .append(")");
	        addToPreparedStatement(preparedStmtList, caseTypes);
	        appendAndQuery = true;
	    }

	    /* -------------------- Case Categories -------------------- */
	    Set<String> caseCategories = criteria.getCaseCategories();
	    if (!CollectionUtils.isEmpty(caseCategories)) {
	        if (appendAndQuery) builder.append(AND_QUERY);
	        builder.append(" c.casecategory IN (")
	               .append(createQuery(caseCategories))
	               .append(")");
	        addToPreparedStatement(preparedStmtList, caseCategories);
	        appendAndQuery = true;
	    }

	    /* -------------------- Court -------------------- */
	    if (criteria.getCourtType() != null) {
	        if (appendAndQuery) builder.append(AND_QUERY);
	        builder.append(" c.courttype = ?");
	        preparedStmtList.add(criteria.getCourtType());
	        appendAndQuery = true;
	    }

	    if (criteria.getCourtName() != null) {
	        if (appendAndQuery) builder.append(AND_QUERY);
	        builder.append(" c.courtname = ?");
	        preparedStmtList.add(criteria.getCourtName());
	        appendAndQuery = true;
	    }

	    /* -------------------- Advocate-based search -------------------- */
	    Set<String> advocateIds = criteria.getAdvocateIds();
	    if (!CollectionUtils.isEmpty(advocateIds)) {
	        if (appendAndQuery) builder.append(AND_QUERY);
	        builder.append(" adv.advocateid IN (")
	               .append(createQuery(advocateIds))
	               .append(")");
	        addToPreparedStatement(preparedStmtList, advocateIds);
	        appendAndQuery = true;
	    }

	    /* -------------------- WITH + Pagination -------------------- */
	    String withClauseQuery =
	            WITH_CLAUSE_QUERY.replace(REPLACE_STRING, builder.toString());

	    return addPaginationWrapper(withClauseQuery, preparedStmtList, criteria);
	}
	
	private void addToPreparedStatement(List<Object> preparedStmtList, Set<String> ids) {
		ids.forEach(id -> {
			preparedStmtList.add(id);
		});
	}
 	
    private String addPaginationWrapper(String query, List<Object> preparedStmtList, CaseCriteria criteria) {
		
		
		Long limit = config.getDefaultLimit();
		Long offset = config.getDefaultOffset();
		String finalQuery = paginationWrapper.replace("{}", query);

		if (criteria.getLimit() != null && criteria.getLimit() <= config.getMaxSearchLimit())
			limit = criteria.getLimit();

		if (criteria.getLimit() != null && criteria.getLimit() > config.getMaxSearchLimit())
			limit = config.getMaxSearchLimit();

		if (criteria.getOffset() != null)
			offset = criteria.getOffset();

		preparedStmtList.add(offset);
		preparedStmtList.add(limit + offset);

		return finalQuery;
	}
    
    private String createQuery(Set<String> ids) {
		StringBuilder builder = new StringBuilder();
		int length = ids.size();
		for (int i = 0; i < length; i++) {
			builder.append(" ?");
			if (i != length - 1)
				builder.append(",");
		}
		return builder.toString();
	}


}
