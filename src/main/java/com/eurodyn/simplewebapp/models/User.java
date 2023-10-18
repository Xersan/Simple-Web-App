package com.eurodyn.simplewebapp.models;

import jakarta.persistence.*;
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
    @Size(min = 1, max = 1, message = "Gender must be a single character.")
    private String gender;

    @NotNull(message = "No date provided.")
    @Size(max = 250, message = "Date error: Too many characters.")
    private String date;

}
