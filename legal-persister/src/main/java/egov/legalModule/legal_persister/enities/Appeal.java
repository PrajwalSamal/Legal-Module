package egov.legalModule.legal_persister.enities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;





@Entity
public class Appeal {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("appeal_id")
    private Long appealId;

    @JsonProperty("appeal_date")
    private String appealDate;

    @JsonProperty("appeal_status")
    private String appealStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "case_id")
    @JsonProperty("case")
    private Case caseEntity;


	public Long getAppealId() {
		return appealId;
	}

	public void setAppealId(Long appealId) {
		this.appealId = appealId;
	}

	public String getAppealDate() {
		return appealDate;
	}

	public void setAppealDate(String appealDate) {
		this.appealDate = appealDate;
	}

	public String getAppealStatus() {
		return appealStatus;
	}

	public void setAppealStatus(String appealStatus) {
		this.appealStatus = appealStatus;
	}

	public Case getCaseEntity() {
		return caseEntity;
	}

	public void setCaseEntity(Case caseEntity) {
		this.caseEntity = caseEntity;
	}

	

    
}



