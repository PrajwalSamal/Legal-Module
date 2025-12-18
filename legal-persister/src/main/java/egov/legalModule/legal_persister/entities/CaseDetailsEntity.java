package egov.legalModule.legal_persister.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class CaseDetailsEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long caseDetailId;
    public String title;
    public String description;
    public String department;
    @OneToOne(mappedBy = "caseDetails")
    private CaseRegisterEntity caseEntity;
	public Long getCaseDetailId() {
		return caseDetailId;
	}
	public void setCaseDetailId(Long caseDetailId) {
		this.caseDetailId = caseDetailId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public CaseRegisterEntity getCaseEntity() {
		return caseEntity;
	}
	public void setCaseEntity(CaseRegisterEntity caseEntity) {
		this.caseEntity = caseEntity;
	}
    
    
}