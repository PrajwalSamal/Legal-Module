package org.egov.legal.infra.mdms.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONArray;

@Component
public class MDMSServiceImpl {

	@Autowired
	public ResourceLoader resourceLoader;

	@Value("${egov.mdms.conf.path}")
	public String mdmsFileDirectory;

	@Value("${masters.config.url}")
	public String masterConfigUrl;

	@Value("${egov.mdms.stopOnAnyConfigError:true}")
	public boolean stopOnAnyConfigError;

	private static Map<String, Map<String, Map<String, JSONArray>>> tenantMap = new HashMap<>();

	private static Map<String, Map<String, Object>> masterConfigMap = new HashMap<>();

	public static Map<String, Map<String, Map<String, JSONArray>>> getTenantMap() {
		return tenantMap;
	}
	public static Map<String, Map<String, Object>> getMasterConfigMap() {
		return masterConfigMap;
	}
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	@PostConstruct
	public void run() {
//		 importing try and catch 
		try {
			System.out.println("Reading Files from: " + mdmsFileDirectory);
			LinkedList<String> errorFilesList = new LinkedList<>();
			
//			legal-config 
			readMdmsConfigFiles(masterConfigUrl);
			
//			load all MDMS master files 
			readFiles(mdmsFileDirectory, errorFilesList);
			
			System.out.println("List OF files which has Error while parsing " +errorFilesList);
			
//			stopping application if any config fails
			if(!errorFilesList.isEmpty() && stopOnAnyConfigError) {
				System.out.println("Stopping as all files couldn't be loaded!");
				System.exit(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Getting, Exceptions while loading yaml files!");
			e.printStackTrace();
		}
	}


}
