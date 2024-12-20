package com.mk.SpringBootProject_1.Service;

import com.mk.SpringBootProject_1.Entity.JournalEntry;
import com.mk.SpringBootProject_1.Repository.EntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private EntryRepo entryRepo;

    public void save(JournalEntry journalEntry) {
        entryRepo.save(journalEntry);
    }

    public List<JournalEntry> findAll() {
        return entryRepo.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id) {
        return entryRepo.findById(id);
    }
    public void deleteById(ObjectId id) {
        entryRepo.deleteById(id);
    }
}
