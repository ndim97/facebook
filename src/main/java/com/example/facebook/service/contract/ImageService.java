package com.example.facebook.service.contract;

import com.example.facebook.entity.Image;
import com.example.facebook.entity.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    public Image updateAvatarImage(Profile profile, MultipartFile multipartFile) throws IOException;

    public Image getAvatarImage(Profile profile);

    public Image getImage(Image imageDTO);
}
