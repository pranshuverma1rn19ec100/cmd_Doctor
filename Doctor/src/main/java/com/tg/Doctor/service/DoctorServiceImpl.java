package com.tg.Doctor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tg.Doctor.exceptions.DoctorAppointmentException;
import com.tg.Doctor.models.Appointment;
import com.tg.Doctor.models.Doctor;

import com.tg.Doctor.repositories.DoctorRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private AppointmentServiceImpl appointmentService;

	@Override
	public List<Doctor> getAllDoctors() {
		log.info("Getting all doctors...");
		try {
			return doctorRepository.findAll();

		} catch (Exception ex) {
			log.error("Error adding doctor: {}", ex.getMessage(), ex);

			throw new DoctorAppointmentException("Error getting all doctors", ex);
		}
	}

	@Override
	public Doctor addDoctor(Doctor doctor) {
		log.info("Adding a new doctor...");
		try {
			return doctorRepository.save(doctor);
		} catch (Exception ex) {
			log.error("Error getting all doctors: {}", ex.getMessage(), ex);

			throw new DoctorAppointmentException("Error getting all doctors", ex);
		}
	}

	@Override
	public Doctor viewDoctorId(String doctorId) {
		try {
			log.info("Viewing doctor by ID: {}", doctorId);

			return doctorRepository.findById(doctorId).orElse(null);
		} catch (Exception ex) {
			log.error("Error viewing doctor by ID: {}", ex.getMessage(), ex);

			throw new DoctorAppointmentException("Error getting all doctors", ex);
		}
	}

	@Override
	public boolean scheduleAppointment(String doctorId, String appointmentTime) {
		log.info("Scheduling appointment for doctor ID: {}, time: {}", doctorId, appointmentTime);

		try {
			return appointmentService.scheduleAppointment(doctorId, appointmentTime);
		}

		catch (Exception ex) {
			log.error("Error scheduling appointment: {}", ex.getMessage(), ex);

			throw new DoctorAppointmentException("Error getting all doctors", ex);
		}
	}

	@Override
	public boolean editAppointment(String doctorId, String oldAppointmentTime, String newAppointmentTime) {
		log.info("Editing appointment for doctor ID: {}, old time: {}, new time: {}", doctorId, oldAppointmentTime,
				newAppointmentTime);

		try {
			return appointmentService.editAppointment(doctorId, oldAppointmentTime, newAppointmentTime);

		} catch (Exception ex) {
			log.error("Error editing appointment: {}", ex.getMessage(), ex);

			throw new DoctorAppointmentException("Error getting all doctors", ex);
		}
	}

	@Override
	public boolean cancelAppointment(String doctorId, String appointmentTime) {
		log.info("Cancelling appointment for doctor ID: {}, time: {}", doctorId, appointmentTime);

		try {
			return appointmentService.cancelAppointment(doctorId, appointmentTime);
		} catch (Exception ex) {
			log.error("Error cancelling appointment: {}", ex.getMessage(), ex);

			throw new DoctorAppointmentException("Error getting all doctors", ex);
		}
	}

	@Override
	public List<String> viewAllAppointments(String doctorId) {
		log.info("Viewing all appointments for doctor ID: {}", doctorId);

		try {
			Set<String> appointmentsSet = appointmentService.viewAllAppointments(doctorId);
			return new ArrayList<>(appointmentsSet);
		} catch (Exception ex) {
			log.error("Error viewing all appointments: {}", ex.getMessage(), ex);

			throw new DoctorAppointmentException("Error getting all doctors", ex);
		}
	}

	@Override
	public List<Appointment> viewSchedule(String doctorId) {
		return appointmentService.viewAppointment(doctorId);
	}
}
