package egov.legalModule.legal_persister.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UlbOfficerEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UlbOfficeId;
    public String officerId;
    public String name;
	public Long getUlbOfficeId() {
		return UlbOfficeId;
	}
	public void setUlbOfficeId(Long ulbOfficeId) {
		UlbOfficeId = ulbOfficeId;
	}
	public String getOfficerId() {
		return officerId;
	}
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
}