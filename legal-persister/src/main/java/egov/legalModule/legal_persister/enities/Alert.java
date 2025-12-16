package egov.legalModule.legal_persister.enities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Alert {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("alert_id")
    private Long alertId;

    @JsonProperty("alert_date")
    private String alertDate;

    @JsonProperty("message")
    private String message;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hearing_id")
    @JsonProperty("hearing_detail")
    private HearingDetail hearingDetail;

	public Long getAlertId() {
		return alertId;
	}

	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	public String getAlertDate() {
		return alertDate;
	}

	public void setAlertDate(String alertDate) {
		this.alertDate = alertDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HearingDetail getHearingDetail() {
		return hearingDetail;
	}

	public void setHearingDetail(HearingDetail hearingDetail) {
		this.hearingDetail = hearingDetail;
	}

    
}



