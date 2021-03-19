package com.gemz.dev.service;

import com.gemz.dev.Util.ImageUtil;
import com.gemz.dev.exception.ApiException;
import com.gemz.dev.model.Card;
import com.gemz.dev.model.Image;
import com.gemz.dev.repository.CardRepository;
import com.gemz.dev.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepo;
    @Autowired
    private ImageRepository imageRepo;
    @Autowired
    private ImageUtil imageUtil;

    public List<Card> getAll() {
        List<Card> cards = cardRepo.findAll();
        for (Card card : cards) {
            Image image = new Image(card.getImage().getId(),
                    card.getImage().getName(),
                    card.getImage().getType(),
                    imageUtil.decompressBytes(card.getImage().getBase64Image()));
            card.setImage(image);
        }
        if (cards.isEmpty())
            throw new ApiException("No Cards found", HttpStatus.NO_CONTENT);
        return cards;
    }

    public Card get(Long id) {
        try {
            Card card = cardRepo.findById(id).get();
            Image image = new Image(card.getImage().getId(),
                    card.getImage().getName(),
                    card.getImage().getType(),
                    imageUtil.decompressBytes(card.getImage().getBase64Image()));
            card.setImage(image);
            return card;
        } catch (NoSuchElementException ex) {
            throw new ApiException("Card with Id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }
    }

    public Card save(Card card) {
        Image image = new Image(card.getImage().getId(),
                card.getImage().getName(),
                card.getImage().getType(),
                imageUtil.decompressBytes(card.getImage().getBase64Image()));
        card.setImage(image);
        return cardRepo.save(card);
    }

    public void delete(long id) {
        try {
            Card card = cardRepo.findById(id).get();
            cardRepo.deleteById(id);
            imageRepo.deleteById(card.getImage().getId());
        } catch (NoSuchElementException ex) {
            throw new ApiException("Card with Id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }
    }

}
