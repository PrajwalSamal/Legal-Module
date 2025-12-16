package egov.legalModule.legal_persister.enities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class InterimOrder {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("interim_order_id")
    private Long interimOrderId;

    @JsonProperty("order_date")
    private String orderDate;

    @JsonProperty("order_details")
    private String orderDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "case_id")
    @JsonProperty("case")
    private Case caseEntity;


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



