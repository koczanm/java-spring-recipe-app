package pl.mateuszkoczan.springframework.recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.mateuszkoczan.springframework.recipeapp.commands.NoteCommand;
import pl.mateuszkoczan.springframework.recipeapp.domains.Note;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand noteCommand) {
        if (noteCommand == null) {
            return null;
        }

        final Note note = new Note();
        note.setId(noteCommand.getId());
        note.setRecipeNote(noteCommand.getRecipeNote());

        return note;
    }
}
