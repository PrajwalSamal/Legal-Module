package egov.legalModule.legal_persister.enities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InterimOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interimOrderId;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_details")
    private String orderDetails;

    // Getters and Setters
}

