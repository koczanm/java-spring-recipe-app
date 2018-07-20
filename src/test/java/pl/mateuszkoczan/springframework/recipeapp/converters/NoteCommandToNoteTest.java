package pl.mateuszkoczan.springframework.recipeapp.converters;

import org.junit.Before;
import org.junit.Test;
import pl.mateuszkoczan.springframework.recipeapp.commands.NoteCommand;
import pl.mateuszkoczan.springframework.recipeapp.domains.Note;

import static org.junit.Assert.*;

public class NoteCommandToNoteTest {
    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTE = "Note";

    private NoteCommandToNote converter;

    @Before
    public void setUp() throws Exception {
        converter = new NoteCommandToNote();
    }

    @Test
    public void convertNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convertEmptyObject() {
        assertNotNull(converter.convert(new NoteCommand()));
    }

    @Test
    public void convert() {
        NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(ID_VALUE);
        noteCommand.setRecipeNote(RECIPE_NOTE);

        Note note = converter.convert(noteCommand);

        assertNotNull(note);
        assertEquals(ID_VALUE, note.getId());
        assertEquals(RECIPE_NOTE, note.getRecipeNote());
    }
}