package com.neimerc.spring5recipeapp.converters;

import com.neimerc.spring5recipeapp.commands.IngredientCommand;
import com.neimerc.spring5recipeapp.domain.Ingredient;
import com.neimerc.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    public static final Long ID = 1L;
    public static final String DESCRIPTION = "description";
    public static final BigDecimal AMOUNT = new BigDecimal(0.5);
    public static final Long ID_UOM = 2L;

    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convertWithUom() throws Exception {
        // given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID_UOM);
        ingredient.setUom(uom);

        // when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        // then
        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUom());
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(ID_UOM, ingredientCommand.getUom().getId());
    }

    @Test
    void convertNullUom() throws Exception {
        // given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUom(null);

        // when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        // then
        assertNotNull(ingredientCommand);
        assertNull(ingredientCommand.getUom());
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
    }
}