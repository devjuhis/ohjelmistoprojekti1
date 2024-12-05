package project.app.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface HinnastoRepository extends CrudRepository<Hinnasto, Long> {

    Hinnasto findByHinnastoId(long hinnastoid);

    List<Hinnasto> findByTapahtuma(Tapahtuma tapahtuma);
}
