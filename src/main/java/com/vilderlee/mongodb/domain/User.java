package com.vilderlee.mongodb.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 类说明:
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/8/29      Create this file
 * </pre>
 */
@Document
@Data
@ToString
public class User {

    private long userId;

    private String userName;

    private int age;

}
