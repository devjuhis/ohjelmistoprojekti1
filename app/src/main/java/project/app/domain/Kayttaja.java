package project.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Kayttaja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kayttajaid", nullable = false, unique = true)
    @NotNull(message = "Käyttäjän id ei voi olla tyhjä")
    private long kayttajaId;

    @Column(name = "etunimi")
    @Size(min = 2, max = 30, message = "Etunimi tulee olla 2-30 merkkiä")
    private String etunimi;

    @Column(name = "sukunimi")
    @Size(min = 2, max = 30, message = "Sukunimi tulee olla 2-30 merkkiä")
    private String sukunimi;

    @Column(name = "salasana")
    @Size(min = 5, max = 256, message = "Salasanan tulee olla 5-256 merkkiä")
    @NotNull(message = "Salasana ei voi olla tyhjä")
    private String salasana;

    @Column(name = "kayttajatunnus", unique = true)
    @Size(min = 3, max = 30, message = "Käyttäjätunnuksen tulee olla 3-30 merkkiä")
    @NotNull(message = "Käyttäjätunnus ei voi olla tyhjä")
    private String kayttajatunnus;

    @Column(name = "oikeus")
    @Size(min = 4, max = 30, message = "Oikeuden tulee olla 4-30 merkkiä")
    @NotNull(message = "Käyttäjän oikeus ei voi olla tyhjä")
    private String oikeus;

    @Column(name = "aktiivinen")
    @NotNull(message = "Käyttäjän aktiivisuus ei voi olla tyhjä")
    private Boolean aktiivisuus = true;

    // Tyhjä konstruktori
    
    public Kayttaja() {
    }

    // Konstruktori arvoilla
    
    public Kayttaja(@Size(min = 2, max = 30, message = "Etunimi tulee olla 2-30 merkkiä") String etunimi,
            @Size(min = 2, max = 30, message = "Sukunimi tulee olla 2-30 merkkiä") String sukunimi,
            @Size(min = 5, max = 256, message = "Salasanan tulee olla 5-256 merkkiä") @NotNull(message = "Salasana ei voi olla tyhjä") String salasana,
            @Size(min = 3, max = 30, message = "Käyttäjätunnuksen tulee olla 3-30 merkkiä") @NotNull(message = "Käyttäjätunnus ei voi olla tyhjä") String kayttajatunnus,
            @Size(min = 4, max = 30, message = "Oikeuden tulee olla 4-30 merkkiä") @NotNull(message = "Käyttäjän oikeus ei voi olla tyhjä") String oikeus) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.salasana = salasana;
        this.kayttajatunnus = kayttajatunnus;
        this.oikeus = oikeus;
    }

    // Getterit ja setterit
    public long getKayttajaId() {
        return kayttajaId;
    }

    public void setKayttajaId(long kayttajaId) {
        this.kayttajaId = kayttajaId;
    }

    public Boolean getAktiivisuus() {
        return aktiivisuus;
    }

    public void setAktiivisuus(Boolean aktiivisuus) {
        this.aktiivisuus = aktiivisuus;
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

    // ToString
    @Override
    public String toString() {
        return "Kayttaja [kayttaja_id=" + kayttajaId + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi
                + ", salasana=" + salasana + ", kayttajatunnus=" + kayttajatunnus + ", oikeus=" + oikeus + "]";
    }

}
