package com.assesment.Multisig.service;

import com.assesment.Multisig.dto.LoginDto;
import com.assesment.Multisig.model.User;
import com.assesment.Multisig.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String validateUser(LoginDto loginRequest) {
        User user = userRepository.fetchUserById(loginRequest.getUserId());
        String encryptedPassword = encryptPassword(loginRequest.getPassword());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.matches(loginRequest.getPassword(),user.getPassword()))
        {
            return "valid login";
        }
        else {
            return "invalid login";
        }

    }


    public String encryptPassword(String password) {
        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength,new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        return encodedPassword;
    }
}
