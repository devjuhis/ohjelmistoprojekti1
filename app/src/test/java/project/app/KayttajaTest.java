package project.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import static org.assertj.core.api.Assertions.assertThat;

import project.app.domain.Kayttaja;
import project.app.domain.KayttajaRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KayttajaTest {

    @Autowired
    private KayttajaRepository repository;

    @Test
    @Order(1)
    public void findByKayttajatunnus() {
        Kayttaja kayttaja = repository.findByKayttajatunnus("matti123");
        assertThat(kayttaja.getKayttajatunnus().equals("matti123"));
    }
    
    @Test
    @Order(2)
    public void createKayttaja() {
        Kayttaja kayttaja = new Kayttaja("testi", "ukko", "salasana", "testi123", "USER");
        assertThat(kayttaja.getKayttajaId()).isNotNull();
    }

    @Test
    @Order(3)
    public void deleteKayttaja() {
        Kayttaja kayttaja = repository.findByKayttajatunnus("matti321");
        repository.delete(kayttaja);
        assertThat(repository.findByKayttajatunnus("matti321")).isNull();
    }

    @Test
    @Order(4)
    public void kayttajaWithWrongValues() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Kayttaja kayttaja = new Kayttaja("a", "b", "c", "d", "e");

        Set<ConstraintViolation<Kayttaja>> violations = validator.validate(kayttaja);

        assertFalse(violations.isEmpty());

    }

}
