package com.dean.glot_spot.controller;

import com.dean.glot_spot.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email) {
        userService.registerUser(email);
        return "Registration successful! Check the console for the magic link.";
    }
}
