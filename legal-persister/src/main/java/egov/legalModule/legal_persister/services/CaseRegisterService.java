package egov.legalModule.legal_persister.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egov.legalModule.legal_persister.enities.Case;
import egov.legalModule.legal_persister.repos.JpaRegisterCase;

@Service
public class CaseRegisterService {
	
	@Autowired
   private JpaRegisterCase jpaRegisterCase;
	
	public  void registerCase(Case regCase) {
		
		Case registerdCase=jpaRegisterCase.save(regCase);
		
	}
	
	
}
