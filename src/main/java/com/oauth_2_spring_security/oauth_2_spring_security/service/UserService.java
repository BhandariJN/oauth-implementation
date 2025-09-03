package com.oauth_2_spring_security.oauth_2_spring_security.service;

import com.oauth_2_spring_security.oauth_2_spring_security.model.User;
import com.oauth_2_spring_security.oauth_2_spring_security.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    public final UserRepo userRepo;
    public User createUser(OAuth2User oAuth2User){

        User user = userRepo.findByEmail(oAuth2User.getAttribute("email"));
        if(user != null){
            throw new RuntimeException("User already exists");
        }
        user = new User();
        user.setName(oAuth2User.getAttribute("name"));
        user.setEmail(oAuth2User.getAttribute("email"));
        System.out.println(userRepo.save(user));
        return user;
    }
}
