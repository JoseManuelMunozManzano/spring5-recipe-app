package com.neimerc.spring5recipeapp.repositories;

import com.neimerc.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
