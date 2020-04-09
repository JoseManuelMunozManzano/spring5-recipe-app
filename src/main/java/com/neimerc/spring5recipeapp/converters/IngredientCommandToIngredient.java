package com.neimerc.spring5recipeapp.converters;

import com.neimerc.spring5recipeapp.commands.IngredientCommand;
import com.neimerc.spring5recipeapp.domain.Ingredient;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {

        if (source != null) {
            final Ingredient ingredient = new Ingredient();
            ingredient.setId(source.getId());
            ingredient.setDescription(source.getDescription());
            ingredient.setAmount(source.getAmount());
            ingredient.setUom(uomConverter.convert(source.getUnitOfMeasure()));
            return ingredient;
        }

        return null;
    }
}
