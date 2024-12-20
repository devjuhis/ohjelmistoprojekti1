package project.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import exceptions.CustomErrorResponse;

import project.app.dto.AuthRequest;
import project.app.dto.AuthResponse;
import project.app.security.JwtTokenUtil;
import project.app.service.UserService;


@RestController
@RequestMapping("/api")
public class AutentikointiController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    //Palautetaan token, jos autentikointi onnistuu
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getKayttajatunnus(), authRequest.getSalasana())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new CustomErrorResponse("Virheelliset tunnukset", HttpStatus.UNAUTHORIZED.value()));
        }

        // Generoidaan JWT-token
        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getKayttajatunnus());
        final String jwt = jwtTokenUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
    