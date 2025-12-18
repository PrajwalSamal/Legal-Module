package egov.legalModule.legal_persister.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AdvocateEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long advocateId;
    public String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id", nullable = false)
    private CaseRegisterEntity caseEntity;
    
	public Long getAdvocateId() {
		return advocateId;
	}
	public void setAdvocateId(Long advocateId) {
		this.advocateId = advocateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
    
}