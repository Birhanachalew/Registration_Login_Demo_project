package com.Birhan.springboot.registrationlogindemo.repository;

import com.Birhan.springboot.registrationlogindemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositery extends JpaRepository<Role, Long> {
     Role findByNmae(String name);
}
