package io.agileintelligent.ppmtool.repositories;

import io.agileintelligent.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    @Override
    Optional<Project> findById(Long aLong);

    @Override
    Iterable<Project> findAll();
}
