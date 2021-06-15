package com.example.facebook.controller;

import com.example.facebook.dto.UpdatePasswordDTO;
import com.example.facebook.entity.Image;
import com.example.facebook.entity.Post;
import com.example.facebook.entity.Profile;
import com.example.facebook.entity.User;
import com.example.facebook.exception.EmptyPostException;
import com.example.facebook.exception.PasswordIsNotProvided;
import com.example.facebook.exception.PasswordsDoNotMatch;
import com.example.facebook.repository.ImageRepository;
import com.example.facebook.service.contract.ImageService;
import com.example.facebook.service.contract.PostService;
import com.example.facebook.service.contract.ProfileService;
import com.example.facebook.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class LoggedUserController extends BaseController{

    private final ProfileService profileService;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public LoggedUserController(ProfileService profileService, ImageRepository imageRepository, ImageService imageService, UserService userService, PostService postService) {
        this.profileService = profileService;
        this.imageRepository = imageRepository;
        this.imageService = imageService;
        this.userService = userService;
        this.postService = postService;
    }

//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/profile")
//    public ModelAndView viewProfile() {
//        return send("profile");
//    }
//

//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/profile")
//    public ModelAndView viewProfile(@AuthenticationPrincipal User user) {
//        return send("profile", "profile", profileService.getUserProfile(user));
//    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ModelAndView viewProfile(@AuthenticationPrincipal User user) {
        return send("profile", "profile", profileService.getUserProfile(user),
                "image", imageService.getAvatarImage(user.getProfile()),
                "user", user,
                "posts", postService.getUserPost(user));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit/profile")
    public ModelAndView updateProfile(@AuthenticationPrincipal User user){
        return send("edit-profile", "profile", profileService.getUserProfile(user),
                "image", imageService.getAvatarImage(user.getProfile()));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/profile")
    public ModelAndView updateProfile(@AuthenticationPrincipal User user, @ModelAttribute Profile profile, @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
        profileService.updateProfile(user, profile, multipartFile);
        return redirect("/profile");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/change/password")
    public ModelAndView changePassword(){
        return send("change-password");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/change/password")
    public ModelAndView changePassword(@AuthenticationPrincipal User user, @ModelAttribute UpdatePasswordDTO updatePasswordDTO) throws PasswordIsNotProvided, PasswordsDoNotMatch {
        userService.updateUserPassword(user, updatePasswordDTO);
        return redirect("/profile");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/new/post")
    public ModelAndView addNewPost() {
        return send("add-new-post");
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/new/post")
    public ModelAndView addNewPost(@AuthenticationPrincipal User user, @ModelAttribute Post post, @ModelAttribute Image image) throws EmptyPostException {
        postService.addNewPost(user, post, image);
        return redirect("/profile");
    }

}
