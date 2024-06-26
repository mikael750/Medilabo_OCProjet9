package com.openclassrooms.medilabo.patient.models;

import com.openclassrooms.medilabo.patient.DTO.PatientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String prenom;
    private String nom;
    private String dateNaissance;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String adressePostale;
    private String numeroTelephone;

    public Patient(PatientDTO patientDTO) throws ParseException {
        this.prenom = patientDTO.getPrenom();
        this.nom = patientDTO.getNom();
        this.dateNaissance = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").parse(patientDTO.getDateNaissance()));
        this.genre = patientDTO.getGenre();
        this.adressePostale = patientDTO.getAdressePostale();
        this.numeroTelephone = patientDTO.getNumeroTelephone();
    }

}

