package com.vilderlee.mongodb.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
public class UserMG {

    @Id
    private long userId;

    private String userName;

    private int age;

    private List<Address> address;

}
