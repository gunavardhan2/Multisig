package com.assesment.Multisig.controller;

import com.assesment.Multisig.dto.LoginDto;
import com.assesment.Multisig.dto.UserDto;
import com.assesment.Multisig.model.User;
import com.assesment.Multisig.repository.UserRepository;
import com.assesment.Multisig.service.EmailServiceImpl;
import com.assesment.Multisig.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Autowired
    EmailServiceImpl emailService;
    @GetMapping("/all_users")
    public List<Map<Long, String>> getAllUsers()
    {
        System.out.println("fetching all users");
        return userRepository.fetchUsers();

    }

    @PostMapping("/create_user")
    public void createNewUser(@RequestBody UserDto userDto) {
        String encryptedPassword = userService.encryptPassword(userDto.getPassword());
        var modelMapper = new ModelMapper();
        User newUser = modelMapper.map(userDto,User.class);
        newUser.setPassword(encryptedPassword);
        User user = userRepository.save(newUser);
        System.out.println("inserted successfully");
    }

    @PostMapping("/validate_user")
    public String validateUser(@RequestBody LoginDto loginRequest) {
         return userService.validateUser(loginRequest);
    }

}

