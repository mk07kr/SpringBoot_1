package com.mk.SpringBootProject_1.Controllers;

import com.mk.SpringBootProject_1.Entity.JournalEntry;
import com.mk.SpringBootProject_1.Service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Journal")
public class JournalEntryController {


    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAllEntry() {

        return journalEntryService.findAll();
    }


    @PostMapping
    public ResponseEntity<JournalEntry> addEntry(@RequestBody JournalEntry myEntry) {
            try{
                    journalEntryService.save(myEntry);
                    myEntry.setDate(LocalDateTime.now());
                    return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
            }
            catch(Exception e){
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId myId) {
            Optional<JournalEntry> byId = journalEntryService.findById(myId);
            if (byId.isPresent()) {
                    return new ResponseEntity<>(byId.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


    }

    @DeleteMapping("id/{myId}")
    public void deleteEntryById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);

    }

    @PutMapping("id/{myId}")
    public JournalEntry updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
        JournalEntry old = journalEntryService.findById(myId).orElse(null);
        if (old != null) {
            old.setTitle(old.getTitle() != null && !old.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(old.getContent() != null && !old.getContent().equals("") ? newEntry.getContent() : old.getContent());
        }
        journalEntryService.save(old);

        return old;


    }

}

