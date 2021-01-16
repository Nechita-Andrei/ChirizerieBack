package com.findork.chiriezerie.controller;

import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.model.daos.UserDao;
import com.findork.chiriezerie.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @GetMapping("/user")
    public UserDao getCurrentUser(User user) {
        log.info("Getting current user");
        return userService.getCurrentUser(user);
    }

    @GetMapping("/users/{userId}")
    public UserDao getUserById(@PathVariable Long userId) {
        log.info("Getting user by userId={}", userId);
        return userService.getUserById(userId);
    }
    
    @PutMapping("/users/picture")
    public UserDao updateProfilePicture(@RequestBody UserDao user) {
        log.info("Updating profile picture for userId={}", user.getId());
        return userService.updateProfilePicture(user);
    }
}
