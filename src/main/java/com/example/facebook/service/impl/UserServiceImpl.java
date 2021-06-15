package com.example.facebook.service.impl;

import com.example.facebook.dto.RegisterDTO;
import com.example.facebook.dto.UpdatePasswordDTO;
import com.example.facebook.entity.Image;
import com.example.facebook.entity.Profile;
import com.example.facebook.entity.Role;
import com.example.facebook.entity.User;
import com.example.facebook.exception.*;
import com.example.facebook.repository.ImageRepository;
import com.example.facebook.repository.ProfileRepository;
import com.example.facebook.repository.UserRepository;
import com.example.facebook.service.contract.RoleService;
import com.example.facebook.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ImageRepository imageRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProfileRepository profileRepository, ImageRepository imageRepository, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.imageRepository = imageRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(RegisterDTO registerDTO) throws NameIsNotProvided, NotOldEnough, PasswordIsNotProvided, PasswordsDoNotMatch, AccountEmailException {
        if(registerDTO.getUsername() == null || registerDTO.getUsername().isEmpty() ){
            throw new NameIsNotProvided("Full name is not provided");
        }

        if (userRepository.findFirstByEmail(registerDTO.getEmail()).isPresent() ){
            throw new AccountEmailException("Account with this email is already exists!");
        }

        if(registerDTO.getYears() < 14){
            throw new NotOldEnough("You are not old enough!");
        }

        if(registerDTO.getPassword() == null || registerDTO.getPassword().isEmpty()){
            throw new PasswordIsNotProvided("Password is not provided");
        }

        if(registerDTO.getPasswordRepeat() == null || !registerDTO.getPassword().equals(registerDTO.getPasswordRepeat())){
            throw new PasswordsDoNotMatch("Passwords do not match!");
        }

        User user = new User();
        Profile profile = new Profile();
        Image img = new Image();
        Set<Role> roles = new HashSet<>();

        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        img.setUrl("https://i.imgur.com/T1AMAhF.png");

        profile.setUserFullName(registerDTO.getUsername());
        profile.setFullNamePublic(true);
        profile.setImage(img);
        user.setProfile(profile);

        LocalDate date = LocalDate.now();
        user.setRegisterDate(date);
        user.setActive(false);

        roles.add(roleService.getUserRole());
        user.setRoles(roles);

        imageRepository.save(img);
        profileRepository.save(profile);
        userRepository.save(user);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found, with email: " + email));
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id).get();
    }


    @Override
    public User updateUserPassword(User user, UpdatePasswordDTO updatePasswordDTO) throws PasswordIsNotProvided, PasswordsDoNotMatch {

        if(updatePasswordDTO.getNewPassword() == null || updatePasswordDTO.getNewPassword().isEmpty()){
            throw new PasswordIsNotProvided("New password is not provided");
        }

        if(updatePasswordDTO.getPasswordRepeat().isEmpty() || !updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getPasswordRepeat())){
            throw new PasswordsDoNotMatch("New passwords do not match!");
        }

        if(passwordEncoder.matches(updatePasswordDTO.getOldPassword(), user.getPassword()) &&
                updatePasswordDTO.getEmail().equals(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(updatePasswordDTO.getNewPassword()));
            userRepository.save(user);
        }
        else throw new UserNotFoundException("Entered email or password do not match with yours.");

        return user;
    }

















    //new

    @Override
    public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {

        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new EmailNotFound("There is no such user with this email " + email));

        user.setResetPasswordToken(token);
        userRepository.save(user);
    }

    @Override
    public User get(String resetPasswordToken) {

        return userRepository.findByResetPasswordToken(resetPasswordToken);
    }

//    @Override
//    public void updatePassword(User user, String newPassword) {
//
//        user.setPassword(passwordEncoder.encode(newPassword));
//        userRepository.save(user);
//    }

}
