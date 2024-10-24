package project.app.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

    private static final Key AVAIN = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    private static final long JWT_TOKEN_VALIDITY = 10 * 60 * 60;

    // Tokenin luonti käyttäjänimellä
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(AVAIN)
                .compact();
    }

    // Tokenista käyttäjänimen haku
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(AVAIN).parseClaimsJws(token).getBody().getSubject();
    }

    // Tarkista, onko token vanhentunut
    private Boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parser().setSigningKey(AVAIN).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

    // Tokenin validointi käyttäjänimellä
    public Boolean validateToken(String token, String username) {
        String extractedUsername = getUsernameFromToken(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}

