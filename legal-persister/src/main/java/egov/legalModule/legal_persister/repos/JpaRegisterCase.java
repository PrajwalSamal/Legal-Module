package egov.legalModule.legal_persister.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import egov.legalModule.legal_persister.enities.Case;

public interface JpaRegisterCase extends JpaRepository<Case, Long> {

}
