package org.egov.legal.infra.mdms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egov.legal.infra.mdms.service.impl.MDMSServiceImpl;
import org.egov.legal.infra.mdms.utils.MDMSConstants;
import org.egov.mdms.model.MasterDetail;
import org.egov.mdms.model.MdmsCriteriaReq;
import org.egov.mdms.model.ModuleDetail;
import org.egov.tracer.model.CustomException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;

@Service
@Slf4j
public class MDMSService {

	public Map<String, Map<String, JSONArray>> searchMaster(MdmsCriteriaReq mdmsCriteriaReq) {
		Map<String, Map<String, Map<String, JSONArray>>> tenantIdMap = MDMSServiceImpl.getTenantMap();

		String tenantId = mdmsCriteriaReq.getMdmsCriteria().getTenantId();

		Map<String, Map<String, JSONArray>> stateLevel = null;
		Map<String, Map<String, JSONArray>> ulbLevel = null;


		if (tenantId.contains(".")) {
			String array[] = tenantId.split("\\.");
			stateLevel = tenantIdMap.get(array[0]);
			ulbLevel = tenantIdMap.get(tenantId);
			if (ulbLevel == null)
				throw new CustomException("Invalid_tenantId.MdmsCriteria.tenantId", "Invalid Tenant Id");
		} else {
			stateLevel = tenantIdMap.get(tenantId);
			if (stateLevel == null)
				throw new CustomException("Invalid_tenantId.MdmsCriteria.tenantId", "Invalid Tenant Id");
		}

		Map<String, JSONArray> finalMasterMap = new HashMap<>();
		List<ModuleDetail> moduleDetails = mdmsCriteriaReq.getMdmsCriteria().getModuleDetails();

		Map<String, Map<String, JSONArray>> responseMap = new HashMap<>();

		for(ModuleDetail moduleDetail : moduleDetails) {

			if(stateLevel.get(moduleDetail.getModuleName()) == null)
				continue;

			for(MasterDetail masterDetail : moduleDetail.getMasterDetails()) {
				JSONArray masterData;

				try {
					masterData = getMasterData(
							stateLevel,
							ulbLevel,
							moduleDetail.getModuleName(),
							masterDetail.getName(),
							tenantId);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Exception occured while reading the msater data");
					continue;
				}

				if(masterData == null)
					continue;


				if(masterDetail.getFilter() != null)
					masterData = filterMaster(masterData, masterDetail.getFilter());

				finalMasterMap.put(masterDetail.getName(), masterData);
			}
			responseMap.put(moduleDetail.getModuleName(), finalMasterMap);
		}
		return responseMap;


	}

	//	Determines STATE vs ULB using legal-config.json

	private JSONArray getMasterData(
			Map<String, Map<String, JSONArray>> stateLevel,
			Map<String, Map<String, JSONArray>> ulbLevel, String moduleName, String masterName, String tenantId) throws Exception {


		Map<String, Map<String, Object>> masterConfigMap = MDMSServiceImpl.getMasterConfigMap();

		boolean isStateLevel = true; //court master default

		Object masterData = masterConfigMap.get(moduleName).get(masterName);

		try {
			isStateLevel = JsonPath.read(
					new ObjectMapper().writeValueAsString(masterData),
					MDMSConstants.STATE_LEVEL_JSONPATH
					);
		} catch (Exception ignored) {}

		if (ulbLevel == null || isStateLevel) {
			return stateLevel.get(moduleName).get(masterName);
		} else {
			return ulbLevel.get(moduleName).get(masterName);
		}
	}



	public JSONArray filterMaster(JSONArray masters, String filterExp) {
		JSONArray filteredMasters = JsonPath.read(masters, filterExp);
		return filteredMasters;
	}

}
