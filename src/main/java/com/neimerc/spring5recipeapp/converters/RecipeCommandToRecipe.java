package com.neimerc.spring5recipeapp.converters;

import com.neimerc.spring5recipeapp.commands.CategoryCommand;
import com.neimerc.spring5recipeapp.commands.IngredientCommand;
import com.neimerc.spring5recipeapp.commands.RecipeCommand;
import com.neimerc.spring5recipeapp.domain.Recipe;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;
    private final CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }


    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {

        if (source != null) {
            final Recipe recipe = new Recipe();
            recipe.setId(source.getId());
            recipe.setDescription(source.getDescription());
            recipe.setPrepTime(source.getPrepTime());
            recipe.setCookTime(source.getCookTime());
            recipe.setServings(source.getServings());
            recipe.setSource(source.getSource());
            recipe.setUrl(source.getUrl());
            recipe.setDirections(source.getDirections());
            recipe.setDifficulty(source.getDifficulty());

            if (source.getNotes() != null) {
                recipe.setNotes(notesConverter.convert(source.getNotes()));
            }

            if (source.getCategories() != null && source.getCategories().size() > 0) {
                for (CategoryCommand catCommand : source.getCategories()) {
                    recipe.getCategories().add(categoryConverter.convert(catCommand));
                }
            }

            if (source.getIngredients() != null && source.getIngredients().size() > 0) {
                for (IngredientCommand ingCommand : source.getIngredients()) {
                    recipe.getIngredients().add(ingredientConverter.convert(ingCommand));
                }
            }

            return recipe;
        }

        return null;
    }
}
