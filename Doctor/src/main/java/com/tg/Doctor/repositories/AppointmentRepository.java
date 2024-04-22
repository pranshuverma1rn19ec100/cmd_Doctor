package com.tg.Doctor.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tg.Doctor.models.Appointment;

// JpaRepository for managing Appointment entities
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    
    // Method to find appointments by doctorId
    List<Appointment> findByDoctorId(String doctorId);
}
