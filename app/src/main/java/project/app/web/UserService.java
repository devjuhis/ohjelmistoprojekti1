package project.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import project.app.domain.Kayttaja;
import project.app.domain.KayttajaRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    KayttajaRepository repository;

    // Palautetaan käyttäjän tiedot
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Kayttaja kirjautunutkayttaja = repository.findByKayttajatunnus(username);
        if (kirjautunutkayttaja == null) {
            System.out.println("Käyttäjää ei löytynyt: " + username);
            throw new UsernameNotFoundException("Käyttäjää ei löytynyt: " + username);
        }

         // Tulosta käyttäjätiedot debugia varten
        System.out.println("Käyttäjä löytyi: " + kirjautunutkayttaja);

        return new org.springframework.security.core.userdetails.User(kirjautunutkayttaja.getKayttajatunnus(), kirjautunutkayttaja.getSalasana(), 
                        AuthorityUtils.createAuthorityList(kirjautunutkayttaja.getOikeus()));
    }
}
