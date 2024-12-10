package project.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


import project.app.domain.Kayttaja;
import project.app.domain.KayttajaRepository;

@SpringBootApplication
public class AppApplication {

	@Autowired
    private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner ticketGuruDemo(KayttajaRepository kayttajarepo) {
		return (args) -> {
			kayttajarepo.save(new Kayttaja("admin", "admin", passwordEncoder.encode("admin"), "admin", "ADMIN"));
			kayttajarepo.save(new Kayttaja("myyja", "myyja", passwordEncoder.encode("myyja"), "myyja", "USER"));
		};
	}
	

}
