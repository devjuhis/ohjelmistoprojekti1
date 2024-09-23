package project.app.domain;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Maksutapahtuma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long maksutapahtuma_id;
    private double hintayhteensa;
    private LocalDateTime aikaleima;
    
//FK erittely_id
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maksutapahtuma")
    private List<Erittely> erittelyt;
//FK kayttaja_id
    @ManyToOne
    @JoinColumn(name = "kayttaja_id")
    private Kayttaja kayttaja;

}
