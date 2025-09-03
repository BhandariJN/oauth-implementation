package com.oauth_2_spring_security.oauth_2_spring_security.service;

import com.oauth_2_spring_security.oauth_2_spring_security.model.User;
import com.oauth_2_spring_security.oauth_2_spring_security.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private  final UserRepo userRepo;



    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest){
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        User user = userRepo.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setName(oAuth2User.getAttribute("name"));
            user.setEmail(email);
            User saved= userRepo.save(user);
            System.out.println(saved);
        }
        return oAuth2User;
    }


}
