package com.auth.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.auth.auth.model.User;
import com.auth.auth.repository.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) throw new RuntimeException("No hay usuarios");
        return users;
    }

    public User getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new RuntimeException("No hay usuario"); 
        return user.get();
    }

    public User getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new RuntimeException("No hay usuario"); 
        return user.get();
    }
}

