package com.Birhan.springboot.registrationlogindemo.service.impl;

import com.Birhan.springboot.registrationlogindemo.dto.UserDto;
import com.Birhan.springboot.registrationlogindemo.entity.Role;
import com.Birhan.springboot.registrationlogindemo.entity.User;
import com.Birhan.springboot.registrationlogindemo.repository.RoleRepositery;
import com.Birhan.springboot.registrationlogindemo.repository.UserRepositery;
import com.Birhan.springboot.registrationlogindemo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {
    private UserRepositery userRepositery;
    private RoleRepositery roleRepositery;
    private PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepositery userRepositery,  RoleRepositery roleRepositery, PasswordEncoder passwordEncoder){
        this.userRepositery = userRepositery;
        this.roleRepositery =roleRepositery;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void saveUser(UserDto userDto){
        User user = new User();
        User.setName(userDto.getFirstName() + " " + userDto.getLastName());
        User.setEmail(userDto.getEmail());



        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepositery.findByNmae("Role_Admin");

        if(role == null){
            role =checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepositery.save(user);
    }
    @Override
    public User findByEmail(String email){
        return UserRepositery.findByEmail(email);
    }
    @Override
    public List<UserDto> findAllUser(){
        List<User> users = userRepositery.findAll();
        return users.stream().map((user) -> ConvertEntityToDto(user))
                .Collect(Collectors.toList());
    }
    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("Role_Admin");
        return roleRepositery.save(role);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<UserDto> findAllUser() {
        return null;
    }
}
