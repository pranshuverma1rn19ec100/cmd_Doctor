package com.tg.Doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tg.Doctor.models.Doctor;
import com.tg.Doctor.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	// Endpoint for adding a new doctor
	@PostMapping("/addDoctor")
	public ResponseEntity<String> handleData(@RequestBody Doctor doctor) {
		try {
			doctorService.addDoctor(doctor);
			return ResponseEntity.ok("Data saved successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data: " + e.getMessage());
		}
	}

	// Endpoint for retrieving all doctors
	@GetMapping("/getAllDoctor")
	public ResponseEntity<?> getAllDoctors() {
		try {
			List<Doctor> doctors = doctorService.getAllDoctors();
			return new ResponseEntity<>(doctors, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error fetching doctors: " + e.getMessage());
		}
	}

	// Endpoint for retrieving a doctor by ID
	@GetMapping("/{doctorId}")
	public ResponseEntity<?> getDoctorById(@PathVariable String doctorId) {
		try {
			Doctor doctor = doctorService.viewDoctorId(doctorId);
			if (doctor != null) {
				return new ResponseEntity<>(doctor, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error fetching doctor: " + e.getMessage());
		}
	}
}
