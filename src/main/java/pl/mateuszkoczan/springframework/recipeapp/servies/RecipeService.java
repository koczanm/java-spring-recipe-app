package pl.mateuszkoczan.springframework.recipeapp.servies;

import org.springframework.context.annotation.Bean;
import pl.mateuszkoczan.springframework.recipeapp.domains.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
