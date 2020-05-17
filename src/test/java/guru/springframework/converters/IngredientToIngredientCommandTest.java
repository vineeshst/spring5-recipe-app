package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {
    private static final Long ID_VALUE = 1L;
    private static final String DESC = "desc";
    private static final BigDecimal AMOUNT = new BigDecimal(3);
    private static final Long UOM_ID = 1L;
    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        //Given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESC);
        ingredient.setAmount(AMOUNT);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);
        ingredient.setUom(unitOfMeasure);

        //When
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //Then
        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESC, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(UOM_ID, ingredientCommand.getUnitOfMeasure().getId());
    }
    @Test
    void convertWithNullUOM() {
        //Given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESC);
        ingredient.setAmount(AMOUNT);


        //When
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //Then
        assertNotNull(ingredientCommand);
        assertNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESC, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());

    }

}