package com.mk.SpringBootProject_1.Repository;

import com.mk.SpringBootProject_1.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;

public class UserRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Users> getUserForSA() {
        Query q=new Query();
//        q.addCriteria(Criteria.where("username").is("thala"));
        q.addCriteria(Criteria.where("email").exists(true));
        q.addCriteria(Criteria.where("email").ne(null).ne(""));
        q.addCriteria(Criteria.where("sentiment").is(true));
        List<Users> users=mongoTemplate.find(q,Users.class);

        return users;
    }
}
