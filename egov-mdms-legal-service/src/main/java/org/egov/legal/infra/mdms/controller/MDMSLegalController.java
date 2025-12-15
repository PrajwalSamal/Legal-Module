package org.egov.legal.infra.mdms.controller;

public class MDMSLegalController {

	


	    @Autowired
	    private MDMSService mdmsService;

	    @PostMapping("_search")
	    @ResponseBody
	    public ResponseEntity<?> search(@RequestBody @Valid MdmsCriteriaReq mdmsCriteriaReq) {

	        Map<String, Map<String, JSONArray>> response = mdmsService.searchMaster(mdmsCriteriaReq);
	        MdmsResponse mdmsResponse = new MdmsResponse();
	        mdmsResponse.setMdmsRes(response);

	        return new ResponseEntity<>(mdmsResponse, HttpStatus.OK);
	    }

	    @PostMapping("_get")
	    @ResponseBody
	    public ResponseEntity<?> getMaster(@RequestParam("moduleName") String module,
	                                       @RequestParam("masterName") String master,
	                                       @RequestParam(value = "filter", required = false) String filter,
	                                       // @RequestParam("tenantId") String tenantId, // Not required anymore
	                                       @RequestBody RequestInfo requestInfo) {

	        log.info("MDMSController mdmsCriteriaReq [" + module + ", " + master + ", " + filter + "]");

	        MdmsCriteriaReq mdmsCriteriaReq = new MdmsCriteriaReq();
	        mdmsCriteriaReq.setRequestInfo(requestInfo);
	        MdmsCriteria criteria = new MdmsCriteria();
	        // criteria.setTenantId(tenantId); // No tenant logic now

	        ModuleDetail detail = new ModuleDetail();
	        detail.setModuleName(module);

	        MasterDetail masterDetail = new MasterDetail();
	        masterDetail.setName(master);
	        masterDetail.setFilter(filter);
	        ArrayList<MasterDetail> masterList = new ArrayList<>();
	        masterList.add(masterDetail);
	        detail.setMasterDetails(masterList);

	        ArrayList<ModuleDetail> moduleList = new ArrayList<>();
	        moduleList.add(detail);

	        criteria.setModuleDetails(moduleList);
	        mdmsCriteriaReq.setMdmsCriteria(criteria);

	        Map<String, Map<String, JSONArray>> response = mdmsService.searchMaster(mdmsCriteriaReq);
	        MdmsResponse mdmsResponse = new MdmsResponse();
	        mdmsResponse.setMdmsRes(response);
	        return new ResponseEntity<>(mdmsResponse, HttpStatus.OK);
	    }
	

}
