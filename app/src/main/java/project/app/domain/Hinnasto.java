package project.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Hinnasto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long hinnastoId;
    
    //FK erittely_id
    @ManyToOne
    @JoinColumn(name = "tapahtumaId", nullable = false)
    private Tapahtuma tapahtuma;

    @Column (length = 30)
    private String hintaluokka;

    private double hinta;

    public Hinnasto() {
    }

    public Hinnasto(Tapahtuma tapahtuma, String hintaluokka, double hinta) {
        this.tapahtuma = tapahtuma;
        this.hintaluokka = hintaluokka;
        this.hinta = hinta;
    }

    public long getHinnastoid() {
        return hinnastoId;
    }

    public void setHinnastoid(long hinnastoId) {
        this.hinnastoId = hinnastoId;
    }

    public Tapahtuma getTapahtuma() {
        return tapahtuma;
    }

    public void setTapahtuma(Tapahtuma tapahtuma) {
        this.tapahtuma = tapahtuma;
    }

    public String getHintaluokka() {
        return hintaluokka;
    }

    public void setHintaluokka(String hintaluokka) {
        this.hintaluokka = hintaluokka;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    @Override
    public String toString() {
        return "Hinnasto [hinnasto_id=" + hinnastoId + ", tapahtuma=" + tapahtuma + ", hintaluokka=" + hintaluokka
                + ", hinta=" + hinta + "]";
    }
    
}
