package com.Birhan.springboot.registrationlogindemo.service;

import com.Birhan.springboot.registrationlogindemo.dto.UserDto;
import com.Birhan.springboot.registrationlogindemo.entity.User;

import java.util.List;

public interface UserService{
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUser();
}
