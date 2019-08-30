package com.vilderlee.mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 类说明:
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/8/30      Create this file
 * </pre>
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String country;
    private String province;
    private String city;
    private String street;
}
