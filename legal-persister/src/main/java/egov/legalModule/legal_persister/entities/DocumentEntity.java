package egov.legalModule.legal_persister.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DocumentEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long documentId;
    public String fileName;
    public String documentUid;
    public String fileStoreId;
    public String documentType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id", nullable = false)
    private CaseRegisterEntity caseEntity;
    
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDocumentUid() {
		return documentUid;
	}
	public void setDocumentUid(String documentUid) {
		this.documentUid = documentUid;
	}
	public String getFileStoreId() {
		return fileStoreId;
	}
	public void setFileStoreId(String fileStoreId) {
		this.fileStoreId = fileStoreId;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
    
    
}