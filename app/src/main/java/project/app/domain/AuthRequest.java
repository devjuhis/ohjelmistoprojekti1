package project.app.domain;

public class AuthRequest {
    
    private String kayttajatunnus;
    private String salasana;
    
    //Tyhjä konstruktori ja konstruktori arvoilla
    public AuthRequest() {
    }

    public AuthRequest(String kayttajatunnus, String salasana) {
        this.kayttajatunnus = kayttajatunnus;
        this.salasana = salasana;
    }

    public String getKayttajatunnus() {
        return kayttajatunnus;
    }

    public void setKayttajatunnus(String kayttajatunnus) {
        this.kayttajatunnus = kayttajatunnus;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    
}
