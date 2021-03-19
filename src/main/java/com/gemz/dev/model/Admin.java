package com.gemz.dev.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username is required")
    private String username;
    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password is required")
    private String password;
}
