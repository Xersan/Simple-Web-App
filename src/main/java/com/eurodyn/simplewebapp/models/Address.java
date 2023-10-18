package com.eurodyn.simplewebapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max = 250, message = "Address must be between 2 and 250 characters.")
    private String workAddress;

    @Size(min = 2, max = 250, message = "Address must be between 2 and 250 characters.")
    private String homeAddress;
}
