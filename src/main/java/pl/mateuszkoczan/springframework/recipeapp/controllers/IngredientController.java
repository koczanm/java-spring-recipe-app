package pl.mateuszkoczan.springframework.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mateuszkoczan.springframework.recipeapp.services.IngredientService;
import pl.mateuszkoczan.springframework.recipeapp.services.RecipeService;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("recipe/{id}/ingredients")
    public String getIngredientList(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));

        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String getIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),
                Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }
}
