package egov.legalModule.legal_persister.enities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CaseClosure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long closureId;

    @Column(name = "closure_date")
    private Date closureDate;

    @Column(name = "closure_reason")
    private String closureReason;

    // Getters and Setters
}
