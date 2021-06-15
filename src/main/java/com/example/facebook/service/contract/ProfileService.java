package com.example.facebook.service.contract;

import com.example.facebook.dto.ImageDTO;
import com.example.facebook.dto.ProfileDTO;
import com.example.facebook.entity.Image;
import com.example.facebook.entity.Profile;
import com.example.facebook.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfileService {

    public Profile updateProfile(User user, Profile profile, MultipartFile image) throws IOException;

    public Profile getProfileById(Long id);

    public Profile getUserProfile(User user);
}
