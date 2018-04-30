package pl.mateuszkoczan.springframework.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.mateuszkoczan.springframework.recipeapp.domains.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
