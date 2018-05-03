package pl.mateuszkoczan.springframework.recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.mateuszkoczan.springframework.recipeapp.commands.RecipeCommand;
import pl.mateuszkoczan.springframework.recipeapp.domains.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private  NoteCommandToNote noteConverter;

    public RecipeCommandToRecipe(NoteCommandToNote noteConverter) {
        this.noteConverter = noteConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setImage(recipeCommand.getImage());
        recipe.setIngredients(recipeCommand.getIngredients());
        recipe.setNote(noteConverter.convert(recipeCommand.getNote()));
        recipe.setCategories(recipeCommand.getCategories());

        return recipe;
    }
}
