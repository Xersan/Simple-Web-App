package com.eurodyn.simplewebapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "users")
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
    @Size(min = 10, max = 10, message = "Incorrect date.")
    private String date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_address",
            joinColumns =
                    { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "address_id", referencedColumnName = "id") })
    private Address address;

}
