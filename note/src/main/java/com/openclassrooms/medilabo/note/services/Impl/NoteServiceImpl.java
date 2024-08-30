package com.openclassrooms.medilabo.note.services.Impl;

import com.openclassrooms.medilabo.note.models.Note;
import com.openclassrooms.medilabo.note.repository.NoteRepository;
import com.openclassrooms.medilabo.note.services.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    /**
     *{@inheritDoc}
     */
    public List<Note> getAllNote() {

        return noteRepository.findAll();

    }

    /**
     *{@inheritDoc}
     */
    public List<Note> getNotesByPatient(int id) {

        return noteRepository.findByPatId(String.valueOf(id));

    }

    /**
     *{@inheritDoc}
     */
    public Note saveNote(Note note) {

        return noteRepository.save(note);

    }

    /**
     *{@inheritDoc}
     */
    public Note updateNote(Note note) {

        return noteRepository.save(note);

    }

    /**
     *{@inheritDoc}
     */
    public void deleteNote(int id) {

        noteRepository.deleteById(String.valueOf(id));

    }
}
