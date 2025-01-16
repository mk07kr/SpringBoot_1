package com.mk.SpringBootProject_1.Controllers;

import com.mk.SpringBootProject_1.Entity.Users;
import com.mk.SpringBootProject_1.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private userService service;

    @GetMapping
    public List<Users> getAllUsers(){
    return service.getAll();
}
    @PostMapping("/createUser")
    public void createUser(@RequestBody Users user){
        service.saveNewUser(user);
    }
}
