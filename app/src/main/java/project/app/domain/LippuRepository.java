package project.app.domain;

import org.springframework.data.repository.CrudRepository;


public interface LippuRepository extends CrudRepository<Lippu, Long> {

    Lippu findByLippuid(long lippuid);
}
