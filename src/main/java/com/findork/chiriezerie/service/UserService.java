package com.findork.chiriezerie.service;

import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.model.daos.UserDao;

public interface UserService {
    
    UserDao getCurrentUser(User user);
    
    UserDao getUserById(Long userId);
    
    UserDao updateProfilePicture(UserDao userDao);
}
