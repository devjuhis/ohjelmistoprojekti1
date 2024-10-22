package project.app.domain;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Tapahtuma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tapahtumaId;

    @Column(length = 60)
    @NotNull(message = "Nimi ei voi olla null")
    private String nimi;

    @NotNull(message = "Aika ei voi olla null")
    private LocalDate aika;

    @Column(length = 60)
    @NotNull(message = "Paikka ei voi olla null")
    private String paikka;

    @Column(length = 500)
    private String kuvaus;

    @NotNull(message = "Lippumaara ei voi olla null")
    @Min(value = 0, message = "Lippumaara ei voi olla negatiivinen")
    private int lippumaara;

    @NotNull(message = "Ennakkomyynti ei voi olla null")
    private LocalDate ennakkomyynti;

    public Tapahtuma() {
    }

    public Tapahtuma(String nimi, LocalDate aika, String paikka, String kuvaus, int lippumaara,
            LocalDate ennakkomyynti) {
        this.nimi = nimi;
        this.aika = aika;
        this.paikka = paikka;
        this.kuvaus = kuvaus;
        this.lippumaara = lippumaara;
        this.ennakkomyynti = ennakkomyynti;
    }

    public long getTapahtumaId() {
        return tapahtumaId;
    }

    public void setTapahtumaId(long tapahtumaId) {
        this.tapahtumaId = tapahtumaId;
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
        return "Tapahtuma [tapahtumaid=" + tapahtumaId + ", nimi=" + nimi + ", aika=" + aika + ", paikka=" + paikka
                + ", kuvaus=" + kuvaus + ", lippumaara=" + lippumaara + ", ennakkomyynti=" + ennakkomyynti + "]";
    }
}
