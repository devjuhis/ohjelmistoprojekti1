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
    private long hinnastoid;
    
    //FK erittely_id
    @ManyToOne
    @JoinColumn(name = "tapahtuma_id", nullable = false)
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
        return hinnastoid;
    }

    public void setHinnastoid(long hinnasto_id) {
        this.hinnastoid = hinnasto_id;
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
        return "Hinnasto [hinnasto_id=" + hinnastoid + ", tapahtuma=" + tapahtuma + ", hintaluokka=" + hintaluokka
                + ", hinta=" + hinta + "]";
    }
    
}
