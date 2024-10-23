package project.app.domain;

public class AuthResponse {
    
    private String jwt;

    // Konstruktori

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    // JWT-tokenin palautus, settereitä ei ole syytä olla ettei tokenia sillä generoitua tokenia ei ole syytä muokata
    
    public String getJwt() {
        return jwt;
    }
}
