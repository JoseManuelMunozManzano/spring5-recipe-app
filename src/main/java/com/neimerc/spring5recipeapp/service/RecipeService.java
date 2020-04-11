package com.neimerc.spring5recipeapp.service;

import com.neimerc.spring5recipeapp.commands.RecipeCommand;
import com.neimerc.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    public Set<Recipe> getRecipes();

    public Recipe findById(Long l);

    public RecipeCommand findCommandById(Long l);

    public RecipeCommand saveRecipeCommand(RecipeCommand command);

}
