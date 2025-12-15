package egov.legalModule.legal_persister.enities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Appeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appealId;

    @Column(name = "appeal_date")
    private Date appealDate;

    @Column(name = "appeal_status")
    private String appealStatus;

    // Getters and Setters
}

