package com.vilderlee.mongodb.java;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * 类说明:
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/9/2      Create this file
 * </pre>
 */
public class JavaOrigin {

    public static void main(String[] args) {
        MongoClient client = MongoClients.create("mongodb://39.104.159.18:27017");
        MongoDatabase test = client.getDatabase("test");
        MongoCollection<Document> user = test.getCollection("user");

        Document first = user.find(eq("userName", "lichao")).first();

        System.out.println(first.toJson());
        System.out.println(user.countDocuments());
    }
}
