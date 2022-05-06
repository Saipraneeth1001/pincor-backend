package com.pincor.address.now.security;

import com.pincor.address.now.entity.User;
import com.pincor.address.now.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.loadUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username");
        }
        return new CustomUserDetails(user);
    }
}
