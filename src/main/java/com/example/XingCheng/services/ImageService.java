package com.example.XingCheng.services;

import com.example.XingCheng.data.entities.Address;
import com.example.XingCheng.data.entities.Image;
import com.example.XingCheng.repositories.AddressRepository;
import com.example.XingCheng.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    public List<Image> getImages(){
        return imageRepository.findAll();
    }

}
