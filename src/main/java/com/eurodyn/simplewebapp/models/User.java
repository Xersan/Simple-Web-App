package com.eurodyn.simplewebapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "No name provided.")
    @Size(min = 2, max = 250, message = "Name must be between 2 and 250 characters.")
    private String name;
    @NotNull(message = "No surname provided.")
    @Size(min = 2, max = 250, message = "Surname must be between 2 and 250 characters.")
    private String surname;
    @NotNull(message = "No gender provided.")
    private Character gender;
    @NotNull(message = "No date provided.")
    @Size(max = 250, message = "Date error: Too many characters.")
    private String date;
    private String workAddress;
    private String homeAddress;

}
