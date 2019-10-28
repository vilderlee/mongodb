package com.vilderlee.mongodb.domain.dto;

import lombok.Data;

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
@Data
public class User {

    private String Id;

    private String userName;

    private int age;

}
