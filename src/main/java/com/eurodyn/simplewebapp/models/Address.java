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

    @Size(max = 250, message = "Address must be 250 characters max.")
    private String workAddress;

    @Size(max = 250, message = "Address must be 250 characters max.")
    private String homeAddress;
}
