package com.mk.SpringBootProject_1.Entity;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "Config_API")
@Getter
@Setter
public class ConfigApiEntry {


    private String key;
    private String value;


}
