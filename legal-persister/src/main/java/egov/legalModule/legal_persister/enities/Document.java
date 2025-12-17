package egov.legalModule.legal_persister.enities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"document\"")
public class Document {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("document_id")
    private Long documentId;

    @JsonProperty("document_type")
    private String documentType;

    @JsonProperty("upload_date")
    private String uploadDate;

    @JsonProperty("url")
    private String url;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "case_id",nullable = false)
    @JsonProperty("case")
    private Case caseEntity;
    
    


	public Document(Long documentId, String documentType, String uploadDate, String url, Case caseEntity) {
		super();
		this.documentId = documentId;
		this.documentType = documentType;
		this.uploadDate = uploadDate;
		this.url = url;
		this.caseEntity = caseEntity;
	}

	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Case getCase1() {
		return caseEntity;
	}

	public void setCase1(Case caseEntity) {
		this.caseEntity = caseEntity;
	}

    
}
