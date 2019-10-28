package com.vilderlee.mongodb;

import com.mongodb.client.result.UpdateResult;
import com.vilderlee.mongodb.domain.Address;
import com.vilderlee.mongodb.domain.FundIn;
import com.vilderlee.mongodb.domain.OrderTest;
import com.vilderlee.mongodb.domain.Response;
import com.vilderlee.mongodb.domain.Result;
import com.vilderlee.mongodb.domain.UserMG;
import com.vilderlee.mongodb.mapper.FundInDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbApplicationTests {

    @Autowired private MongoTemplate mongoTemplate;

    @Test public void insert() {

        mongoTemplate.createCollection("springboot");
        UserMG user = new UserMG();
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
        UserMG useru = new UserMG();
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
        List<UserMG> users = mongoTemplate.find(query, UserMG.class);

        users.stream().forEach(System.out::println);

//        Query query = new Query();
//        Criteria criteria = new Criteria();
//        criteria.orOperator(Criteria.where("age").is(18), Criteria.where("userId").is(1));
//        query.addCriteria(criteria);
//        List<UserMG> users = mongoTemplate.find(query, UserMG.class);
//        users.stream().forEach(System.out::println);
    }
    @Test
    public void delete(){
        Query query = Query.query(Criteria.where("userId").is(1).orOperator(Criteria.where("userId").is(2)));

        mongoTemplate.remove(query,UserMG.class);
        List<UserMG> all = mongoTemplate.findAll(UserMG.class);
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

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, UserMG.class);
        UserMG user =  mongoTemplate.findOne(query,UserMG.class);
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

    @Test
    public void insertMany(){
        OrderTest order1 = new OrderTest("10000001", "10", 20l);
        OrderTest order2 = new OrderTest("10000001", "10", 20l);
        OrderTest order3 = new OrderTest("10000001", "10", 20l);
        OrderTest order4 = new OrderTest("10000002", "20", 20l);
        OrderTest order5 = new OrderTest("10000002", "30", 20l);
        List<OrderTest> list = Arrays.asList(order1,order2,order3,order4,order5);
        mongoTemplate.insert(list,"orders");
    }

    /**
     * map-reduce操作
     * db.getCollection.mapReduce(
     *  function map{ emit(key,value)},
     *  function reduce(key,value){return value;},
     *  {
     *      query: filter origin data;  <document>
     *      sort: sort;  <document>
     *      out: output collection;     String collectionName
     *  }
     * );
     */

    @Test
    public void mapReduce() {
        String map = "function(){ var key = this.key;"
                + " var value = {status:this.status,amt:this.amt}; emit(key,value);}";
        String reduce = "function(key,values){"
                + "    var result= new Object();"
                + "    var result1= new Object();"
                + "    result.key = key;"
                + "    result1.key = key;"
                + "    result.status = values[0].status;"
                + "    result1.status = values[1].status;   "
                + "    result.amt = values[0].amt;"
                + "    result1.amt = values[1].amt;"
                + "    var response = {message:null, result:null, length:NumberInt(values.length)};"
                + "    if(values.length > 2){"
                + "        response.message = '数据不对';"
                + "        return response;"
                + "        }"
                + "    "
                + "    "
                + "    if(result.status != result1.status){  "
                + "        response.message = '状态不一致';"
                + "        response.result = [result,result1];"
                + "        return response;"
                + "    }"
                + "    if(result.amt != result1.amt){"
                + "        response.message = '金额不一致';"
                + "         response.result = [result,result1];"
                + "        return response;"
                + "    }"
                + "}";

        String finalizer = "function(key,value){"
                + "     return {_id:key,message:value.message,list:value.result}"
                + "  }     ";

        MapReduceOptions options = new MapReduceOptions();
        options.outputCollection("checkResult");
        options.finalizeFunction(finalizer);


        Query query = new Query();
        query.addCriteria(Criteria.where("status").exists(true));
        query.with(Sort.by(Sort.Direction.ASC,"amt"));
        MapReduceResults<Response> order = mongoTemplate.mapReduce("orders", map, reduce, options, Response.class);



        Iterator iterator = order.iterator();
        if (iterator.hasNext()){

        }
        System.out.println();
    }


    @Test
    public void getAllResponse(){
    }
}
