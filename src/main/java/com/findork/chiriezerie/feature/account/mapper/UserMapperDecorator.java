package com.findork.chiriezerie.feature.account.mapper;

import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.feature.account.role.RoleService;
import com.findork.chiriezerie.security.payload.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public abstract class UserMapperDecorator implements UserMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public User signUpRequestToUser(SignUpRequest signUpRequest) {
        User user = userMapper.signUpRequestToUser(signUpRequest);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setPhoneNumber(signUpRequest.getPhone());
        user.setRoles(Collections.singleton(roleService.getUserRole(signUpRequest.getRoleName())));
        return user;
    }
}
