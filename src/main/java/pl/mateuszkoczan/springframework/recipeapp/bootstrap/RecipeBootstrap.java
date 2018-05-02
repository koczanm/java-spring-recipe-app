package pl.mateuszkoczan.springframework.recipeapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.mateuszkoczan.springframework.recipeapp.domains.*;
import pl.mateuszkoczan.springframework.recipeapp.repositories.CategoryRepository;
import pl.mateuszkoczan.springframework.recipeapp.repositories.RecipeRepository;
import pl.mateuszkoczan.springframework.recipeapp.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Loading Bootstrap Data");

        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUOMOptional = unitOfMeasureRepository.findByDescription("each");

        if (!eachUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tablespoonUOMOptional = unitOfMeasureRepository.findByDescription("tablespoon");

        if (!tablespoonUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaspoonUOMOptional = unitOfMeasureRepository.findByDescription("teaspoon");

        if (!teaspoonUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUOMOptional = unitOfMeasureRepository.findByDescription("dash");

        if (!dashUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUOMOptional = unitOfMeasureRepository.findByDescription("pint");

        if (!pintUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupUOMOptional = unitOfMeasureRepository.findByDescription("cup");

        if (!cupUOMOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        UnitOfMeasure eachUOM = eachUOMOptional.get();
        UnitOfMeasure tablespoonUOM = tablespoonUOMOptional.get();
        UnitOfMeasure teaspoonUOM = teaspoonUOMOptional.get();
        UnitOfMeasure dashUOM = dashUOMOptional.get();
        UnitOfMeasure pintUOM = pintUOMOptional.get();
        UnitOfMeasure cupUOM = cupUOMOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole");
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setDirections("1. Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the" +
                " inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2. Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should " +
                "be a little chunky.)" +
                "\n" +
                "3. Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in " +
                "the lime juice will provide some balance to the richness of the avocado and will help delay the " +
                "avocados from turning brown." +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their " +
                "hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree " +
                "of hotness." +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. " +
                "Start with this recipe and adjust to your taste." +
                "\n" +
                "4. Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover " +
                "it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the " +
                "guacamole brown.) Refrigerate until ready to serve." +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it" +
                " just before serving." +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        Note guacamoleNote = new Note();
        guacamoleNote.setRecipeNote("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your " +
                "mashed avocados." +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches " +
                "in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries." +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of " +
                "availability of other ingredients stop you from making guacamole." +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole " +
                "dip. Purists may be horrified, but so what? It tastes great." +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");

        guacamoleRecipe.setNote(guacamoleNote);

        guacamoleRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUOM));
        guacamoleRecipe.addIngredient(new Ingredient("kosher salt", new BigDecimal(".5"), teaspoonUOM));
        guacamoleRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice",
                new BigDecimal(2), tablespoonUOM));
        guacamoleRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion",
                new BigDecimal(2), tablespoonUOM));
        guacamoleRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced",
                new BigDecimal(2), eachUOM));
        guacamoleRecipe.addIngredient(new Ingredient("cilantro", new BigDecimal(2), tablespoonUOM));
        guacamoleRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2),
                dashUOM));
        guacamoleRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped",
                new BigDecimal(".5"), eachUOM));

        guacamoleRecipe.getCategories().add(americanCategory);
        guacamoleRecipe.getCategories().add(mexicanCategory);

        guacamoleRecipe.setUrl("http://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecipe.setServings(4);
        guacamoleRecipe.setSource("Simply Recipes");

        recipes.add(guacamoleRecipe);

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setCookTime(9);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1. Prepare a gas or charcoal grill for medium-high, direct heat." +
                "\n" +
                "2. Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano," +
                " cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose " +
                "paste. Add the chicken to the bowl and toss to coat all over." +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings." +
                "\n" +
                "3. Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted" +
                " into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes." +
                "\n" +
                "4. Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high " +
                "heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and" +
                " heat for a few seconds on the other side." +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving." +
                "\n" +
                "5. Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of " +
                "arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with" +
                " the thinned sour cream. Serve with lime wedges." +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Note tacosNote = new Note();
        tacosNote.setRecipeNote("We have a family motto and it is this: Everything goes better in a tortilla." +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled" +
                " jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot" +
                " pan on the stove comes wafting through the house." +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and " +
                "sweet orange juice while the grill is heating. You can also use this time to prepare the taco " +
                "toppings." +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the " +
                "tacos and dig in. The whole meal comes together in about 30 minutes!" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

        tacosRecipe.setNote(tacosNote);

        tacosRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2),
                tablespoonUOM));
        tacosRecipe.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), teaspoonUOM));
        tacosRecipe.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaspoonUOM));
        tacosRecipe.addIngredient(new Ingredient("sugar", new BigDecimal(1), teaspoonUOM));
        tacosRecipe.addIngredient(new Ingredient("salt", new BigDecimal(".5"), teaspoonUOM));
        tacosRecipe.addIngredient(new Ingredient("clove of garlic, chopped", new BigDecimal(1),
                eachUOM));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1),
                tablespoonUOM));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3),
                tablespoonUOM));
        tacosRecipe.addIngredient(new Ingredient("olive oil", new BigDecimal(2), tablespoonUOM));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4),
                tablespoonUOM));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), eachUOM));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupUOM));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, sliced", new BigDecimal(2),
                eachUOM));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUOM));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"),
                pintUOM));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"),
                eachUOM));
        tacosRecipe.addIngredient(new Ingredient("roughly chopped cilantro", new BigDecimal(4),
                eachUOM));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk",
                new BigDecimal(4), cupUOM));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUOM));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        tacosRecipe.setUrl("http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacosRecipe.setServings(4);
        tacosRecipe.setSource("Simply Recipes");

        recipes.add(tacosRecipe);

        return recipes;
    }
}
