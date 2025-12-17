package egov.legalModule.legal_persister.enities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "\"interim_order\"")
public class InterimOrder {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("interim_order_id")
    private Long interimOrderId;

    @JsonProperty("order_date")
    private String orderDate;

    @JsonProperty("order_details")
    private String orderDetails;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "case_id",nullable = false)
    @JsonProperty("case")
    private Case caseEntity;

	public InterimOrder(Long interimOrderId, String orderDate, String orderDetails, Case caseEntity) {
		super();
		this.interimOrderId = interimOrderId;
		this.orderDate = orderDate;
		this.orderDetails = orderDetails;
		this.caseEntity = caseEntity;
	}

	public InterimOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getInterimOrderId() {
		return interimOrderId;
	}

	public void setInterimOrderId(Long interimOrderId) {
		this.interimOrderId = interimOrderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Case getCaseEntity() {
		return caseEntity;
	}

	public void setCaseEntity(Case caseEntity) {
		this.caseEntity = caseEntity;
	}

	

    
}



