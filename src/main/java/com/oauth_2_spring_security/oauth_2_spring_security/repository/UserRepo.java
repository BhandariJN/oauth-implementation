package com.oauth_2_spring_security.oauth_2_spring_security.repository;


import com.oauth_2_spring_security.oauth_2_spring_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
