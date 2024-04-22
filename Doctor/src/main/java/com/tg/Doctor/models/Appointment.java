package com.tg.Doctor.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "Appointments")
public class Appointment {
	
	// Appointment ID generated using a custom generator
	@Id
	@GenericGenerator(name = "doctor_Id", strategy = "com.tg.Doctor.models.IdGenerator")
	@GeneratedValue(generator = "doctor_Id")
	@Column(name = "Doctor_Id")
	private String doctorId;

	// Name associated with the appointment
	@Column(name = "Name")
	private String Name;
	
	// Day of the appointment
	@Column(name = "day")
    private Days day;
	
	// Start time of the appointment
	@Column(name = "start_time")
	private String startTime;
	    
	// End time of the appointment
	@Column(name = "end_time")
	private String endTime;
	
	// Date and time when the appointment was created
	@Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    // Date and time when the appointment was last modified
    @Column(name = "last_modified_date")
    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;
		
    // Many-to-one relationship with the Doctor entity
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Doctor_Id_FK")
	private Doctor doctor;
}
