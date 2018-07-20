package pl.mateuszkoczan.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import pl.mateuszkoczan.springframework.recipeapp.commands.RecipeCommand;
import pl.mateuszkoczan.springframework.recipeapp.domains.*;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {
    private static final Long RECIPE_ID = 1L;
    private static final String DESCRIPTION = "Description";
    private static final Integer PREP_TIME = 7;
    private static final Integer COOK_TIME = 5;
    private static final Integer SERVINGS = 3;
    private static final String SOURCE = "Source";
    private static final String URL = "Url";
    private static final String DIRECTIONS = "Directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Long INGREDIENT_ID_1 = 1L;
    private static final Long INGREDIENT_ID_2 = 2L;
    private static final Long CATEGORY_ID_1 = 3L;
    private static final Long CATEGORY_ID_2 = 4L;
    private static final Long NOTE_ID = 5L;

    private RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand(),
                new NoteToNoteCommand());
    }

    @Test
    public void convertNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() {
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGREDIENT_ID_1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_ID_2);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        Category category1 = new Category();
        category1.setId(CATEGORY_ID_1);

        Category category2 = new Category();
        category2.setId(CATEGORY_ID_2);

        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Note note = new Note();
        note.setId(NOTE_ID);

        recipe.setNote(note);

        RecipeCommand recipeCommand = converter.convert(recipe);

        assertNotNull(recipeCommand);
        assertEquals(RECIPE_ID, recipeCommand.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(2, recipeCommand.getIngredients().size());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(NOTE_ID, recipeCommand.getNote().getId());
    }
}