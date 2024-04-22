package com.tg.Doctor.exceptions;

public class DoctorAppointmentException extends RuntimeException {

	public DoctorAppointmentException(String message) {
        super(message);
    }

    public DoctorAppointmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
