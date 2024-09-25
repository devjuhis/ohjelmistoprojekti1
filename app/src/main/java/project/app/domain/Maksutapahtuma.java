package project.app.domain;
import java.time.LocalDateTime;
import java.util.List;

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
    private long maksutapahtumaid;
    private double hintayhteensa;
    private LocalDateTime aikaleima;
    
//FK erittely_id
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maksutapahtuma")
    private List<Erittely> erittelyt;
//FK kayttaja_id
    @ManyToOne
    @JoinColumn(name = "kayttaja_id")
    private Kayttaja kayttaja;

    // Parametriton konstruktori
    public Maksutapahtuma() {
    }

    // Parametrillinen konstruktori
    public Maksutapahtuma(double hintayhteensa, LocalDateTime aikaleima, List<Erittely> erittelyt, Kayttaja kayttaja) {
        this.hintayhteensa = hintayhteensa;
        this.aikaleima = aikaleima;
        this.erittelyt = erittelyt;
        this.kayttaja = kayttaja;
    }

    // getterit ja setterit
    public long getMaksutapahtumaid() {
        return maksutapahtumaid;
    }


    public void setMaksutapahtuma_id(long maksutapahtuma_id) {
        this.maksutapahtumaid = maksutapahtuma_id;
    }


    public double getHintayhteensa() {
        return hintayhteensa;
    }


    public void setHintayhteensa(double hintayhteensa) {
        this.hintayhteensa = hintayhteensa;
    }


    public LocalDateTime getAikaleima() {
        return aikaleima;
    }


    public void setAikaleima(LocalDateTime aikaleima) {
        this.aikaleima = aikaleima;
    }


    public List<Erittely> getErittelyt() {
        return erittelyt;
    }


    public void setErittelyt(List<Erittely> erittelyt) {
        this.erittelyt = erittelyt;
    }


    public Kayttaja getKayttaja() {
        return kayttaja;
    }


    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }

    // ToString ilman erittelyt-listaa
    @Override
    public String toString() {
        return "Maksutapahtuma [maksutapahtuma_id=" + maksutapahtumaid + ", hintayhteensa=" + hintayhteensa
                + ", aikaleima=" + aikaleima + ", kayttaja=" + kayttaja + "]";
    }

    
    
    
    

}
