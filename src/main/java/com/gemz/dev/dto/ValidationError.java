package com.gemz.dev.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ToString
@EqualsAndHashCode
public class ValidationError {

    @Getter
    @Setter
    private List<String> errors;
    @Getter
    @Setter
    private String uri;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Getter
    @Setter
    private Calendar timeStamp;

    public ValidationError() {
        this.timeStamp = Calendar.getInstance();
        this.errors = new ArrayList<>();
    }

    public ValidationError(List<String> errors, String uri) {
        this();
        this.uri = uri;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

}
