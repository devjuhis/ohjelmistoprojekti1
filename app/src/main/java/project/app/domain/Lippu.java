package project.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Lippu {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long lippuId;

    @ManyToOne
    @JoinColumn(name = "tapahtumaid", nullable = false)
    private Tapahtuma tapahtuma;

    @ManyToOne
    @JoinColumn(name = "hinnasto_id")
    private Hinnasto hinnasto;

    @ManyToOne
    @JoinColumn(name = "maksutapahtumaId")
    private Maksutapahtuma maksutapahtuma;

    @Column(nullable = true)
    private int kaytetty = 0;

    @Column(nullable = true)
    //palautettu = -1 vai ei = 1
    private int maara = 1;

    public Lippu() {
    }

    public Lippu(Tapahtuma tapahtuma, Hinnasto hinnasto, int kaytetty, int maara) {
        this.tapahtuma = tapahtuma;
        this.hinnasto = hinnasto;
        this.kaytetty = kaytetty;
        this.maara = maara;
    }

    public Lippu(Tapahtuma tapahtuma, Hinnasto hinnasto, Maksutapahtuma maksutapahtuma, int kaytetty, int maara) {
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

    public int getKaytetty() {
        return kaytetty;
    }

    public void setKaytetty(int kaytetty) {
        this.kaytetty = kaytetty;
    }

    public int getMaara() {
        return maara;
    }

    public void setMaara(int maara) {
        this.maara = maara;
    }

    @Override
    public String toString() {
        return "Lippu [lippuid=" + lippuId + ", tapahtuma=" + tapahtuma + ", hinnasto=" + hinnasto + ", kaytetty="
                + kaytetty + "]";
    }
    
}
