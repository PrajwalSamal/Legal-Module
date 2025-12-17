package egov.legalModule.legal_persister.controllers;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egov.legalModule.legal_persister.dtos.RegisterCase;
import egov.legalModule.legal_persister.enities.Case;
import egov.legalModule.legal_persister.services.CaseRegisterService;

@RestController
@RequestMapping("legal-module/api/v1")
public class CaseRegister {
	
	@Autowired
	private CaseRegisterService service;
	
	@PostMapping("/register")
	public String registerCase(@RequestBody RegisterCase resgisCase) {
	
		
		RegisterCase case2=new RegisterCase();
//		case2=resgisCase;
		
//		service.registerCase(case2);
		return "Case Resitered Sucessfully";
	}
	

}
