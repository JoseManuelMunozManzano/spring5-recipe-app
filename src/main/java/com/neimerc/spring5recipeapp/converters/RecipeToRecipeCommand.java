package com.neimerc.spring5recipeapp.converters;

import com.neimerc.spring5recipeapp.commands.RecipeCommand;
import com.neimerc.spring5recipeapp.domain.Recipe;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, IngredientToIngredientCommand ingredientConverter, CategoryToCategoryCommand categoryConverter) {
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {

        if (recipe != null) {
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

            if (recipe.getNotes() != null) {
                recipeCommand.setNotes(notesConverter.convert(recipe.getNotes()));
            }

            if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
                recipe.getCategories()
                        .forEach(category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
            }

            if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
                recipe.getIngredients()
                        .forEach(ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));
            }

            return recipeCommand;
        }

        return null;
    }
}
