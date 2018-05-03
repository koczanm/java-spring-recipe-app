package pl.mateuszkoczan.springframework.recipeapp.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.mateuszkoczan.springframework.recipeapp.commands.RecipeCommand;
import pl.mateuszkoczan.springframework.recipeapp.converters.RecipeCommandToRecipe;
import pl.mateuszkoczan.springframework.recipeapp.converters.RecipeToRecipeCommand;
import pl.mateuszkoczan.springframework.recipeapp.domains.Recipe;
import pl.mateuszkoczan.springframework.recipeapp.repositories.RecipeRepository;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {
    private static final String NEW_DESCRIPTION ="New Description";

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void saveRecipeCommand() {
        // given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        // when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        // then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), testRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), testRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), testRecipeCommand.getIngredients().size());
    }
}