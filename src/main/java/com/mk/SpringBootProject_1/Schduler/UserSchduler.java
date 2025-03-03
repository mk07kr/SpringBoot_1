package com.mk.SpringBootProject_1.Schduler;

import com.mk.SpringBootProject_1.Cache.AppCache;
import com.mk.SpringBootProject_1.Entity.JournalEntry;
import com.mk.SpringBootProject_1.Entity.Users;
import com.mk.SpringBootProject_1.Repository.UserRepoImpl;
import com.mk.SpringBootProject_1.Service.EmailService;
import com.mk.SpringBootProject_1.Service.SentimentAnalysisService;
import com.mk.SpringBootProject_1.enums.Sentiment;
import com.mk.SpringBootProject_1.model.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

//    @Autowired
//    private KafkaTemplate<String, SentimentData> kafkaTemplate;


    @Scheduled(cron="0 0 9 * * Sun") // Format of cron = "sec min hour day month dayName"
    public void fetchUserAndSendSaMail() {
        List<Users> users = userImpl.getUserForSA();
        for (Users user : users) {
            List<JournalEntry> entries = user.getEntries();
            List<Sentiment> filteredEntries = entries.stream().filter(x -> x.getDate().
                    isAfter(LocalDateTime.now().minusDays(7))).map(x -> Sentiment.valueOf(String.valueOf((x.getSentiment())))).toList();
//            String jointEntry = String.join(" ", filteredEntries);
//            String sentiment = saService.analyzeSentiment(jointEntry);
            Map<Sentiment, Integer> sCounts = new HashMap<>();
            for (Sentiment sentiment : filteredEntries) {
                if (sentiment != null) {
                    sCounts.put(sentiment, sCounts.getOrDefault(sentiment, 0) + 1);
                }
                Sentiment mostFreqSntmnt = null;
                int maxCount=0;
                for(Map.Entry<Sentiment, Integer> entry : sCounts.entrySet()) {
                    if(entry.getValue()>maxCount) {
                        maxCount=entry.getValue();
                        mostFreqSntmnt = entry.getKey();
                    }
                }
//                Sentiment mostFreqSntmnt = sCounts.entrySet().stream()
//                        .max(Map.Entry.comparingByValue())
//                        .map(Map.Entry::getKey)
//                        .orElse(null);

                if (mostFreqSntmnt != null) {
                    emailService.sendEmail(user.getEmail(), "Sentiment Analysis for last 7 days", mostFreqSntmnt.toString());
//                    SentimentData sentimentData =SentimentData.builder().email(user.getEmail()).sentiment("Sentiment for last 7 days "+mostFreqSntmnt).build();
//                    kafkaTemplate.send("weekly-sentiments",sentimentData.getEmail(),sentimentData);
                }
            }
        }
    }

    //Reload App cache after every 20 mins
    @Scheduled(cron="0 0/20 * * * *")
    public void clearAppCache(){
        appCache.init();
    }
}




