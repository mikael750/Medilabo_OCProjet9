package com.openclassrooms.medilabo.note.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("notesTest")
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    private String id;
    private String patId;
    private String patient;
    @Indexed(unique = true)
    private String note;

}
