package com.findork.chiriezerie.util;

import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.model.daos.UserDao;

public class UserTranslator {
    
    public static User translate(UserDao userDao) {
        return User.builder()
                .id(userDao.getId())
                .email(userDao.getEmail())
                .name(userDao.getName())
                .username(userDao.getUsername())
                .phoneNumber(userDao.getPhoneNumber())
                .roles(userDao.getRoles())
                .profilePicture(userDao.getProfilePicture())
                .build();
    }
}
