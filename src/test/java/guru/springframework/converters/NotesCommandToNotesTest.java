package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.h2.command.dml.MergeUsing;
import org.hibernate.tool.schema.internal.DefaultSchemaFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {
    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTES = "recipe";
    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void testNullObject() {
        converter.convert(null);
    }

    @Test
    void testEmptyObject() {
        converter.convert(new NotesCommand());
    }

    @Test
    void convert() {
        //Given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //When
        Notes notes = converter.convert(notesCommand);

        //Then
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}