package com.mk.SpringBootProject_1.Service;


import com.mk.SpringBootProject_1.Entity.Users;
import com.mk.SpringBootProject_1.Repository.userRepo;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class userService {

    @Autowired
    private userRepo userRepository;

    public void save(Users user) {
        user.setUsername(user.getUsername().trim());
        userRepository.save(user);
    }

    public List<Users> getAll() {
        return userRepository.findAll();
    }

    public Optional<Users> findById(ObjectId id) {
        return userRepository.findById(id);
    }
    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public Users findByUsername(String username) {
        Users user = userRepository.findByUsername(username.trim());
        if (user == null) {
            System.out.println("User with username " + username + " not found");
        }
        return user;
    }


}
