package project.app.domain;

import exceptions.BadRequestException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Lippu {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long lippuId;

    @ManyToOne
    @JoinColumn(name = "tapahtumaid", nullable = false)
    @NotNull(message="tapahtuma ei saa olla null")
    private Tapahtuma tapahtuma;

    @ManyToOne
    @JoinColumn(name = "hinnastoId", nullable = false)
    @NotNull(message="hinnasto ei saa olla null")
    private Hinnasto hinnasto;

    @ManyToOne
    @JoinColumn(name = "maksutapahtumaId")
    private Maksutapahtuma maksutapahtuma;

    // jos käytetty true -> käyttämätön false
    @NotNull(message="käytetty ei voi olla null")
    private Boolean kaytetty = false;

    //palautettu = -1 -> ei = 1
    @Min(value = -1, message = "maara voi olla vain -1 (palautettu) tai 1 (ei palautettu)")
    @Max(value = 1, message = "maara voi olla vain 1 (ei palautettu) tai -1 (palautettu)")
    private int maara = 1;

    @NotNull(message="removed ei voi olla null")
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

        if (kaytetty == true && removed == true) {
            throw new BadRequestException("Lippua ei voida asettaa käytetyksi, koska se on poistettu.");
        } 
        else if (kaytetty == true && maara == -1) {
            throw new BadRequestException("Lippua ei voida asettaa käytetyksi, koska se on palautettu.");
        } 
        else {
            this.kaytetty = kaytetty;
        }
}  

    public int getMaara() {
        return maara;
    }

    public void setMaara(int maara) {
        
        if (maara == -1 && (removed || kaytetty)) {
            throw new BadRequestException("Maara ei voi olla -1 (palautettu), jos lippu on poistettu tai käytetty.");
        }
    
        if (maara == 1 || maara == -1) {
            this.maara = maara;
        } else {
            throw new BadRequestException("Maara voi olla vain 1 tai -1");
        }
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        if (removed == true && (kaytetty == true || maara == -1)) {
            throw new BadRequestException("Lippua ei voi asettaa 'poistettu', jos se on jo käytetty tai palautettu.");
        } else {
            this.removed = removed;
        }
    }

    @Override
    public String toString() {
        return "Lippu [lippuid=" + lippuId + ", tapahtuma=" + tapahtuma + ", hinnasto=" + hinnasto + ", kaytetty="
                + kaytetty + "]";
    }
    
}
