package com.tg.Doctor.service;

import java.util.HashMap; // Importing HashMap for storing doctor appointments
import java.util.HashSet; // Importing HashSet for managing appointment times
import java.util.List; // Importing List for handling collections
import java.util.Map; // Importing Map for key-value pair data structure
import java.util.Set; // Importing Set for handling collections

import org.springframework.beans.factory.annotation.Autowired; // Importing Autowired for dependency injection
import org.springframework.beans.factory.annotation.Value; // Importing Value for injecting property values
import org.springframework.http.ResponseEntity; // Importing ResponseEntity for HTTP responses
import org.springframework.stereotype.Service; // Importing Service annotation to indicate this class as a service
import org.springframework.web.client.RestTemplate; // Importing RestTemplate for making HTTP requests

import com.tg.Doctor.exceptions.DoctorAppointmentException; // Importing DoctorAppointmentException for custom exception handling
import com.tg.Doctor.models.Appointment; // Importing Appointment model
import com.tg.Doctor.models.Doctor; // Importing Doctor model
import com.tg.Doctor.repositories.AppointmentRepository; // Importing AppointmentRepository for data access
import com.tg.Doctor.repositories.DoctorRepository; // Importing DoctorRepository for data access

import lombok.extern.slf4j.Slf4j; // Importing slf4j logging for logging purposes

@Service // Indicates that this class is a service component
@Slf4j // Lombok annotation for generating logging methods
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired // Injects AppointmentRepository instance
	private AppointmentRepository appointmentRepository;

	@Autowired // Injects DoctorRepository instance
	private DoctorRepository doctorRepository;

	private Map<String, Set<String>> doctorAppointments = new HashMap<>(); // Map to store doctor's appointments

	@Override
	public boolean scheduleAppointment(String doctorId, String appointmentTime) {
		log.info("Scheduling appointment for doctor ID: {}, time: {}", doctorId, appointmentTime);
		try {
			if (!doctorAppointments.containsKey(doctorId)) {
				doctorAppointments.put(doctorId, new HashSet<>());
			}
			if (doctorAppointments.get(doctorId).contains(appointmentTime)) {
				return false; // Appointment slot already booked
			} else {
				doctorAppointments.get(doctorId).add(appointmentTime);
				return true; // Appointment scheduled successfully
			}
		} catch (Exception ex) {
			log.error("Error scheduling appointment: {}", ex.getMessage(), ex);
			throw new DoctorAppointmentException("Error scheduling appointment", ex);
		}
	}

	@Override
	public boolean cancelAppointment(String doctorId, String appointmentTime) {
		log.info("Cancelling appointment for doctor ID: {}, time: {}", doctorId, appointmentTime);
		try {
			if (doctorAppointments.containsKey(doctorId)) {
				return doctorAppointments.get(doctorId).remove(appointmentTime);
			}
			return false; // No appointments for the specified doctor
		} catch (Exception ex) {
			log.error("Error cancelling appointment: {}", ex.getMessage(), ex);
			throw new DoctorAppointmentException("Error cancelling appointment", ex);
		}
	}

	@Override
	public boolean editAppointment(String doctorId, String oldAppointmentTime, String newAppointmentTime) {
		log.info("Editing appointment for doctor ID: {}, old time: {}, new time: {}", doctorId, oldAppointmentTime,
				newAppointmentTime);
		try {
			if (doctorAppointments.containsKey(doctorId)) {
				Set<String> appointments = doctorAppointments.get(doctorId);
				if (appointments.contains(oldAppointmentTime)) {
					appointments.remove(oldAppointmentTime);
					appointments.add(newAppointmentTime);
					return true; // Appointment edited successfully
				}
			}
			return false; // Appointment not found or doctor does not exist
		} catch (Exception ex) {
			log.error("Error editing appointment: {}", ex.getMessage(), ex);
			throw new DoctorAppointmentException("Error editing appointment", ex);
		}
	}

	@Override
	public boolean checkDoctorAvailability(String doctorId, String appointmentTime) {
		log.info("Checking doctor availability for doctor ID: {}, time: {}", doctorId, appointmentTime);
		try {
			if (!doctorAppointments.containsKey(doctorId)) {
				doctorAppointments.put(doctorId, new HashSet<>());
			}
			return !doctorAppointments.get(doctorId).contains(appointmentTime);
		} catch (Exception ex) {
			log.error("Error checking doctor availability: {}", ex.getMessage(), ex);
			throw new DoctorAppointmentException("Error checking doctor availability", ex);
		}
	}

	@Override
	public Set<String> viewAllAppointments(String doctorId) {
		log.info("Viewing all appointments for doctor ID: {}", doctorId);
		try {
			return doctorAppointments.getOrDefault(doctorId, new HashSet<>());
		} catch (Exception ex) {
			log.error("Error viewing all appointments: {}", ex.getMessage(), ex);
			throw new DoctorAppointmentException("Error viewing all appointments", ex);
		}
	}

	@Override
	public Map<String, Set<String>> viewAllAppointmentsOfAllDoctors() {
		log.info("Viewing all appointments for all doctors");
		try {
			return new HashMap<>(doctorAppointments);
		} catch (Exception ex) {
			log.error("Error viewing all appointments: {}", ex.getMessage(), ex);
			throw new DoctorAppointmentException("Error viewing all appointments", ex);
		}
	}

	@Override
	public List<Appointment> viewAppointment(String doctorId) {
		return appointmentRepository.findByDoctorId(doctorId);
	}

}
