package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
    private final UnitOfMeasureCommandToUnitOfMeasure uomconverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomconverter) {
        this.uomconverter = uomconverter;
    }

    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source==null)
        return null;
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setDescription(source.getDescription());
        ingredient.setAmount(source.getAmount());
        ingredient.setUom(uomconverter.convert(source.getUnitOfMeasure()));
        return ingredient;
    }
}
