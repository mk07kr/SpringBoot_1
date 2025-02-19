package com.mk.SpringBootProject_1.Controllers;

import com.mk.SpringBootProject_1.Api_response.Weather;
import com.mk.SpringBootProject_1.Entity.Users;
import com.mk.SpringBootProject_1.Repository.userRepo;
import com.mk.SpringBootProject_1.Service.WeatherService;
import com.mk.SpringBootProject_1.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private userService service;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private Weather weather;

    @Autowired
    private userRepo repo;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Users user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users userInDB = service.findByUsername(username);
        userInDB.setUsername(user.getUsername());
        userInDB.setPassword(user.getPassword());

        // Save the updated user
        service.saveNewUser(userInDB);

        // Return the updated user
        return new ResponseEntity<>(userInDB, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        repo.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Weather weather= weatherService.getWeather("Mumbai");
        String greet="";
        if(weather!=null){
            greet=" Weather feels like "+weather.getCurrent().getFeelslike_c()+" Humidity = "+weather.getCurrent().getHumidity();
        }
        return new ResponseEntity<>("Hii "+authentication.getName()+greet,HttpStatus.OK);
    }


}





