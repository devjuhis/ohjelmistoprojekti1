package project.app.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KayttajaTest {

    @Autowired
    private KayttajaRepository repository;

    @Test
    @Order(1)
    public void findByKayttajatunnus() {
        Kayttaja kayttaja = repository.findByKayttajatunnus("admin");
        assertThat(kayttaja.getKayttajatunnus().equals("admin"));
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
        Kayttaja kayttaja = repository.findByKayttajatunnus("myyja");
        repository.delete(kayttaja);
        assertThat(repository.findByKayttajatunnus("myyja")).isNull();
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
