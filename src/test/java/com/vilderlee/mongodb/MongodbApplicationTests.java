package com.vilderlee.mongodb;

import com.mongodb.client.result.UpdateResult;
import com.vilderlee.mongodb.domain.Address;
import com.vilderlee.mongodb.domain.FundIn;
import com.vilderlee.mongodb.domain.Result;
import com.vilderlee.mongodb.domain.User;
import com.vilderlee.mongodb.mapper.FundInDao;
import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private FundInDao fundInDao;
    @Test
    public void insertAll(){
        List<FundIn> all = fundInDao.getAll();
        mongoTemplate.insertAll(all);

    }


    @Test public void save() {
        User useru = new User();
        useru.setUserId(1L);
        useru.setUserName("lichao");
        useru.setAge(25);
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("China","ShanXi","gaoPing","0001")) ;
        addresses.add(new Address("China","Beijing","Beijing","0001")) ;

        useru.setAddress(addresses);
        mongoTemplate.save(useru);

    }

    @Test public void findAll(){

        Query query = Query.query(Criteria.where("age").is(18).and("userId").is(2));
        List<User> users = mongoTemplate.find(query, User.class);

        users.stream().forEach(System.out::println);

//        Query query = new Query();
//        Criteria criteria = new Criteria();
//        criteria.orOperator(Criteria.where("age").is(18), Criteria.where("userId").is(1));
//        query.addCriteria(criteria);
//        List<User> users = mongoTemplate.find(query, User.class);
//        users.stream().forEach(System.out::println);
    }
    @Test
    public void delete(){
        Query query = Query.query(Criteria.where("userId").is(1).orOperator(Criteria.where("userId").is(2)));

        mongoTemplate.remove(query,User.class);
        List<User> all = mongoTemplate.findAll(User.class);
        all.forEach(System.out::println);
    }

    @Test
    public void update(){
        Query query = Query.query(Criteria.where("userName").is("vilderlee"));

        Update update = new Update();
//        update.inc("age",3);
//        update.max("age",100);
        update.multiply("age", 3);
        update.pop("users", Update.Position.FIRST);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);
        User user =  mongoTemplate.findOne(query,User.class);
        System.out.println(user);

        System.out.println(updateResult);
    }


    @Test
    public void aggregateSum(){
        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(Aggregation.group("_id").sum("age").as("totalAge")) ;
        AggregationResults<Result> user = mongoTemplate
                .aggregate(Aggregation.newAggregation(operations), "user", Result.class);

        user.getMappedResults().forEach(System.out::println);

        String json = user.getRawResults().toJson();
        System.out.println(json);
        user.getMappedResults().forEach(System.out::println);
    }


    @Test
    public void aggregateAvg(){
        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(Aggregation.group("t").sum("age").as("totalAge")) ;
        AggregationResults<Result> user = mongoTemplate
                .aggregate(Aggregation.newAggregation(operations), "user", Result.class);

        user.getMappedResults().forEach(System.out::println);

//        String json = user.getRawResults().toJson();
//        System.out.println(json);
//        user.getMappedResults().forEach(System.out::println);
    }
}
