package com.example.facebook.service.impl;

import com.example.facebook.entity.Image;
import com.example.facebook.entity.Profile;
import com.example.facebook.repository.ImageRepository;
import com.example.facebook.service.contract.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image updateAvatarImage(Profile profile, MultipartFile multipartFile) throws IOException {

        Image image = profile.getImage();

//        String storageDirectionPath = "https://i.imgur.com/";
//        String url = StringUtils.cleanPath(storageDirectionPath + multipartFile.getOriginalFilename());

        String url = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        image.setUrl(url);

        imageRepository.save(image);

        return image;
    }

    @Override
    public Image getAvatarImage(Profile profile){
        Image image = profile.getImage();
        return image;
    }

    @Override
    public Image getImage(Image imageDTO) {
        Image image = new Image();
        image.setUrl(imageDTO.getUrl());
        imageRepository.save(image);
        return image;
    }


}
