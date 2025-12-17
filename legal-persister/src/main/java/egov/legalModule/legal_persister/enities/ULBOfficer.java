package egov.legalModule.legal_persister.enities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"ULB_officer\"")
public class ULBOfficer {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("officer_id")
    private Long officerId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("role")
    private String role;
    
	public ULBOfficer(Long officerId, String name, String role) {
		super();
		this.officerId = officerId;
		this.name = name;
		this.role = role;
	}
	public ULBOfficer() {
		super();
	}
	public Long getOfficerId() {
		return officerId;
	}
	public void setOfficerId(Long officerId) {
		this.officerId = officerId;
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

    
}
