package guru.springframework.converters;

import com.sun.istack.Nullable;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final IngredientCommandToIngredient ingredientConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter,
                                 CategoryCommandToCategory categoryConverter, NotesCommandToNotes notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Override
    @Nullable
    @Synchronized
    public Recipe convert(RecipeCommand source) {
        if (source == null)
        return null;
        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServing());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setNotes(notesConverter.convert(source.getNotes()));
        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }
        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }
        return recipe;
    }
}
