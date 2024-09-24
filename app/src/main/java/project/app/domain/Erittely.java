package project.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Erittely {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long erittely_id;
    private Integer maara;
//FK maksutapahtuma_id 
    @ManyToOne
    @JoinColumn(name = "maksutapahtuma_id")
    private Maksutapahtuma maksutapahtuma;
//FK lippu_id 
    @ManyToOne
    @JoinColumn(name = "lippu_id")
    private Lippu lippu;

    // Parametriton konstruktori
    public Erittely() {
    }

    // Parametrillinen konstruktori
    public Erittely(Integer maara, Maksutapahtuma maksutapahtuma, Lippu lippu) {
        this.maara = maara;
        this.maksutapahtuma = maksutapahtuma;
        this.lippu = lippu;
    }

    // getterit ja setterit
    public long getErittely_id() {
        return erittely_id;
    }


    public void setErittely_id(long erittely_id) {
        this.erittely_id = erittely_id;
    }


    public Integer getMaara() {
        return maara;
    }


    public void setMaara(Integer maara) {
        this.maara = maara;
    }


    public Maksutapahtuma getMaksutapahtuma() {
        return maksutapahtuma;
    }


    public void setMaksutapahtuma(Maksutapahtuma maksutapahtuma) {
        this.maksutapahtuma = maksutapahtuma;
    }


    public Lippu getLippu() {
        return lippu;
    }


    public void setLippu(Lippu lippu) {
        this.lippu = lippu;
    }

    @Override
    public String toString() {
        return "Erittely [erittely_id=" + erittely_id + ", maara=" + maara + ", maksutapahtuma=" + maksutapahtuma
                + ", lippu=" + lippu + "]";
    }

    
    

    

    
  


}
