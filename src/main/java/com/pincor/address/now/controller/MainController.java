package com.pincor.address.now.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class MainController {

    @GetMapping("/get")
    public String get() {
        String userName = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal().toString();
        return "currently logged in user is: "+userName;
    }
}
