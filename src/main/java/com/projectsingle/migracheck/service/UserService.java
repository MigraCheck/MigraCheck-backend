package com.projectsingle.migracheck.service;

import com.projectsingle.migracheck.entity.User;
public interface UserService {
    
    public User createUser(User user);

    public User getUserById(int id);
}
