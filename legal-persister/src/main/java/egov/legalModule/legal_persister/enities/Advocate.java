package egov.legalModule.legal_persister.enities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"advocate\"")
public class Advocate {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("advocate_id")
    private Long advocateId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("role")
    private String role;

    @JsonProperty("notification_sent")
    private boolean notificationSent;
    
    
    
    
	public Advocate(Long advocateId, String name, String role, boolean notificationSent) {
		super();
		this.advocateId = advocateId;
		this.name = name;
		this.role = role;
		this.notificationSent = notificationSent;
	}
	public Advocate() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isNotificationSent() {
		return notificationSent;
	}
	public void setNotificationSent(boolean notificationSent) {
		this.notificationSent = notificationSent;
	}

    
}
