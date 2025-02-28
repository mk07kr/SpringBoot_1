package com.mk.SpringBootProject_1;

import com.mk.SpringBootProject_1.Schduler.UserSchduler;
import com.mk.SpringBootProject_1.Service.EmailService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;

@SpringBootTest
public class EmailServiceTests {
//@Autowired
//    private EmailService emailService;


//@Disabled
//@Test
//public void testSendEmail() {
//    SimpleMailMessage message = new SimpleMailMessage();
//   emailService.sendEmail("meetmayankcv@gmail.com","testing Java SMTP via SpringBoot",
//           "Hi MK , Hope you are fine ! We are testing oour new method .");
//
//}

@Autowired
private UserSchduler userSchduler;

@Test
        public void testSendEmail1() {
    userSchduler.fetchUserAndSendSaMail();
}



}
