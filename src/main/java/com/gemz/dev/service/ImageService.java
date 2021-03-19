package com.gemz.dev.service;

import com.gemz.dev.Util.ImageUtil;
import com.gemz.dev.exception.ApiException;
import com.gemz.dev.model.Image;
import com.gemz.dev.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;

@Service
public class ImageService {

    @Autowired
    ImageRepository repo;

    @Autowired
    ImageUtil imageUtil;

    public Image upload(MultipartFile file) {
        try {
            Image image = new Image(file.getOriginalFilename(),
                    file.getContentType(),
                    imageUtil.compressBytes(file.getBytes()));
            return repo.save(image);
        } catch (Exception ex) {
            return new Image();
        }
    }

    public Image get(long id) {
        try {
            Image retrievedImage = repo.findById(id).get();
            return new Image(id,
                    retrievedImage.getName(),
                    retrievedImage.getType(),
                    imageUtil.decompressBytes(retrievedImage.getBase64Image()));
        } catch (NoSuchElementException ex) {
            throw new ApiException("Cannot find image with id " + id, HttpStatus.NOT_FOUND);
        }
    }
}
