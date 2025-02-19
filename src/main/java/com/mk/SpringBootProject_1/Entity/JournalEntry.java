package com.mk.SpringBootProject_1.Entity;


import com.mk.SpringBootProject_1.enums.Sentiment;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




import java.time.*;


@Document(collection = "Journal_Entries")
@Getter
@Setter
public class JournalEntry {

    @Id
    private ObjectId id;
    private String title;
    private String content;
    private Sentiment sentiment;
    private LocalDateTime date;



}
