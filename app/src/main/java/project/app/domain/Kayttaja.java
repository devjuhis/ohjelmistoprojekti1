package project.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Kayttaja {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long kayttaja_id;
    
    private String etunimi;

    private String sukunimi;

    private String salasana;

    private String kayttajatunnus;

    private String oikeus;

    //Tyhjä konstruktori
    public Kayttaja() {
    }

    //Konstruktori arvoilla
    public Kayttaja(long kayttaja_id, String etunimi, String sukunimi, String salasana, String kayttajatunnus,
            String oikeus) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.salasana = salasana;
        this.kayttajatunnus = kayttajatunnus;
        this.oikeus = oikeus;
    }

    //Getterit ja setterit
    public long getKayttaja_id() {
        return kayttaja_id;
    }

    public void setKayttaja_id(long kayttaja_id) {
        this.kayttaja_id = kayttaja_id;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public String getKayttajatunnus() {
        return kayttajatunnus;
    }

    public void setKayttajatunnus(String kayttajatunnus) {
        this.kayttajatunnus = kayttajatunnus;
    }

    public String getOikeus() {
        return oikeus;
    }

    public void setOikeus(String oikeus) {
        this.oikeus = oikeus;
    }

    //ToString
    @Override
    public String toString() {
        return "Kayttaja [kayttaja_id=" + kayttaja_id + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi
                + ", salasana=" + salasana + ", kayttajatunnus=" + kayttajatunnus + ", oikeus=" + oikeus + "]";
    }

    

    
}

