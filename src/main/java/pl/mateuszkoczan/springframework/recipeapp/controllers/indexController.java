package pl.mateuszkoczan.springframework.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mateuszkoczan.springframework.recipeapp.servies.RecipeService;

@Slf4j
@Controller
public class indexController {
    private final RecipeService recipeService;

    public indexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index Page");

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
