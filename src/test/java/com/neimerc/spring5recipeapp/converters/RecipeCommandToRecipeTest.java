package com.neimerc.spring5recipeapp.converters;

import com.neimerc.spring5recipeapp.commands.CategoryCommand;
import com.neimerc.spring5recipeapp.commands.IngredientCommand;
import com.neimerc.spring5recipeapp.commands.NotesCommands;
import com.neimerc.spring5recipeapp.commands.RecipeCommand;
import com.neimerc.spring5recipeapp.domain.Difficulty;
import com.neimerc.spring5recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    public static final Long ID = 1L;
    public static final String DESCRIPTION = "description";
    public static final Integer PREP_TIME = 10;
    public static final Integer COOK_TIME = 15;
    public static final Integer SERVINGS = 4;
    public static final String SOURCE = "source";
    public static final String URL = "url";
    public static final String DIRECTIONS = "directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long ING_ID_1 = 1L;
    public static final Long ING_ID_2 = 2L;
    public static final Long NOTES_ID = 1L;

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes(), new CategoryCommandToCategory());
    }

    @Test
    void testNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        // given
        RecipeCommand command = new RecipeCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);
        command.setDirections(DIRECTIONS);
        command.setDifficulty(DIFFICULTY);

        IngredientCommand ingredient1 = new IngredientCommand();
        ingredient1.setId(ING_ID_1);
        command.getIngredients().add(ingredient1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(ING_ID_2);
        command.getIngredients().add(ingredient2);

        NotesCommands notes1 = new NotesCommands();
        notes1.setId(NOTES_ID);
        command.setNotes(notes1);

        CategoryCommand category1 = new CategoryCommand();
        category1.setId(CAT_ID_1);
        command.getCategories().add(category1);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CAT_ID_2);
        command.getCategories().add(category2);

        // when
        Recipe recipe = converter.convert(command);

        // then
        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
    }
}