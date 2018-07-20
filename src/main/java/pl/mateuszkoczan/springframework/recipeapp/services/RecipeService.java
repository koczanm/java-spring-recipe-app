package pl.mateuszkoczan.springframework.recipeapp.services;

import pl.mateuszkoczan.springframework.recipeapp.commands.RecipeCommand;
import pl.mateuszkoczan.springframework.recipeapp.domains.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findRecipeById(Long id);

    RecipeCommand findRecipeCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteById(Long id);
}
