package pl.mateuszkoczan.springframework.recipeapp.services;

import pl.mateuszkoczan.springframework.recipeapp.domains.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);
}