package com.openclassrooms.medilabo.risks.services;

public interface RiskService {

    /**
     * Evaluates the risk of diabetes for a specific patient
     *
     * @param patientId ID of the patient
     * @return A string representing the risk report for the patient
     */
    String evaluateRiskForPatient(int patientId);
}
