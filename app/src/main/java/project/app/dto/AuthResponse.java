package project.app.dto;

public class AuthResponse {
    
    private String jwt;

    // Konstruktori

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    // JWT-tokenin palautus, settereitä ei ole syytä sillä generoitua tokenia ei tule muokata

    public String getJwt() {
        return jwt;
    }
}
