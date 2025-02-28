package com.mk.SpringBootProject_1.Repository;

import com.mk.SpringBootProject_1.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UserRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Users> getUserForSA() {
        Query q=new Query();
//        q.addCriteria(Criteria.where("username").is("thala"));  // Example
        Criteria emailCriteria = new Criteria().andOperator(
                Criteria.where("email").exists(true),  // Ensure email exists
                Criteria.where("email").ne(null),     // Ensure email is not null
                Criteria.where("email").ne("")       // Ensure email is not empty
        );

        // Add email criteria and sentiment criteria to the query
        q.addCriteria(emailCriteria);
        q.addCriteria(Criteria.where("sentiment").is(true));
        List<Users> users=mongoTemplate.find(q,Users.class);

        return users;
    }
}
