package egov.legalModule.legal_persister.entities;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cases")
public class CaseRegisterEntity{
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "case_id")
	private Long caseId;
	
    private String tenantId;
    @Column(name = "case_type", nullable = false)
    private String caseType;
    @Column(name = "case_category", nullable = false)
    private String caseCategory;
    private String courtType;
    private String courtCode;
    
    @OneToOne
    @JoinColumn(name = "case_detail_id" )
    private CaseDetailsEntity caseDetails;
    
    @OneToMany(mappedBy = "caseEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<AdvocateEntity> advocate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ulb_officer_id", nullable = false)
    private UlbOfficerEntity ulbOfficer;
    
    @OneToOne(mappedBy = "caseEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PetitionerEntity petitioner;
    
    @OneToMany(mappedBy = "caseEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<DocumentEntity> documents;
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getCaseCategory() {
		return caseCategory;
	}
	public void setCaseCategory(String caseCategory) {
		this.caseCategory = caseCategory;
	}
	public String getCourtType() {
		return courtType;
	}
	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}
	public String getCourtCode() {
		return courtCode;
	}
	public void setCourtCode(String courtCode) {
		this.courtCode = courtCode;
	}
	public CaseDetailsEntity getCaseDetails() {
		return caseDetails;
	}
	public void setCaseDetails(CaseDetailsEntity caseDetails) {
		this.caseDetails = caseDetails;
	}
	public ArrayList<AdvocateEntity> getAdvocate() {
		return advocate;
	}
	public void setAdvocate(ArrayList<AdvocateEntity> advocate) {
		this.advocate = advocate;
	}
	public UlbOfficerEntity getUlbOfficer() {
		return ulbOfficer;
	}
	public void setUlbOfficer(UlbOfficerEntity ulbOfficer) {
		this.ulbOfficer = ulbOfficer;
	}
	public PetitionerEntity getPetitioner() {
		return petitioner;
	}
	public void setPetitioner(PetitionerEntity petitioner) {
		this.petitioner = petitioner;
	}
	public ArrayList<DocumentEntity> getDocuments() {
		return documents;
	}
	public void setDocuments(ArrayList<DocumentEntity> documents) {
		this.documents = documents;
	}
    
    
}