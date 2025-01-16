package com.mk.SpringBootProject_1.Controllers;

import com.mk.SpringBootProject_1.Entity.JournalEntry;
import com.mk.SpringBootProject_1.Entity.Users;
import com.mk.SpringBootProject_1.Service.JournalEntryService;
import com.mk.SpringBootProject_1.Service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {


    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private userService UserService;

    @GetMapping
    public ResponseEntity<?> getAllEntryofUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users userN = UserService.findByUsername(username);
        List<JournalEntry> all=userN.getEntries();
        if(all!=null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<JournalEntry> addEntry(@RequestBody JournalEntry myEntry) {
            try{
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String username = authentication.getName();
                    journalEntryService.save(myEntry,username);
                    myEntry.setDate(LocalDateTime.now());
                    return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
            }
            catch(Exception e){
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getEntryById(@PathVariable ObjectId myId) {
             Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
             String username = authentication.getName();
             Users user=UserService.findByUsername(username);
             List<JournalEntry> collect = user.getEntries().stream().filter(entry -> entry.getId().equals(myId))
                     .collect(Collectors.toList());
             if(!collect.isEmpty()) {
                 Optional<JournalEntry> byId = journalEntryService.findById(myId);
                 if (byId.isPresent()) {
                     return new ResponseEntity<>(byId.get(), HttpStatus.OK);
                 }
             }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public void deleteEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        journalEntryService.deleteById(myId,username);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myId,
                                             @RequestBody JournalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users user = UserService.findByUsername(username);

        // Filter user's entries to check if the entry exists
        List<JournalEntry> collect = user.getEntries().stream()
                .filter(entry -> entry.getId().equals(myId))
                .collect(Collectors.toList());

        if (!collect.isEmpty()) {
            // Find the journal entry by ID
            Optional<JournalEntry> byId = journalEntryService.findById(myId);
            if (byId.isPresent()) {
                JournalEntry oldEntry = byId.get(); // Unwrap the Optional
                // Update fields only if the new value is non-null and non-empty
                oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()
                        ? newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty()
                        ? newEntry.getContent() : oldEntry.getContent());

                // Save the updated entry
                journalEntryService.save(oldEntry);
                return new ResponseEntity<>(oldEntry, HttpStatus.OK);
            }
        }
        // Return a NOT_FOUND response if the entry does not exist
        return new ResponseEntity<>("Entry not found or unauthorized access", HttpStatus.NOT_FOUND);
    }


}

