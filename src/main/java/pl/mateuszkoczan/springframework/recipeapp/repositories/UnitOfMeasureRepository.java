package pl.mateuszkoczan.springframework.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.mateuszkoczan.springframework.recipeapp.domains.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
