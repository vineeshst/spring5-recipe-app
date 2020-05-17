package guru.springframework.converters;

import com.sun.istack.Nullable;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    private final UnitOfMeasureToUnitOfMeasureCommand uomCommandConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomCommandConverter) {
        this.uomCommandConverter = uomCommandConverter;
    }

    @Override
    @Nullable
    @Synchronized
    public IngredientCommand convert(Ingredient source) {
        if (source == null)
        return null;
        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(source.getId());
        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setUnitOfMeasure(uomCommandConverter.convert(source.getUom()));
        return ingredientCommand;
    }
}
