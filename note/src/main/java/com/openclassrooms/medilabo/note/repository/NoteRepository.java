package com.openclassrooms.medilabo.note.repository;

import com.openclassrooms.medilabo.note.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    public List<Note> findByPatient(String patient);

    public List<Note> findByPatId(String patId);

    public List<Note> findByNote(String note);

}
