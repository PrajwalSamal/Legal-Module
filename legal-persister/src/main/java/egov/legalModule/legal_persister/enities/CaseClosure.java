package egov.legalModule.legal_persister.enities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "\"case_closure\"")
public class CaseClosure {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("closure_id")
    private Long closureId;

    @JsonProperty("closure_date")
    private String closureDate;

    @JsonProperty("closure_reason")
    private String closureReason;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "case_id",nullable = false)
    @JsonProperty("case")
    private Case caseEntity;
    
	public CaseClosure() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CaseClosure(Long closureId, String closureDate, String closureReason, Case caseEntity) {
		super();
		this.closureId = closureId;
		this.closureDate = closureDate;
		this.closureReason = closureReason;
		this.caseEntity = caseEntity;
	}

	public Long getClosureId() {
		return closureId;
	}

	public void setClosureId(Long closureId) {
		this.closureId = closureId;
	}

	public String getClosureDate() {
		return closureDate;
	}

	public void setClosureDate(String closureDate) {
		this.closureDate = closureDate;
	}

	public String getClosureReason() {
		return closureReason;
	}

	public void setClosureReason(String closureReason) {
		this.closureReason = closureReason;
	}

	public Case getCaseEntity() {
		return caseEntity;
	}

	public void setCaseEntity(Case caseEntity) {
		this.caseEntity = caseEntity;
	}



    
}


