package com.gemz.dev.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull(message = "title cannot be null")
    @NotBlank(message = "title is required")
    private String title;
    private String description;
    @NotNull(message = "Url cannot be null")
    @NotBlank(message = "Url is required")
    private String url;
    @OneToOne(fetch = FetchType.EAGER)
    private Image image;


}
