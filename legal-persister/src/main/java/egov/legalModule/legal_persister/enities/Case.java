package egov.legalModule.legal_persister.enities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "register_case")
public class Case {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("case_id")
    private Long caseId;

    @JsonProperty("registration_date")
    private String registrationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advocate_id")
    @JsonProperty("advocate")
    private Advocate advocate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ulb_officer_id")
    @JsonProperty("ulb_officer")
    private ULBOfficer ulbOfficer;

    @OneToMany(mappedBy = "caseEntity")
    @JsonProperty("documents")
    private List<Document> documents;

    @OneToMany(mappedBy = "caseEntity")
    @JsonProperty("hearing_details")
    private List<HearingDetail> hearingDetails;

    @OneToOne(mappedBy = "caseEntity")
    @JsonProperty("judgment")
    private Judgment judgment;

    @OneToOne(mappedBy = "caseEntity")
    @JsonProperty("appeal")
    private Appeal appeal;

    @OneToOne(mappedBy = "caseEntity")
    @JsonProperty("counter_affidavit")
    private CounterAffidavit counterAffidavit;

    @OneToMany(mappedBy = "caseEntity")
    @JsonProperty("para_wise_remarks")
    private List<ParaWiseRemark> paraWiseRemarks;

    @OneToOne(mappedBy = "caseEntity")
    @JsonProperty("interim_order")
    private InterimOrder interimOrder;

    @OneToOne(mappedBy = "caseEntity")
    @JsonProperty("case_closure")
    private CaseClosure caseClosure;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assigned_advocate_id")
    @JsonProperty("assigned_advocate")
    private Advocate assignedAdvocate;


	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Advocate getAdvocate() {
		return advocate;
	}

	public void setAdvocate(Advocate advocate) {
		this.advocate = advocate;
	}

	public ULBOfficer getUlbOfficer() {
		return ulbOfficer;
	}

	public void setUlbOfficer(ULBOfficer ulbOfficer) {
		this.ulbOfficer = ulbOfficer;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public List<HearingDetail> getHearingDetails() {
		return hearingDetails;
	}

	public void setHearingDetails(List<HearingDetail> hearingDetails) {
		this.hearingDetails = hearingDetails;
	}

	public Judgment getJudgment() {
		return judgment;
	}

	public void setJudgment(Judgment judgment) {
		this.judgment = judgment;
	}

	public Appeal getAppeal() {
		return appeal;
	}

	public void setAppeal(Appeal appeal) {
		this.appeal = appeal;
	}

	public CounterAffidavit getCounterAffidavit() {
		return counterAffidavit;
	}

	public void setCounterAffidavit(CounterAffidavit counterAffidavit) {
		this.counterAffidavit = counterAffidavit;
	}

	public List<ParaWiseRemark> getParaWiseRemarks() {
		return paraWiseRemarks;
	}

	public void setParaWiseRemarks(List<ParaWiseRemark> paraWiseRemarks) {
		this.paraWiseRemarks = paraWiseRemarks;
	}

	public InterimOrder getInterimOrder() {
		return interimOrder;
	}

	public void setInterimOrder(InterimOrder interimOrder) {
		this.interimOrder = interimOrder;
	}

	public CaseClosure getCaseClosure() {
		return caseClosure;
	}

	public void setCaseClosure(CaseClosure caseClosure) {
		this.caseClosure = caseClosure;
	}

	public Advocate getAssignedAdvocate() {
		return assignedAdvocate;
	}

	public void setAssignedAdvocate(Advocate assignedAdvocate) {
		this.assignedAdvocate = assignedAdvocate;
	}

    
}
