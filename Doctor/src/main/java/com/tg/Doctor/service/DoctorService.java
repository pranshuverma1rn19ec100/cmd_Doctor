package com.tg.Doctor.service;

import java.util.List;
import java.util.Set;

import com.tg.Doctor.models.Appointment;
import com.tg.Doctor.models.Doctor;

public interface DoctorService {

	List<Doctor> getAllDoctors();

	Doctor addDoctor(Doctor doctor);

	Doctor viewDoctorId(String doctorId);

	boolean scheduleAppointment(String doctorId, String appointmentTime);

	boolean editAppointment(String doctorId, String oldAppointmentTime, String newAppointmentTime);

	boolean cancelAppointment(String doctorId, String appointmentTime);

	List<String> viewAllAppointments(String doctorId);

	List<Appointment> viewSchedule(String doctorId);

}
