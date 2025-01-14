package com.mk.SpringBootProject_1.Repository;

import com.mk.SpringBootProject_1.Entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userRepo extends MongoRepository<Users,ObjectId> {
    Users findByUsername(String username);

}
