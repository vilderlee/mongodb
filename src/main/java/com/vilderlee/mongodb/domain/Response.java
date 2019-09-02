package com.vilderlee.mongodb.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

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
@Document @Data @ToString
public class Response {
    @Id
    private String id;

    private Value value;

//    private String message;

    private List<Result> result;

    private int length;



    @Document @Data @ToString
    public static class Value {
        private String _id;

        private String message;

        private List list = new ArrayList();
    }
}
