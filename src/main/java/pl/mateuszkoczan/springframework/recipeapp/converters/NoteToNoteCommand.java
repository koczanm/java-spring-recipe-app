package pl.mateuszkoczan.springframework.recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.mateuszkoczan.springframework.recipeapp.commands.NoteCommand;
import pl.mateuszkoczan.springframework.recipeapp.domains.Note;

@Component
public class NoteToNoteCommand implements Converter<Note, NoteCommand> {

    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Note note) {
        if (note == null) {
            return null;
        }

        final NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(note.getId());
        noteCommand.setRecipeNote(note.getRecipeNote());

        return noteCommand;
    }
}
