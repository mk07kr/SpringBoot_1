package com.mk.SpringBootProject_1.Service;



import com.mk.SpringBootProject_1.Entity.Users;
import com.mk.SpringBootProject_1.Repository.userRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class userService {


    @Autowired
    private userRepo userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(Users user) {
        user.setUsername(user.getUsername().trim());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        userRepository.save(user);
    }
    public void saveAdmin(Users user) {
        user.setUsername(user.getUsername().trim());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER", "ADMIN"));
        userRepository.save(user);
    }
        public void save(Users user) {
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
     return  userRepository.findByUsername(username);
    }


}
