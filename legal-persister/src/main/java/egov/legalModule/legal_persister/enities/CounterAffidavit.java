package egov.legalModule.legal_persister.enities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;



@Entity
public class CounterAffidavit {
	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    @JsonProperty("counter_affidavit_id")
	    private Long counterAffidavitId;

	    @JsonProperty("submission_date")
	    private String submissionDate;

	    @JsonProperty("details")
	    private String details;

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "case_id")
	    @JsonProperty("case")
	    private Case caseEntity;


	public Long getCounterAffidavitId() {
		return counterAffidavitId;
	}

	public void setCounterAffidavitId(Long counterAffidavitId) {
		this.counterAffidavitId = counterAffidavitId;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Case getCaseEntity() {
		return caseEntity;
	}

	public void setCaseEntity(Case caseEntity) {
		this.caseEntity = caseEntity;
	}



    
}
