package pl.mateuszkoczan.springframework.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mateuszkoczan.springframework.recipeapp.commands.IngredientCommand;
import pl.mateuszkoczan.springframework.recipeapp.converters.IngredientToIngredientCommand;
import pl.mateuszkoczan.springframework.recipeapp.domains.Recipe;
import pl.mateuszkoczan.springframework.recipeapp.repositories.RecipeRepository;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found");
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            throw new RuntimeException("Ingredient Not Found");
        }

        return ingredientCommandOptional.get();
    }
}
