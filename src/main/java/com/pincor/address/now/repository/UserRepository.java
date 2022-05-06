package com.pincor.address.now.repository;

import com.pincor.address.now.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void createUser(User user) {
        mongoTemplate.save(user);
    }

    public User loadUserByUserName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        return mongoTemplate.findOne(query, User.class);
    }
}
