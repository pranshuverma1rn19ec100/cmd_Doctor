package com.tg.Doctor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tg.Doctor.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,String>{

}
