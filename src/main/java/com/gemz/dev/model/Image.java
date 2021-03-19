package com.gemz.dev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Column(length = 16777215)
    private byte[] base64Image;

    public Image(String name, String type, byte[] imgByte) {
        this.name = name;
        this.type = type;
        this.base64Image = imgByte;
    }

    public Image(long id, String name, String type, byte[] imgByte) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.base64Image = imgByte;
    }
}
