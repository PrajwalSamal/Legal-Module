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
@Table(name = "\"para_wise_remark\"")
public class ParaWiseRemark {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("para_wise_remark_id")
    private Long paraWiseRemarkId;

    @JsonProperty("para_number")
    private int paraNumber;

    @JsonProperty("remark")
    private String remark;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "case_id",nullable = false)
    @JsonProperty("case")
    private Case caseEntity;

	public ParaWiseRemark(Long paraWiseRemarkId, int paraNumber, String remark, Case caseEntity) {
		super();
		this.paraWiseRemarkId = paraWiseRemarkId;
		this.paraNumber = paraNumber;
		this.remark = remark;
		this.caseEntity = caseEntity;
	}

	public ParaWiseRemark() {
		super();
	}

	public Long getParaWiseRemarkId() {
		return paraWiseRemarkId;
	}

	public void setParaWiseRemarkId(Long paraWiseRemarkId) {
		this.paraWiseRemarkId = paraWiseRemarkId;
	}

	public int getParaNumber() {
		return paraNumber;
	}

	public void setParaNumber(int paraNumber) {
		this.paraNumber = paraNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Case getCaseEntity() {
		return caseEntity;
	}

	public void setCaseEntity(Case caseEntity) {
		this.caseEntity = caseEntity;
	}

	

    
}


