package com.projectsingle.migracheck.service;

import org.springframework.stereotype.Service;
import com.projectsingle.migracheck.entity.User;
import com.projectsingle.migracheck.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user){      
        return userRepository.save(user);
    }

    @Override
    public User getUserById (int id){
        return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("El usuario no existe"));
    }
}
