package com.gemz.dev.controller;

import com.gemz.dev.model.Image;
import com.gemz.dev.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/image")
@CrossOrigin(origins = "${cors.origin}")
public class ImageController {

    @Autowired
    ImageService service;

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("imageFile") MultipartFile file) {
        return new ResponseEntity<>(service.upload(file), HttpStatus.OK);
    }

    @GetMapping(path = {"get/{id}"})
    public ResponseEntity<Image> getImage(@PathVariable long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }


}
