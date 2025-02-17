package com.mk.SpringBootProject_1;

import com.mk.SpringBootProject_1.Repository.userRepo;
import com.mk.SpringBootProject_1.Service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.when;

// This is the implementation of Mockito , means when we don't want to establish
// connection to DataBase and Mock things like username ,
// so we use this implementation . This will not invoke whole spring boot startup .


public class TestUserServiceImpl {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private userRepo repo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByUsername() {
//        when(repo.findByUsername(ArgumentMatcher.anyString()).thenReturn(Users.builder().username("ram").password("jbfjdbf")));
        UserDetails user = userDetailsServiceImpl.loadUserByUsername("ram");
        Assertions.assertNotNull(user);

    }
}
