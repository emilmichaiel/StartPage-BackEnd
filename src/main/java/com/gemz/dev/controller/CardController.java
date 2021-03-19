package com.gemz.dev.controller;

import com.gemz.dev.Util.ImageUtil;
import com.gemz.dev.model.Card;
import com.gemz.dev.model.Image;
import com.gemz.dev.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/card")
@CrossOrigin(value = "${cors.origin}")
public class CardController {

    @Autowired
    private CardService service;

    @GetMapping("/getall")
    public ResponseEntity<List<Card>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Card> add(@RequestBody Card card) {
        return new ResponseEntity<>(service.save(card), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Card> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Card> update(@PathVariable Long id, @RequestBody Card card) {
        card.setId(id);
        return new ResponseEntity<>(service.save(card), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Card> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
