package com.example.facebook.service.contract;

import com.example.facebook.dto.RegisterDTO;
import com.example.facebook.dto.UpdatePasswordDTO;
import com.example.facebook.entity.User;
import com.example.facebook.exception.*;

public interface UserService {

    User register(RegisterDTO registerDTO) throws NameIsNotProvided, NotOldEnough, PasswordIsNotProvided, PasswordsDoNotMatch, AccountEmailException;

    public User getUserById(Long id);

    public User updateUserPassword(User user, UpdatePasswordDTO updatePasswordDTO) throws PasswordIsNotProvided, PasswordsDoNotMatch;


    //new

    public void updateResetPasswordToken(String token, String email);

    public User get(String resetPasswordToken);

}
