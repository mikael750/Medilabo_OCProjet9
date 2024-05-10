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

}
