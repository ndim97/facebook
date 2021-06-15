package com.example.facebook.service.impl;

import com.example.facebook.entity.Image;
import com.example.facebook.entity.Profile;
import com.example.facebook.entity.User;
import com.example.facebook.repository.ImageRepository;
import com.example.facebook.repository.ProfileRepository;
import com.example.facebook.repository.UserRepository;
import com.example.facebook.service.contract.ImageService;
import com.example.facebook.service.contract.ProfileService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    public ProfileServiceImpl(UserRepository userRepository, ProfileRepository profileRepository, ImageRepository imageRepository, ImageService imageService) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.imageRepository = imageRepository;
        this.imageService = imageService;
    }

    @Override
    public Profile updateProfile(User user, Profile profile, MultipartFile image) throws IOException {

        Profile updateProfile = user.getProfile();

        imageService.updateAvatarImage(updateProfile, image);

        updateProfile.setUserFullName(profile.getUserFullName());
        updateProfile.setFullNamePublic(profile.getFullNamePublic());
        updateProfile.setOtherInfo(profile.getOtherInfo());
        profileRepository.save(updateProfile);

        return updateProfile;
    }

    @Override
    public Profile getProfileById(Long id) {
        return profileRepository.getById(id);
    }

    @Override
    public Profile getUserProfile(User user){
        Profile profile = user.getProfile();
        return profile;
    }


}
