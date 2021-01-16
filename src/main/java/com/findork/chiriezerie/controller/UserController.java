package com.findork.chiriezerie.controller;

import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.model.daos.UserDao;
import com.findork.chiriezerie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @GetMapping("/user")
    public UserDao getCurrentUser(User user) {
        return userService.getCurrentUser(user);
    }

    @GetMapping("/users/{userId}")
    public UserDao getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
    
    @PutMapping("/users/picture")
    public UserDao updateProfilePicture(@RequestBody UserDao user) {
        return userService.updateProfilePicture(user);
    }
}
