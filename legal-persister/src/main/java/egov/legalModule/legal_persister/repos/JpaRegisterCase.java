package egov.legalModule.legal_persister.repos;

import org.springframework.data.jpa.repository.JpaRepository;



import egov.legalModule.legal_persister.entities.CaseRegisterEntity;

public interface JpaRegisterCase extends JpaRepository<CaseRegisterEntity, Long> {

}
