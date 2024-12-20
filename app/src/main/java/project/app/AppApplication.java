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

import project.app.domain.Hinnasto;
import project.app.domain.HinnastoRepository;
import project.app.domain.Kayttaja;
import project.app.domain.KayttajaRepository;
import project.app.domain.Lippu;
import project.app.domain.LippuRepository;
import project.app.domain.Maksutapahtuma;
import project.app.domain.MaksutapahtumaRepository;
import project.app.domain.Tapahtuma;
import project.app.domain.TapahtumaRepository;

@SpringBootApplication
public class AppApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner ticketGuruDemo(HinnastoRepository hintarepo,
			KayttajaRepository kayttajarepo, LippuRepository lippurepo, MaksutapahtumaRepository maksurepo,
			TapahtumaRepository tapahtumarepo) {
		return (args) -> {

			LocalDate aika = LocalDate.of(2024, 12, 14);
			LocalDate myynninloppu = LocalDate.of(2024, 12, 13);
			LocalDateTime aikaleima = LocalDateTime.now();

			kayttajarepo.save(new Kayttaja("admin", "admin", passwordEncoder.encode("admin"), "admin", "ADMIN"));
			kayttajarepo.save(new Kayttaja("myyja", "myyja", passwordEncoder.encode("myyja"), "myyja", "USER"));
			tapahtumarepo.save(
					new Tapahtuma("Uusi tapahtuma", aika, "Olympiastadion", "Hyvä tapahtuma :D", 600, myynninloppu));
			tapahtumarepo.save(
					new Tapahtuma("Toinen tapahtuma", aika, "Hartwall areena", "Konsertti", 50, myynninloppu));
			hintarepo.save(new Hinnasto(tapahtumarepo.findByTapahtumaId(1), "opiskelija", 12));
			hintarepo.save(new Hinnasto(tapahtumarepo.findByTapahtumaId(1), "aikuinen", 20));

			lippurepo.save(new Lippu(tapahtumarepo.findByTapahtumaId(1), hintarepo.findByHinnastoId(1)));
			lippurepo.save(new Lippu(tapahtumarepo.findByTapahtumaId(1), hintarepo.findByHinnastoId(1),
					maksurepo.findByMaksutapahtumaId(1), false, 1, false));
			lippurepo.save(new Lippu(tapahtumarepo.findByTapahtumaId(1), hintarepo.findByHinnastoId(1),
					maksurepo.findByMaksutapahtumaId(1), false, 1, false));

			// Hae maksutapahtumaan liittyvät liput
			List<Lippu> liput = lippurepo.findByMaksutapahtuma(maksurepo.findByMaksutapahtumaId(1));

			// Laske lippujen hinnat yhteen
			double yhteishinta = liput.stream()
					.mapToDouble(lippu -> lippu.getHinnasto().getHinta())
					.sum();
			maksurepo.save(new Maksutapahtuma(yhteishinta, aikaleima, kayttajarepo.findByKayttajatunnus("admin")));

			Lippu muutettavaLippu = lippurepo.findByLippuId(1);
			muutettavaLippu.setMaksutapahtuma(maksurepo.findByMaksutapahtumaId(1));
			lippurepo.save(muutettavaLippu);
		};
	}

}
