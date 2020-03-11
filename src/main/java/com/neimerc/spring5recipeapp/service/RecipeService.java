package com.neimerc.spring5recipeapp.service;

import com.neimerc.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    public Set<Recipe> getRecipes();
}
