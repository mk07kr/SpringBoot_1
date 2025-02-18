package com.mk.SpringBootProject_1.Controllers;

import com.mk.SpringBootProject_1.Cache.AppCache;
import com.mk.SpringBootProject_1.Entity.Users;
import com.mk.SpringBootProject_1.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private userService UserService;
    @Autowired
    private AppCache appCache;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        List<Users> all = UserService.getAll();
        if(all!=null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @PostMapping("/create")
    public ResponseEntity<?> addAdmin(@RequestBody Users user) {
        UserService.saveAdmin(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/clear-Cache")
    public void clearAppCache(){
        appCache.init();
    }
}
