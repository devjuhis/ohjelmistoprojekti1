package project.app.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface LippuRepository extends CrudRepository<Lippu, Long> {

    Lippu findByLippuId(long lippuid);
    List<Lippu> findByTapahtuma(Tapahtuma tapahtuma);
}
