package com.Birhan.springboot.registrationlogindemo.security;

import com.Birhan.springboot.registrationlogindemo.entity.Role;
import com.Birhan.springboot.registrationlogindemo.entity.User;
import com.Birhan.springboot.registrationlogindemo.repository.UserRepositery;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomUserDetailsService implements userdetailsService {
    private UserRepositery userRepositery;
    public CustomUserDetailsService(UserRepositery userRepositery){
        this.userRepositery = userRepositery;

    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = UserRepositery.findByEmail(email);

        if (user != null){
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(), mapRolesToAuthorities(user.getRole()));
        }
        else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }

    }
    private Collection <? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        Collection<?extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(collectors.toList());
        return mapRoles;
    }

}
