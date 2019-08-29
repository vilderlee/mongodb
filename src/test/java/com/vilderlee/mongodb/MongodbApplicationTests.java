package com.vilderlee.mongodb;

import com.vilderlee.mongodb.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) @SpringBootTest public class MongodbApplicationTests {

    @Autowired private MongoTemplate mongoTemplate;

    @Test public void insert() {

        mongoTemplate.createCollection("springboot");
        User user = new User();
        user.setUserId(1L);
        user.setUserName("vilderlee");
        user.setAge(25);
        mongoTemplate.insert(user);
    }

    @Test public void find() {

        Query query = new Query(Criteria.where("userName").is("vilderlee"));

        User user =  mongoTemplate.findOne(query, User.class);
        System.out.println(user);
    }

}
