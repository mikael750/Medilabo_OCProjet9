package com.openclassrooms.medilabo.patient.services;

import com.openclassrooms.medilabo.patient.DTO.PatientDTO;
import com.openclassrooms.medilabo.patient.models.Patient;

import java.text.ParseException;
import java.util.List;

public interface PatientService {

    /**
     * Retrieves all patients from the repository and converts them to `PatientDTO` objects
     *
     * @return A list of `PatientDTO` objects representing all patients in the repository
     */
    List<PatientDTO> getAllPatients();

    /**
     * Retrieves a patient by their ID and converts it to a `PatientDTO` object.
     *
     * @param id ID of the patient
     * @return A `PatientDTO` object representing the patient with the specified ID, or null if the patient is
     *         not found or the ID is zero.
     */
    PatientDTO getPatientById(int id);

    /**
     * Saves a new patient to the repository
     *
     * @param patientDTO The `PatientDTO` object containing the details of the patient to be saved
     * @return The saved `Patient` entity
     */
    Patient savePatient(PatientDTO patientDTO) throws ParseException;

    /**
     * Updates an existing patient in the repository
     *
     * @param patient The `Patient` entity with updated details
     * @return The updated `Patient` entity
     */
    Patient updatePatient(Patient patient);

    /**
     * Deletes a patient by their ID
     *
     * @param id The ID of the patient to be deleted
     */
    void deletePatient(int id);

}
