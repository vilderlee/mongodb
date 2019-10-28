package com.vilderlee.mongodb.domain.dto;

import lombok.Data;

/**
 * 类说明:
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/9/3      Create this file
 * </pre>
 */
@Data
public class Order {

    private String systemNo;

    private String orderID;

    private String orderName;

    private int count;

    private int version;
}
