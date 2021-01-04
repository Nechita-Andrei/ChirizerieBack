package com.findork.chiriezerie.feature.account;

import com.findork.chiriezerie.model.daos.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/user")
    public UserDao getCurrentUser(User user) {
        return new UserDao(userRepository.getOne(user.getId()));
    }
    
    @GetMapping("/users/{userId}")
    public UserDao getUserById(@PathVariable Long userId) {
        return new UserDao(userRepository.getOne(userId));
    }
}
