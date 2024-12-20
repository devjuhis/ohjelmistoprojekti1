package project.app.domain;

import org.springframework.data.repository.CrudRepository;

public interface TapahtumaRepository extends CrudRepository<Tapahtuma, Long> {

    Tapahtuma findByTapahtumaId(long tapahtumaId);
}
