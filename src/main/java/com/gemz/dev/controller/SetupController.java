package com.gemz.dev.controller;

import com.gemz.dev.dto.SetupStatus;
import com.gemz.dev.model.Admin;
import com.gemz.dev.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/setup")
@CrossOrigin(value = "${cors.origin}")
public class SetupController {

    @Autowired
    private AdminRepository adminRepo;

    @GetMapping("/check")
    public ResponseEntity<SetupStatus> checkStatus() {
        List<Admin> adminList = adminRepo.findAll();
        if (adminList.isEmpty())
            return new ResponseEntity<>(new SetupStatus(true), HttpStatus.OK);
        return new ResponseEntity<>(new SetupStatus(false), HttpStatus.OK);
    }

}
