package com.tg.Doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tg.Doctor.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
	
	@Autowired
    private AppointmentService appointmentService;

    // Endpoint for scheduling an appointment
    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleAppointment(@RequestParam String doctorId,
                                                      @RequestParam String appointmentTime) {
        try {
            boolean result = appointmentService.scheduleAppointment(doctorId, appointmentTime);
            if (result) {
                return ResponseEntity.ok("Appointment scheduled successfully");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Appointment slot already booked");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error scheduling appointment: " + e.getMessage());
        }
    }

    // Endpoint for cancelling an appointment
    @PostMapping("/cancel")
    public ResponseEntity<String> cancelAppointment(@RequestParam String doctorId,
                                                     @RequestParam String appointmentTime) {
        try {
            boolean result = appointmentService.cancelAppointment(doctorId, appointmentTime);
            if (result) {
                return ResponseEntity.ok("Appointment cancelled successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error cancelling appointment: " + e.getMessage());
        }
    }

    // Endpoint for editing an appointment
    @PostMapping("/edit")
    public ResponseEntity<String> editAppointment(@RequestParam String doctorId,
                                                   @RequestParam String oldAppointmentTime,
                                                   @RequestParam String newAppointmentTime) {
        try {
            boolean result = appointmentService.editAppointment(doctorId, oldAppointmentTime, newAppointmentTime);
            if (result) {
                return ResponseEntity.ok("Appointment edited successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error editing appointment: " + e.getMessage());
        }
    }

    // Endpoint for checking doctor availability at a specific time
    @GetMapping("/checkAvailability")
    public ResponseEntity<Boolean> checkDoctorAvailability(@RequestParam String doctorId,
                                                            @RequestParam String appointmentTime) {
        try {
            boolean available = appointmentService.checkDoctorAvailability(doctorId, appointmentTime);
            return ResponseEntity.ok(available);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }

    // Endpoint for viewing all appointments of a specific doctor
    @GetMapping("/viewAll/{doctorId}")
    public ResponseEntity<?> viewAllAppointments(@PathVariable String doctorId) {
        try {
            return ResponseEntity.ok(appointmentService.viewAllAppointments(doctorId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error viewing appointments: " + e.getMessage());
        }
    }

    // Endpoint for viewing all appointments of all doctors
    @GetMapping("/viewAll")
    public ResponseEntity<?> viewAllAppointmentsOfAllDoctors() {
        try {
            return ResponseEntity.ok(appointmentService.viewAllAppointmentsOfAllDoctors());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error viewing appointments: " + e.getMessage());
        }
    }
}
