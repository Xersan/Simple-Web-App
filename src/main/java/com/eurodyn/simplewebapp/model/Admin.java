package com.eurodyn.simplewebapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 64)
    private String username;

    @NotNull
    @Size(max = 128)
    private String password;

    @NotNull
    @Size(max = 250)
    private String roles;

    @SuppressWarnings("unused")
    public Admin(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Admin() {}

}
