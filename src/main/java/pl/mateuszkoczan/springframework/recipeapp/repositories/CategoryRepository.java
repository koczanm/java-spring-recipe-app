package pl.mateuszkoczan.springframework.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.mateuszkoczan.springframework.recipeapp.domains.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
