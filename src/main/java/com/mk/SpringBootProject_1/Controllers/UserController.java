package com.mk.SpringBootProject_1.Controllers;

import com.mk.SpringBootProject_1.Entity.Users;
import com.mk.SpringBootProject_1.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

@Autowired
    private userService service;

@GetMapping
public List<Users> getAllUsers(){
    return service.getAll();
}
@PostMapping
public void createUser(@RequestBody Users user){
    service.save(user);
}

@PutMapping("/{username1}")
    public ResponseEntity<?> updateUser(@RequestBody Users user, @PathVariable String username1){
    Users userInDB = service.findByUsername(username1);
    if(userInDB != null){
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
    else {
        user.setUsername(userInDB.getUsername());
        user.setPassword(userInDB.getPassword());
        service.save(userInDB);
        return new ResponseEntity<>(userInDB,HttpStatus.OK);
    }
}
}

