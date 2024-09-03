package com.openclassrooms.medilabo.patient.controllers;

import com.openclassrooms.medilabo.patient.DTO.PatientDTO;
import com.openclassrooms.medilabo.patient.models.Patient;
import com.openclassrooms.medilabo.patient.services.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/patient")
@Slf4j
public class PatientController {

    @Autowired
    private PatientService patientService;

    /**
     * Retrieves a patient by their ID.
     *
     * @param id ID of the patient
     * @return A `ResponseEntity` containing the `PatientDTO` object if found or a 404 (Not Found) status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable int id) {
        PatientDTO patientDTO = patientService.getPatientById(id);
        if (patientDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
    }

    /**
     * Retrieves all patients
     *
     * @return A `ResponseEntity` containing a list of `PatientDTO` objects or an empty list if no patients are found
     */
    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = patientService.getAllPatients();

        if (patients != null) {
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } else {
            log.error("Method getAllPatient returned null");
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }

    }

    /**
     * Adds a new patient
     *
     * @param patientDTO The `PatientDTO` object containing the details of the patient to be added
     * @return A `ResponseEntity` containing the created `Patient` object and an HTTP status of 201 (Created)
     * @throws ParseException If there is an error parsing the patient data.
     */
    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody PatientDTO patientDTO) throws ParseException {

        return new ResponseEntity<>(patientService.savePatient(patientDTO), HttpStatus.CREATED);

    }

    /**
     * Updates an existing patient.
     *
     * @param patientDTO The `PatientDTO` object containing the updated details of the patient
     * @param id ID of the patient
     * @return A `ResponseEntity` containing the updated `Patient` object and an HTTP status of 200 (OK)
     * @throws ParseException If there is an error parsing the patient data
     */
    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    public ResponseEntity<Patient> updatePatient(@RequestBody PatientDTO patientDTO, @PathVariable("id") int id) throws ParseException {

        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO,patient);
        patient.setId(id);
        return new ResponseEntity<>(patientService.updatePatient(patient), HttpStatus.OK);

    }

    /**
     * Deletes a patient by their ID
     *
     * @param id ID of the patient
     */
    @DeleteMapping("/{id}/delete")
    public void deletePatient(@PathVariable("id") int id) {

        patientService.deletePatient(id);

    }
}
