package egov.legalModule.legal_persister.enities;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class HearingDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("hearing_id")
    private Long hearingId;

    @JsonProperty("hearing_date")
    private String hearingDate;

    @OneToMany(mappedBy = "hearingDetail")
    @JsonProperty("alerts")
    private List<Alert> alerts;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "case_id")
    @JsonProperty("case")
    private Case caseEntity;


	public Long getHearingId() {
		return hearingId;
	}

	public void setHearingId(Long hearingId) {
		this.hearingId = hearingId;
	}

	public String getHearingDate() {
		return hearingDate;
	}

	public void setHearingDate(String hearingDate) {
		this.hearingDate = hearingDate;
	}

	public List<Alert> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}

	public Case getCaseEntity() {
		return caseEntity;
	}

	public void setCaseEntity(Case caseEntity) {
		this.caseEntity = caseEntity;
	}

	

    
}
