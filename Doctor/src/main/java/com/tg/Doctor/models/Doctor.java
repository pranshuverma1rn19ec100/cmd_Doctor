package com.tg.Doctor.models;

import java.time.LocalDate; // Importing LocalDate for handling date of birth
import java.util.Date; // Importing Date (deprecated) for potential future use
import java.util.List; // Importing List for handling collections
import java.util.Map; // Importing Map for key-value pair data structure

import org.hibernate.annotations.GenericGenerator; // Importing GenericGenerator for generating unique IDs

import jakarta.persistence.CollectionTable; // Importing CollectionTable for mapping collection tables
import jakarta.persistence.Column; // Importing Column for mapping entity columns
import jakarta.persistence.ElementCollection; // Importing ElementCollection for mapping collections of basic types or embeddable classes
import jakarta.persistence.Entity; // Importing Entity to define a JPA entity
import jakarta.persistence.GeneratedValue; // Importing GeneratedValue for specifying the generation strategy of primary keys
import jakarta.persistence.Id; // Importing Id for specifying the primary key of an entity
import jakarta.persistence.MapKeyColumn; // Importing MapKeyColumn for mapping the key column of a map
import jakarta.persistence.Table; // Importing Table to specify the table name for an entity
import lombok.AllArgsConstructor; // Importing AllArgsConstructor for generating an all-arguments constructor
import lombok.Data; // Importing Data for generating getters, setters, equals, hashCode, and toString
import lombok.NoArgsConstructor; // Importing NoArgsConstructor for generating a no-arguments constructor

@Entity // Specifies that the class is an entity
@AllArgsConstructor // Generates an all-arguments constructor
@Data // Generates getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor // Generates a no-arguments constructor
@Table(name = "Doctor") // Specifies the table name for the entity
public class Doctor {

    @Id // Specifies the primary key of the entity
    @GenericGenerator(name = "doctor_Id", strategy = "com.tg.Doctor.models.IdGenerator") // Specifies the generation strategy for the primary key
    @GeneratedValue(generator = "doctor_Id") // Generates the value for the primary key
    @Column(name = "Doctor_Id") // Specifies the column name for the primary key
    private String doctorId; // Represents the unique identifier for a doctor

    @Column(name = "Doctor_Name") // Specifies the column name for the doctor's name
    private String doctorName; // Represents the name of the doctor

    @Column(name = "Date_Of_Birth") // Specifies the column name for the date of birth
    private LocalDate dateOfBirth; // Represents the date of birth of the doctor

    @Column(name = "Gender") // Specifies the column name for the gender
    private String gender; // Represents the gender of the doctor

    @Column(name = "Status") // Specifies the column name for the status
    private boolean status; // Represents the status of the doctor

    @Column(name = "Clinic_Name") // Specifies the column name for the clinic name
    private String clinicName; // Represents the name of the clinic where the doctor works

    @Column(name = "Mobile_No") // Specifies the column name for the mobile number
    private String mobileNo; // Represents the mobile number of the doctor

    @Column(name = "Email") // Specifies the column name for the email
    private String email; // Represents the email address of the doctor

    @Column(name = "Address") // Specifies the column name for the address
    private String address; // Represents the address of the doctor

    @Column(name = "State") // Specifies the column name for the state
    private String state; // Represents the state where the doctor resides

    @Column(name = "City") // Specifies the column name for the city
    private String city; // Represents the city where the doctor resides

    @Column(name="Specialization") // Specifies the column name for the specialization
    private String specialization; // Represents the specialization of the doctor

    @ElementCollection // Specifies that this property will be mapped to a separate collection table
    @CollectionTable(name = "user_preferences") // Specifies the name of the collection table
    @MapKeyColumn(name = "preference_name") // Specifies the column name for the key of the map
    @Column // Specifies the column for the values of the map
    private Map<Days,List<String>> routine; // Represents the routine preferences of the doctor
}
