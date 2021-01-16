package com.findork.chiriezerie.service.implementation;

import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.model.daos.UserDao;
import com.findork.chiriezerie.repository.UserRepository;
import com.findork.chiriezerie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    @Override
    public UserDao getCurrentUser(User user) {
        return new UserDao(userRepository.getOne(user.getId()));
    }

    @Override
    public UserDao getUserById(Long userId) {
        return new UserDao(userRepository.getOne(userId));
    }

    @Override
    public UserDao updateProfilePicture(UserDao userDao) {
        User user = userRepository.findById(userDao.getId()).get();
        user.setProfilePicture(userDao.getProfilePicture());
        userRepository.save(user);
        return new UserDao(user);
    }
}
