package org.egov.pt.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.egov.common.contract.request.RequestInfo;
import org.egov.pt.models.Case;
import org.egov.pt.models.CaseCriteria;
import org.egov.pt.models.PropertyCriteria;
import org.egov.pt.models.user.User;
import org.egov.pt.models.user.UserDetailResponse;
import org.egov.pt.models.user.UserSearchRequest;
import org.egov.pt.repository.builder.PropertyQueryBuilder;
import org.egov.pt.repository.rowmapper.OpenPropertyRowMapper;
import org.egov.pt.repository.rowmapper.PropertyRowMapper;
import org.egov.pt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class CaseRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PropertyQueryBuilder queryBuilder;

	@Autowired
	private PropertyRowMapper rowMapper;

	@Autowired
	private OpenPropertyRowMapper openRowMapper;

	@Autowired
	private UserService userService;

	public Boolean enrichCriteriaFromUser(CaseCriteria criteria, RequestInfo requestInfo) {

		Set<String> ownerIds = new HashSet<String>();

//		if (!CollectionUtils.isEmpty(criteria.getOwnerIds()))
//			ownerIds.addAll(criteria.getOwnerIds());
//		criteria.setOwnerIds(null);

		String userTenant = criteria.getTenantId();
		if (criteria.getTenantId() == null)
			userTenant = requestInfo.getUserInfo().getTenantId();

		UserSearchRequest userSearchRequest = userService.getBaseUserSearchRequest(userTenant, requestInfo);

		UserDetailResponse userDetailResponse = userService.getUser(userSearchRequest);
		if (CollectionUtils.isEmpty(userDetailResponse.getUser()))
			return true;

		// fetching property id from owner table and enriching criteria
		ownerIds.addAll(userDetailResponse.getUser().stream().map(User::getUuid).collect(Collectors.toSet()));
		return false;
	}

	public List<Case> getAllRegisterdCases(String userTenant, @Valid CaseCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
