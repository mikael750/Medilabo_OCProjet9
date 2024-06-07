package com.openclassrooms.medilabo.note.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
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
