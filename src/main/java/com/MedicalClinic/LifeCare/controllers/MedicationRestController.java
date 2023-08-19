package com.MedicalClinic.LifeCare.controllers;

import com.MedicalClinic.LifeCare.entity.Medication;
import com.MedicalClinic.LifeCare.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/medication")
public class MedicationRestController {
    @Autowired
    private MedicationService medicationService;

    @PostMapping("/save")
    public Medication saveMedication(@RequestBody Medication medication) {
        return medicationService.saveMedication(medication);
    }

    @GetMapping("/find/all")
    public List<Medication> fetchMedicationList() {
        return medicationService.fetchMedicationList();
    }

    @GetMapping("/find/patientid/{patient_id}")
    @PreAuthorize("hasRole('USER')")
    public List<Medication> findByPatientPatientId(@PathVariable("patient_id") Long patientid) {
        return medicationService.findByPatientPatientId(patientid);
    }

    @GetMapping("/find/medprofid/{medprof_id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public List<Medication> findByMedicalProfessionalProfessionalId(@PathVariable("medprof_id") Long patientid) {
        return medicationService.findByMedicalProfessionalProfessionalId(patientid);
    }

    @GetMapping("/find/{medprof_id}/{patient_id}/{appointment_id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public List<Medication> findByProfessionalIdAndPatientIdAndAppointmentId(@PathVariable("medprof_id") Long medprofId,
            @PathVariable("patient_id") Long patientId, @PathVariable("appointment_id") Long appointmentId) {
        return medicationService.findByProfessionalIdAndPatientIdAndAppointmentId(medprofId, patientId, appointmentId);
    }

    @GetMapping("/find/{patient_id}/{appointment_id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR')")
    public List<Medication> findByPatientIdAndAppointmentId(@PathVariable("patient_id") Long patientId,
            @PathVariable("appointment_id") Long appointmentId) {
        return medicationService.findByPatientIdAndAppointmentId(patientId, appointmentId);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public String deleteMedicationById(@PathVariable("id") Long medicationId) {
        medicationService.deleteMedicationById(medicationId);
        return "medication Deleted successful";
    }
}
