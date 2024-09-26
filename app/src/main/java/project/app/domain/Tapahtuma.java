package project.app.domain;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Tapahtuma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tapahtumaid;

    @Column(length = 60)
    private String nimi;

    private LocalDate aika;

    @Column(length = 60)
    private String paikka;

    @Column(length = 500)
    private String kuvaus;

    private int lippumaara;

    private LocalDate ennakkomyynti;

    public Tapahtuma() {
    }

    public Tapahtuma(String nimi, LocalDate aika, String paikka, String kuvaus, int lippumaara, LocalDate ennakkomyynti) {
        this.nimi = nimi;
        this.aika = aika;
        this.paikka = paikka;
        this.kuvaus = kuvaus;
        this.lippumaara = lippumaara;
        this.ennakkomyynti = ennakkomyynti;
    }

    public long getTapahtumaid() {
        return tapahtumaid;
    }

    public void setTapahtumaid(long tapahtumaid) {
        this.tapahtumaid = tapahtumaid;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
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
        return "Tapahtuma [tapahtumaid=" + tapahtumaid + ", nimi=" + nimi + ", aika=" + aika + ", paikka=" + paikka
                + ", kuvaus=" + kuvaus + ", lippumaara=" + lippumaara + ", ennakkomyynti=" + ennakkomyynti + "]";
    }
}
