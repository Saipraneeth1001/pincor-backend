package com.pincor.address.now.controller;

import com.pincor.address.now.entity.User;
import com.pincor.address.now.request.AuthenticationRequest;
import com.pincor.address.now.request.AuthenticationResponse;
import com.pincor.address.now.security.CustomUserDetailsService;
import com.pincor.address.now.service.UserService;
import com.pincor.address.now.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping(value = "/login")
    public ResponseEntity<?> login(
            @RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody AuthenticationRequest request) throws Exception {
        User user = userService.findUserByUserName(request.getUsername());
        if (user != null) {
            throw new Exception("User exists in the database, please login");
        }
        userService.register(request.getUsername(), request.getPassword());
        return new ResponseEntity<>("registration successful please login with your credentials", HttpStatus.OK);
    }
}
