package com.mk.SpringBootProject_1.Controllers;

import com.mk.SpringBootProject_1.Entity.Users;
import com.mk.SpringBootProject_1.Service.UserDetailsServiceImpl;
import com.mk.SpringBootProject_1.Service.userService;
import com.mk.SpringBootProject_1.Utility.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private userService service;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signup")
    public void signup(@RequestBody Users user){
        service.saveNewUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user){
        try{
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwtToken=jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);

        }
        catch (Exception e){
            log.error("Bad request: Try Again",e);
            return new ResponseEntity<>("UnAuthorized", HttpStatus.UNAUTHORIZED);
        }

    }
}
