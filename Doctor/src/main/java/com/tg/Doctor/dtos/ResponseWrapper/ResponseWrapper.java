package com.tg.Doctor.dtos.ResponseWrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseWrapper<T> {
	
	// Message associated with the response
	private String message;
	
	// Object encapsulated within the response
	private T object;
	
	// Constructor with object parameter
	public ResponseWrapper(T object) {
		super();
		this.message = "Appointment Created";
		this.object = object;
	}
	
	// Constructor with message parameter
	public ResponseWrapper(String message) {
		super();
		this.message = message;
	}
}
