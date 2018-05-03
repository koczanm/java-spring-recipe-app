package pl.mateuszkoczan.springframework.recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.mateuszkoczan.springframework.recipeapp.commands.RecipeCommand;
import pl.mateuszkoczan.springframework.recipeapp.domains.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private NoteToNoteCommand noteConverter;

    public RecipeToRecipeCommand(NoteToNoteCommand noteConverter) {
        this.noteConverter = noteConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setImage(recipe.getImage());
        recipeCommand.setIngredients(recipe.getIngredients());
        recipeCommand.setNote(noteConverter.convert(recipe.getNote()));
        recipeCommand.setCategories(recipe.getCategories());

        return recipeCommand;
    }
}
