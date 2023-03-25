package com.Birhan.springboot.registrationlogindemo.repository;

import com.Birhan.springboot.registrationlogindemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositery extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
