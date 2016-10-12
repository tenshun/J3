package com.tenshun.service.user.impl;

import com.tenshun.model.entity.Role;
import com.tenshun.model.entity.User;
import com.tenshun.repository.RoleRepository;
import com.tenshun.repository.UserRepository;
import com.tenshun.service.user.UserService;
import com.tenshun.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceBaseImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(String login, String password, String email) {

        User newUser = new User();
        Role authority = roleRepository.findOne(Constants.USER);
        Set<Role> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(login);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setEmail(email);
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        //log.debug("Created Information for User: {}", newUser);
        return newUser;
    }
}
