package com.oauth_2_spring_security.oauth_2_spring_security.controller;

import com.oauth_2_spring_security.oauth_2_spring_security.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class HomeController {
    private final UserRepo userRepo;



    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {
        System.out.println(principal);

        model.addAttribute("name", userRepo.findAll());
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
