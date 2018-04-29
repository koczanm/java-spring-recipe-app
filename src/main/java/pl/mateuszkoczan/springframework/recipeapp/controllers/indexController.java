package pl.mateuszkoczan.springframework.recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mateuszkoczan.springframework.recipeapp.domains.Category;
import pl.mateuszkoczan.springframework.recipeapp.domains.UnitOfMeasure;
import pl.mateuszkoczan.springframework.recipeapp.repositories.CategoryRepository;
import pl.mateuszkoczan.springframework.recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Optional;

@Controller
public class indexController {
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public indexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage() {
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category ID is: " + categoryOptional.get().getId());
        System.out.println("Unit of measure ID is: " + unitOfMeasureOptional.get().getId());

        return "index";
    }
}
