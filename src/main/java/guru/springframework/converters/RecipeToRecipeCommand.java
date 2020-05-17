package guru.springframework.converters;

import com.sun.istack.Nullable;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final IngredientToIngredientCommand ingrdientConverter;
    private final CategoryToCategoryCommand categoryConverter;
    private final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingrdientConverter,
                                 CategoryToCategoryCommand categoryConverter, NotesToNotesCommand notesConverter) {
        this.ingrdientConverter = ingrdientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Override
    @Nullable
    @Synchronized
    public RecipeCommand convert(Recipe source) {
        if (source == null)
        return null;
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServing(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setNotes(notesConverter.convert(source.getNotes()));
        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach(category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }
        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> recipeCommand.getIngredients().add(ingrdientConverter.convert(ingredient)));
        }
        return recipeCommand;
    }
}
