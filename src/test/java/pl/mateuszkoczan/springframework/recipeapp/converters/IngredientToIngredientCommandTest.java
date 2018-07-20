package pl.mateuszkoczan.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import pl.mateuszkoczan.springframework.recipeapp.commands.IngredientCommand;
import pl.mateuszkoczan.springframework.recipeapp.domains.Ingredient;
import pl.mateuszkoczan.springframework.recipeapp.domains.Recipe;
import pl.mateuszkoczan.springframework.recipeapp.domains.UnitOfMeasure;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {
    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "Cheeseburger";
    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final Long UOM_ID = 2L;
    private static final Recipe RECIPE = new Recipe();

    private IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullConvert() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convert() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);

        ingredient.setUnitOfMeasure(unitOfMeasure);
        ingredient.setRecipe(RECIPE);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(UOM_ID, ingredientCommand.getUnitOfMeasure().getId());
    }

    @Test
    public void convertWithNullUOM() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUnitOfMeasure(null);
        ingredient.setRecipe(RECIPE);

        IngredientCommand ingredientCommand = converter.convert(ingredient);

        assertNotNull(ingredientCommand);
        assertNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
    }
}