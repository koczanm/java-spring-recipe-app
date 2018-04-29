package pl.mateuszkoczan.springframework.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.mateuszkoczan.springframework.recipeapp.domains.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
