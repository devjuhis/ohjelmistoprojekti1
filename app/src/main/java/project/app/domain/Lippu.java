package project.app.domain;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Lippu {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long lippu_id;

    @OneToOne
    @JoinColumn(name = "tapahtuma_id", nullable = false)
    private Tapahtuma tapahtuma;

    @OneToOne
    @JoinColumn(name = "hinnasto_id")
    private Hinnasto hinnasto;

    @Column(nullable = true)
    private int kaytetty = 0;

    // getterit ja setterit
    public int getLippuId() {
        return lippu_id;
    }

    public void setLippuId(int lippu_id) {
        this.lippu_id = lippu_id;
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
}
