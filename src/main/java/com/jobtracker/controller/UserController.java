// controller/UserController.java
package com.jobtracker.controller;

import com.jobtracker.dto.LoginRequestDTO;
import com.jobtracker.dto.LoginResponseDTO;
import com.jobtracker.dto.RegisterRequestDTO;
import com.jobtracker.model.User;
import com.jobtracker.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequestDTO requestDTO) {
        return userService.register(requestDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO requestDTO) {
        return userService.login(requestDTO);
    }
}