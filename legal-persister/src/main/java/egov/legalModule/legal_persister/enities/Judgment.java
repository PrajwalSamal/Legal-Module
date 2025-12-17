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
@Table(name = "\"judgment\"")
public class Judgment {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("judgment_id")
    private Long judgmentId;

    @JsonProperty("judgment_date")
    private String judgmentDate;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("implementation_status")
    private String implementationStatus;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "case_id")
    @JsonProperty("case")
    private Case caseEntity;

	public Judgment(Long judgmentId, String judgmentDate, String summary, String implementationStatus,
			Case caseEntity) {
		super();
		this.judgmentId = judgmentId;
		this.judgmentDate = judgmentDate;
		this.summary = summary;
		this.implementationStatus = implementationStatus;
		this.caseEntity = caseEntity;
	}

	public Judgment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getJudgmentId() {
		return judgmentId;
	}

	public void setJudgmentId(Long judgmentId) {
		this.judgmentId = judgmentId;
	}

	public String getJudgmentDate() {
		return judgmentDate;
	}

	public void setJudgmentDate(String judgmentDate) {
		this.judgmentDate = judgmentDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImplementationStatus() {
		return implementationStatus;
	}

	public void setImplementationStatus(String implementationStatus) {
		this.implementationStatus = implementationStatus;
	}

	public Case getCaseEntity() {
		return caseEntity;
	}

	public void setCaseEntity(Case caseEntity) {
		this.caseEntity = caseEntity;
	}

	

    
}
