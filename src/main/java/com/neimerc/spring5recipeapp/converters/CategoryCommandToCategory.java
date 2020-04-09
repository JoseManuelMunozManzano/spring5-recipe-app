package com.neimerc.spring5recipeapp.converters;

import com.neimerc.spring5recipeapp.commands.CategoryCommand;
import com.neimerc.spring5recipeapp.domain.Category;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {

        if (source != null) {
            final Category category = new Category();
            category.setId(source.getId());
            category.setDescription(source.getDescription());

            return category;
        }

        return null;
    }
}
