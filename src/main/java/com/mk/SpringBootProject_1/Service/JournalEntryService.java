package com.mk.SpringBootProject_1.Service;

import com.mk.SpringBootProject_1.Entity.JournalEntry;
import com.mk.SpringBootProject_1.Entity.Users;
import com.mk.SpringBootProject_1.Repository.EntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private EntryRepo entryRepo;

    @Autowired
    private userService UserService;

    public void save(JournalEntry journalEntry) {
       entryRepo.save(journalEntry);
    }
    @Transactional
    public void save(JournalEntry journalEntry, String userName) {
        try {
            Users user = UserService.findByUsername(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = entryRepo.save(journalEntry);
            user.getEntries().add(saved);
            UserService.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<JournalEntry> findAll() {
        return entryRepo.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id) {
        return entryRepo.findById(id);
    }

    public void deleteById(ObjectId id, String userName) {
        try {
            Users user = UserService.findByUsername(userName);
            user.getEntries().removeIf(entry -> entry.getId().equals(id));
            UserService.save(user);
            entryRepo.deleteById(id);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
