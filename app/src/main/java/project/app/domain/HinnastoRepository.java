package project.app.domain;

import org.springframework.data.repository.CrudRepository;

public interface HinnastoRepository extends CrudRepository<Hinnasto, Long> {

    Hinnasto findByHinnastoId(long hinnastoid);
}
