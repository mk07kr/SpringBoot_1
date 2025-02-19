package com.mk.SpringBootProject_1.Repository;

import com.mk.SpringBootProject_1.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntryRepo extends MongoRepository<JournalEntry, ObjectId> {

}
