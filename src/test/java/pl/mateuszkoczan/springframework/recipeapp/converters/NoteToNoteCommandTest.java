package pl.mateuszkoczan.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import pl.mateuszkoczan.springframework.recipeapp.commands.NoteCommand;
import pl.mateuszkoczan.springframework.recipeapp.domains.Note;

import static org.junit.Assert.*;

public class NoteToNoteCommandTest {
    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTE = "Note";

    private NoteToNoteCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NoteToNoteCommand();
    }

    @Test
    public void convertNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyObject() {
        assertNotNull(converter.convert(new Note()));
    }

    @Test
    public void convert() {
        Note note = new Note();
        note.setId(ID_VALUE);
        note.setRecipeNote(RECIPE_NOTE);

        NoteCommand noteCommand = converter.convert(note);

        assertEquals(ID_VALUE, noteCommand.getId());
        assertEquals(RECIPE_NOTE, noteCommand.getRecipeNote());
    }
}