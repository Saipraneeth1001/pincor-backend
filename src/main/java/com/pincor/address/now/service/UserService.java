package com.pincor.address.now.service;

import com.pincor.address.now.entity.User;
import com.pincor.address.now.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public void register(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRoles(List.of("USER"));
        userRepository.createUser(user);
    }

    public User findUserByUserName(String username) {
        return userRepository.loadUserByUserName(username);
    }
}
