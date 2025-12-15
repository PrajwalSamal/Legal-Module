package egov.legalModule.legal_persister.enities;

import java.util.Date;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    @Column(name = "alert_date")
    private Date alertDate;

    private String message;

    @ManyToOne
    @JoinColumn(name = "hearing_id")
    private HearingDetails hearingDetails;

    // Getters and Setters
}

