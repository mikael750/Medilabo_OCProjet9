package com.openclassrooms.medilabo.patient.IT;

import com.openclassrooms.medilabo.patient.DTO.PatientDTO;
import com.openclassrooms.medilabo.patient.controllers.PatientController;
import com.openclassrooms.medilabo.patient.models.Genre;
import com.openclassrooms.medilabo.patient.models.Patient;
import com.openclassrooms.medilabo.patient.services.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = PatientController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PatientService patientService;

    @InjectMocks
    PatientController patientController;

    @Test
    public void test_getPatientById() {
        //GIVEN
        int patientId = 1;
        Patient expectedPatient = new Patient(1, "Joker", "Clown", "01-01-2001", Genre.M, "Address", "0612345678");
        PatientDTO expectedPatientDTO = new PatientDTO();
        BeanUtils.copyProperties(expectedPatient, expectedPatientDTO);

        when(patientService.getPatientById(patientId)).thenReturn(expectedPatientDTO);

        //WHEN
        ResponseEntity<PatientDTO> response = patientController.getPatientById(patientId);

        //THEN
        assertEquals(expectedPatientDTO, response.getBody());
        verify(patientService, times(1)).getPatientById(patientId);
    }

    @Test
    public void test_getAllPatients() {

        //GIVEN
        List<Patient> expectedPatients = Arrays.asList(
                new Patient(1, "Joker", "Clown", "01-01-2001", Genre.M, "Address", "0612345678"),
                new Patient(2, "Harley", "Queen", "02-02-2002", Genre.F, "Address", "0912345678")
        );
        List<PatientDTO> expectedPatientsDTO = expectedPatients.stream()
                .map(patient -> {
                    PatientDTO patientDTO = new PatientDTO();
                    BeanUtils.copyProperties(patient, patientDTO);
                    return patientDTO;
                }).toList();
        when(patientService.getAllPatients()).thenReturn(expectedPatientsDTO);

        //WHEN
        ResponseEntity<List<PatientDTO>> response = patientController.getAllPatients();


        //THEN
        assertEquals(expectedPatientsDTO, response.getBody());
        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    public void test_addPatient() throws ParseException {
        //GIVEN
        Patient addedPatient = new Patient(1, "Joker", "Clown", "01-01-2001", Genre.M, "Address", "0612345678");
        PatientDTO addedPatientDTO = new PatientDTO();
        BeanUtils.copyProperties(addedPatient, addedPatientDTO);

        when(patientService.savePatient(addedPatientDTO)).thenReturn(addedPatient);

        //WHEN
        ResponseEntity<Patient> response = patientController.addPatient(addedPatientDTO);

        //THEN
        assertEquals(addedPatient, response.getBody());
        verify(patientService, times(1)).savePatient(addedPatientDTO);
    }

    @Test
    public void test_updatePatient() throws ParseException {
        //GIVEN
        int newId = 2;
        Patient updatedPatient = new Patient(1, "Joker", "Clown", "01-01-2001", Genre.M, "Address", "0612345678");
        updatedPatient.setId(2);
        when(patientService.updatePatient(updatedPatient)).thenReturn(updatedPatient);

        PatientDTO updatedPatientDTO = new PatientDTO();
        BeanUtils.copyProperties(updatedPatient, updatedPatientDTO);

        //WHEN
        ResponseEntity<Patient> response = patientController.updatePatient(updatedPatientDTO,newId);

        //THEN
        assertEquals(updatedPatient, response.getBody());
        verify(patientService, times(1)).updatePatient(updatedPatient);
    }

    @Test
    public void test_deletePatient() {
        //GIVEN
        int patientId = 1;

        //WHEN
        patientController.deletePatient(patientId);

        //THEN
        verify(patientService, times(1)).deletePatient(patientId);
    }
}
