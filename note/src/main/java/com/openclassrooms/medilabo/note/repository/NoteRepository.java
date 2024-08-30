package com.openclassrooms.medilabo.note.repository;

import com.openclassrooms.medilabo.note.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    /**
     * Retrieves a list of notes associated with a specific patient ID
     *
     * @param patId The ID of the patient whose notes are to be retrieved
     * @return A list of `Note`
     */
    public List<Note> findByPatId(String patId);

}
