package com.neimerc.spring5recipeapp.converters;

import com.neimerc.spring5recipeapp.commands.NotesCommands;
import com.neimerc.spring5recipeapp.domain.Notes;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommands> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommands convert(Notes notes) {

        if (notes != null) {
            final NotesCommands notesCommands = new NotesCommands();
            notesCommands.setId(notes.getId());
            notesCommands.setRecipeNotes(notes.getRecipeNotes());
            return notesCommands;
        }

        return null;
    }
}
