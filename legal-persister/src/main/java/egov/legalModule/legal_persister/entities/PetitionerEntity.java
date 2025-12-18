package egov.legalModule.legal_persister.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "petitioners")
public class PetitionerEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "petitioner_id")
	private Long petitionerId;
	@Column(name = "petitioner_type", nullable = false)
    public String petitionerType;
    public String name;
    public String address;
    @Column(name = "contact_number", nullable = false)
    public String contactNumber;
    public String email;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id", nullable = false)
    private CaseRegisterEntity caseEntity;
    
	public Long getPetitionerId() {
		return petitionerId;
	}
	public void setPetitionerId(Long petitionerId) {
		this.petitionerId = petitionerId;
	}
	public String getPetitionerType() {
		return petitionerType;
	}
	public void setPetitionerType(String petitionerType) {
		this.petitionerType = petitionerType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    
}