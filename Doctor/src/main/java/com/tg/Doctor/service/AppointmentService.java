package com.tg.Doctor.service;

import java.util.List; // Importing List for handling collections
import java.util.Map; // Importing Map for key-value pair data structure
import java.util.Set; // Importing Set for handling collections

import com.tg.Doctor.models.Appointment; // Importing Appointment model

// Service interface for managing appointments
public interface AppointmentService {

    // Method to schedule an appointment for a doctor at a specified time
	boolean scheduleAppointment(String doctorId, String appointmentTime);

    // Method to cancel an appointment for a doctor at a specified time
	boolean cancelAppointment(String doctorId, String appointmentTime);

    // Method to edit an appointment for a doctor from oldAppointmentTime to newAppointmentTime
	boolean editAppointment(String doctorId, String oldAppointmentTime, String newAppointmentTime);

    // Method to check the availability of a doctor at a specified appointment time
	boolean checkDoctorAvailability(String doctorId, String appointmentTime);

    // Method to view all appointments for a specific doctor
	Set<String> viewAllAppointments(String doctorId);

    // Method to view all appointments for all doctors
	Map<String, Set<String>> viewAllAppointmentsOfAllDoctors();

    // Method to view appointments for a specific doctor
	List<Appointment> viewAppointment(String doctorId);
}
