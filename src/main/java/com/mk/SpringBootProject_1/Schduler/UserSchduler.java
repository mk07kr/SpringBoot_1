package com.mk.SpringBootProject_1.Schduler;

import com.mk.SpringBootProject_1.Cache.AppCache;
import com.mk.SpringBootProject_1.Entity.JournalEntry;
import com.mk.SpringBootProject_1.Entity.Users;
import com.mk.SpringBootProject_1.Repository.UserRepoImpl;
import com.mk.SpringBootProject_1.Service.EmailService;
import com.mk.SpringBootProject_1.Service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;


@Component
public class UserSchduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userImpl;

    @Autowired
    private SentimentAnalysisService saService;

    @Autowired
    private AppCache appCache;


    @Scheduled(cron="0 0 9 * * Sun") // Format of cron = "sec min hour day month dayName"
    public void fetchUserAndSendSaMail(){
        List<Users> users = userImpl.getUserForSA();
        for(Users user : users){
            List<JournalEntry> entries = user.getEntries();
            List<String> filteredEntries = entries.stream().filter(x -> x.getDate().
                    isAfter(LocalDateTime.now().minusDays(7))).map(x-> String.valueOf(x.getSentiment())).toList();
            String jointEntry = String.join(" ", filteredEntries);
            String sentiment = saService.analyzeSentiment(jointEntry);
            emailService.sendEmail(user.getEmail(),"Sentiment Analysis for last 7 days",sentiment);
        }
    }

    //Reload App cache after every 20 mins
    @Scheduled(cron="0 0/20 * * * *")
    public void clearAppCache(){
        appCache.init();
    }
}




