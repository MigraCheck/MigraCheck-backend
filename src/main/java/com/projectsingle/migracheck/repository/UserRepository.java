package com.projectsingle.migracheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projectsingle.migracheck.entity.User;
public interface UserRepository extends JpaRepository<User, Integer>{  
}
