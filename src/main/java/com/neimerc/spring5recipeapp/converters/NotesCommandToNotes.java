package com.neimerc.spring5recipeapp.converters;

import com.neimerc.spring5recipeapp.commands.NotesCommands;
import com.neimerc.spring5recipeapp.domain.Notes;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommands, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommands source) {

        if (source != null) {
            final Notes notes = new Notes();
            notes.setId(source.getId());
            notes.setRecipeNotes(source.getRecipeNotes());
            return notes;
        }

        return null;
    }
}
