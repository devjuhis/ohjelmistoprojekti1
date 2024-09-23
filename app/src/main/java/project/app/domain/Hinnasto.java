package project.app.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Hinnasto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long hinnasto_id;
    
    //FK erittely_id
    @ManyToOne
    @JoinColumn(name = "tapahtuma_id", nullable = false)
    private Tapahtuma tapahtuma;

    @Column (length = 30)
    private String hintaluokka;

    private double hinta;
    
}
