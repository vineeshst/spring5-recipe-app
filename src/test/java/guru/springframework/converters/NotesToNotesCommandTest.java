package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {
    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTES = "recipe";
    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void testNullObject() {
        converter.convert(null);
    }

    @Test
    void testEmptyObject() {
        converter.convert(new Notes());
    }

    @Test
    void convert() {
        //Given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //When
        NotesCommand notesCommand = converter.convert(notes);

        //Then
        assertEquals(ID_VALUE, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}