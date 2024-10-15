package project.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Lippu {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long lippuId;

    @ManyToOne
    @JoinColumn(name = "tapahtumaid", nullable = false)
    private Tapahtuma tapahtuma;

    @ManyToOne
    @JoinColumn(name = "hinnastoId", nullable = false)
    private Hinnasto hinnasto;

    @ManyToOne
    @JoinColumn(name = "maksutapahtumaId")
    private Maksutapahtuma maksutapahtuma;

    // jos käytetty true -> käyttämätön false
    private Boolean kaytetty = false;

    //palautettu = -1 -> ei = 1
    private int maara = 1;

    
    private Boolean removed = false;

    public Lippu() {
    }

    public Lippu(Tapahtuma tapahtuma, Hinnasto hinnasto) {
        this.tapahtuma = tapahtuma;
        this.hinnasto = hinnasto;
    }

    public Lippu(Tapahtuma tapahtuma, Hinnasto hinnasto, Maksutapahtuma maksutapahtuma, Boolean kaytetty, int maara, Boolean removed) {
        this.tapahtuma = tapahtuma;
        this.hinnasto = hinnasto;
        this.maksutapahtuma = maksutapahtuma;
        this.kaytetty = kaytetty;
        this.maara = maara;
    }

    // getterit ja setterit
    public long getLippuId() {
        return lippuId;
    }

    public void setLippuId(int lippuId) {
        this.lippuId = lippuId;
    }

    public Tapahtuma getTapahtuma() {
        return tapahtuma;
    }

    public void setTapahtuma(Tapahtuma tapahtuma) {
        this.tapahtuma = tapahtuma;
    }

    public Hinnasto getHinnasto() {
        return hinnasto;
    }

    public void setHinnasto(Hinnasto hinnasto) {
        this.hinnasto = hinnasto;
    }

    public Maksutapahtuma getMaksutapahtuma() {
        return maksutapahtuma;
    }

    public void setMaksutapahtuma(Maksutapahtuma maksutapahtuma) {
        this.maksutapahtuma = maksutapahtuma;
    }

    public Boolean getKaytetty() {
        return kaytetty;
    }

    public void setKaytetty(Boolean kaytetty) {
        this.kaytetty = kaytetty;
    }

    public int getMaara() {
        return maara;
    }

    public void setMaara(int maara) {
        if (maara == 1 || maara == -1) {
            this.maara = maara; // Aseta arvo vain, jos se on 1 tai -1
        } else {
            throw new IllegalArgumentException("Maara voi olla vain 1 tai -1");
        }
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        return "Lippu [lippuid=" + lippuId + ", tapahtuma=" + tapahtuma + ", hinnasto=" + hinnasto + ", kaytetty="
                + kaytetty + "]";
    }
    
}
