package com.mk.SpringBootProject_1.Cache;

import com.mk.SpringBootProject_1.Entity.ConfigApiEntry;
import com.mk.SpringBootProject_1.Repository.ConfigApiRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component public class AppCache {
public Map<String,String> appCache;

@Autowired
private ConfigApiRepo configApiRepo;


    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<ConfigApiEntry> all = configApiRepo.findAll();
        for (ConfigApiEntry entry : all) {
            appCache.put(entry.getKey(), entry.getValue());
        }


    }
}
