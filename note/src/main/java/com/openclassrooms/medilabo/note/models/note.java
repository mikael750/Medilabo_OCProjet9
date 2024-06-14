package com.openclassrooms.medilabo.note.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("notes")
@AllArgsConstructor
@NoArgsConstructor
public class note {

    @Id
    private String patId;
    private String patient;
    private String note;

}
