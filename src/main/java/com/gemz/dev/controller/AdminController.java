package com.gemz.dev.controller;

import com.gemz.dev.model.Admin;
import com.gemz.dev.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin(value = "${cors.origin}")
public class AdminController {

    @Autowired
    private AdminService service;

    @GetMapping("")
    public ResponseEntity<List<Admin>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> update(@PathVariable Long id, @RequestBody Admin admin) {
        admin.setId(id);
        return new ResponseEntity<>(service.add(admin), HttpStatus.OK);
    }

}
