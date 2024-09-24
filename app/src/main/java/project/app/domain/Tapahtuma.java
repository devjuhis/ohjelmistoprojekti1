package project.app.domain;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Tapahtuma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tapahtuma_id;

    private LocalDate aika;

    @Column(length = 60)
    private String paikka;

    private String kuvaus;

    private int lippumaara;

    private LocalDate ennakkomyynti;

    public Tapahtuma() {
    }

    public Tapahtuma(LocalDate aika, String paikka, String kuvaus, int lippumaara, LocalDate ennakkomyynti) {
        this.aika = aika;
        this.paikka = paikka;
        this.kuvaus = kuvaus;
        this.lippumaara = lippumaara;
        this.ennakkomyynti = ennakkomyynti;
    }

    public long getTapahtuma_id() {
        return tapahtuma_id;
    }

    public void setTapahtuma_id(long tapahtuma_id) {
        this.tapahtuma_id = tapahtuma_id;
    }

    public LocalDate getAika() {
        return aika;
    }

    public void setAika(LocalDate aika) {
        this.aika = aika;
    }

    public String getPaikka() {
        return paikka;
    }

    public void setPaikka(String paikka) {
        this.paikka = paikka;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public int getLippumaara() {
        return lippumaara;
    }

    public void setLippumaara(int lippumaara) {
        this.lippumaara = lippumaara;
    }

    public LocalDate getEnnakkomyynti() {
        return ennakkomyynti;
    }

    public void setEnnakkomyynti(LocalDate ennakkomyynti) {
        this.ennakkomyynti = ennakkomyynti;
    }

    @Override
    public String toString() {
        return "Tapahtuma [tapahtuma_id=" + tapahtuma_id + ", aika=" + aika + ", paikka=" + paikka + ", kuvaus="
                + kuvaus + ", lippumaara=" + lippumaara + ", ennakkomyynti=" + ennakkomyynti + "]";
    }
}