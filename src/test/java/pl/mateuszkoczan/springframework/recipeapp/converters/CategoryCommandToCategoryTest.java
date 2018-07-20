package pl.mateuszkoczan.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import pl.mateuszkoczan.springframework.recipeapp.commands.CategoryCommand;
import pl.mateuszkoczan.springframework.recipeapp.domains.Category;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {
    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "description";

    private CategoryCommandToCategory converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void convertNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);

        Category category = converter.convert(categoryCommand);

        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}