package com.tg.Doctor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tg.Doctor.dtos.ResponseWrapper.ResponseWrapper;



@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(DoctorAppointmentException.class)
    public ResponseEntity<ResponseWrapper> handleDoctorAppointmentException(DoctorAppointmentException exception){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setMessage(exception.getMessage());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseWrapper);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper> handleGlobalException(Exception exception){
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setMessage("Internal server error occurred");
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseWrapper);
    }
}
