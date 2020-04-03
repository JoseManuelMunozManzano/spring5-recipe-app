package com.neimerc.spring5recipeapp.repositories;

import com.neimerc.spring5recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Set<Recipe> findAll();

}
