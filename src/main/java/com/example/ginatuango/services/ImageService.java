package com.example.ginatuango.services;

import com.example.ginatuango.data.entities.Image;
import com.example.ginatuango.repositories.ImageRepository;
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

    public void deleteImagesBtItemId(int id){
        if(!getImagesByItemId(id).isEmpty()){
            imageRepository.deleteImagesByItemId(id);
        }
    }

    public List<Image> getImagesByItemId(int id){
        return imageRepository.getImagesByItemId(id);
    }


}
