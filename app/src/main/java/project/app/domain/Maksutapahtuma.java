package project.app.domain;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@Entity
public class Maksutapahtuma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long maksutapahtumaId;

    @NotNull(message = "Hinta yhteensä ei saa olla null")
    private double hintayhteensa;
    @NotNull(message = "Aikaleima ei saa olla null")
    private LocalDateTime aikaleima;

    //FK kayttaja_id
    @ManyToOne
    @JoinColumn(name = "kayttajaId")
    @NotNull(message = "käyttäjä ei saa olla null")
    @JsonIgnoreProperties({"salasana", "etunimi", "sukunimi", "oikeus", "aktiivisuus", "kayttajatunnus"})
    private Kayttaja kayttaja;

    // Removed-kenttä maksutapahtuman palautusta varten, true = palautettu
    @NotNull(message = "poistettu-tila/removed ei saa olla null")
    private Boolean removed = false;

    // Parametriton konstruktori
    public Maksutapahtuma() {
    }

    // Parametrillinen konstruktori
    public Maksutapahtuma(double hintayhteensa, LocalDateTime aikaleima, Kayttaja kayttaja) {
        this.hintayhteensa = hintayhteensa;
        this.aikaleima = aikaleima;
        this.kayttaja = kayttaja;
    }

    // getterit ja setterit
    public long getMaksutapahtumaId() {
        return maksutapahtumaId;
    }


    public void setMaksutapahtumaId(long maksutapahtumaId) {
        this.maksutapahtumaId = maksutapahtumaId;
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


    public Kayttaja getKayttaja() {
        return kayttaja;
    }


    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }


    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    // ToString ilman erittelyt-listaa
    @Override
    public String toString() {
        return "Maksutapahtuma [maksutapahtuma_id=" + maksutapahtumaId + ", hintayhteensa=" + hintayhteensa
                + ", aikaleima=" + aikaleima + ", kayttaja=" + kayttaja + "]";
    }
}
