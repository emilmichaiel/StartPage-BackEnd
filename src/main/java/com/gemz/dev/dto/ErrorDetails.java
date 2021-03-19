package com.gemz.dev.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;

@ToString
@EqualsAndHashCode
public class ErrorDetails {

    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String uri;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Getter
    @Setter
    private Calendar timeStamp;

    public ErrorDetails() {
        this.timeStamp = Calendar.getInstance();
    }

    public ErrorDetails(String message, String uri) {
        this();
        this.message = message;
        this.uri = uri;
    }

}