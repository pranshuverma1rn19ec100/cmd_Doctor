package com.tg.Doctor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import com.tg.Doctor.models.Doctor;
import com.tg.Doctor.repositories.DoctorRepository;
import com.tg.Doctor.service.AppointmentService;
import com.tg.Doctor.service.DoctorServiceImpl;


public class DoctorServiceTest {
	
	@InjectMocks
	private DoctorServiceImpl doctorService;
	@Mock
    private DoctorRepository doctorRepository;
	@Mock
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        doctorRepository = mock(DoctorRepository.class);
        appointmentService = mock(AppointmentService.class);
        doctorService = new DoctorServiceImpl(doctorRepository, appointmentService);
    }

    @Test
    void testGetAllDoctors() {
        // Mock data
        Doctor doctor1 = new Doctor("1", "John", LocalDate.of(1980, 1, 1), "Male", true, "Clinic A",
                "1234567890", "john@example.com", "Address", "State", "City", "Specialization", new HashMap<>());
        Doctor doctor2 = new Doctor("2", "Alice", LocalDate.of(1985, 2, 2), "Female", true, "Clinic B",
                "9876543210", "alice@example.com", "Address", "State", "City", "Specialization", new HashMap<>());
        when(doctorRepository.findAll()).thenReturn(List.of(doctor1, doctor2));

        // Test
        assertEquals(2, doctorService.getAllDoctors().size());
    }

    @Test
    void testScheduleAppointment() {
        // Mock data
        String doctorId = "1";
        String appointmentTime = "2024-04-23 10:00 AM";
        when(appointmentService.scheduleAppointment(doctorId, appointmentTime)).thenReturn(true);

        // Test
        assertTrue(doctorService.scheduleAppointment(doctorId, appointmentTime));
    }

    @Test
    void testCancelAppointment() {
        // Mock data
        String doctorId = "1";
        String appointmentTime = "2024-04-23 10:00 AM";
        when(appointmentService.cancelAppointment(doctorId, appointmentTime)).thenReturn(true);

        // Test
        assertTrue(doctorService.cancelAppointment(doctorId, appointmentTime));
    }

    @Test
    void testEditAppointment() {
        // Mock data
        String doctorId = "1";
        String oldAppointmentTime = "2024-04-23 10:00 AM";
        String newAppointmentTime = "2024-04-24 10:00 AM";
        when(appointmentService.editAppointment(doctorId, oldAppointmentTime, newAppointmentTime)).thenReturn(true);

        // Test
        assertTrue(doctorService.editAppointment(doctorId, oldAppointmentTime, newAppointmentTime));
    }

    @Test
    void testViewAllAppointments() {
        // Mock data
        String doctorId = "1";
        Set<String> appointmentsSet = new HashSet<>();
        appointmentsSet.add("2024-04-23 10:00 AM");
        appointmentsSet.add("2024-04-24 11:00 AM");
        when(appointmentService.viewAllAppointments(doctorId)).thenReturn(appointmentsSet);

        // Test
        List<String> appointments = doctorService.viewAllAppointments(doctorId);
        assertEquals(2, appointments.size());
    }

    // Add more test cases as needed
}

}
