package egov.legalModule.legal_persister.services;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Service;


import egov.legalModule.legal_persister.entities.CaseRegisterEntity;
import egov.legalModule.legal_persister.repos.JpaRegisterCase;

@Service
public class CaseRegisterService {
	
	@Autowired
   private JpaRegisterCase jpaRegisterCase;
	
	public  void registerCase(CaseRegisterEntity regCase) {
		
		
		CaseRegisterEntity registerdCase=jpaRegisterCase.save(regCase);
		
	}
	
	
}
