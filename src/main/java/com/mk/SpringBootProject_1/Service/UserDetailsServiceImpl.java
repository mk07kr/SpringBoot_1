package com.mk.SpringBootProject_1.Service;

import com.mk.SpringBootProject_1.Entity.Users;
import com.mk.SpringBootProject_1.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private userRepo UserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Users user = UserRepo.findByUsername(username);
        if(user != null){
           return User.builder().username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0])).build();
        }

        throw new UsernameNotFoundException(username);

    }
}
