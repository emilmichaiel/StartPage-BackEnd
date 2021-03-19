package com.gemz.dev.dto;

import com.gemz.dev.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Jwt {

    private String _token;
    private Admin admin;
}
