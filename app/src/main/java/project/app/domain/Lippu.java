package project.app.domain;

import jakarta.persistence.Column;
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
    private long lippuid;

    @ManyToOne
    @JoinColumn(name = "tapahtuma_id", nullable = false)
    private Tapahtuma tapahtuma;

    @ManyToOne
    @JoinColumn(name = "hinnasto_id")
    private Hinnasto hinnasto;

    @Column(nullable = true)
    private int kaytetty = 0;

    public Lippu() {
    }

    public Lippu(Tapahtuma tapahtuma, Hinnasto hinnasto, int kaytetty) {
        this.tapahtuma = tapahtuma;
        this.hinnasto = hinnasto;
        this.kaytetty = kaytetty;
    }

    // getterit ja setterit
    public long getLippuId() {
        return lippuid;
    }

    public void setLippuId(int lippu_id) {
        this.lippuid = lippu_id;
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

    public int getKaytetty() {
        return kaytetty;
    }

    public void setKaytetty(int kaytetty) {
        this.kaytetty = kaytetty;
    }

    @Override
    public String toString() {
        return "Lippu [lippuid=" + lippuid + ", tapahtuma=" + tapahtuma + ", hinnasto=" + hinnasto + ", kaytetty="
                + kaytetty + "]";
    }
    
}
