package com.vilderlee.mongodb.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document
@Data
@ToString
public class Result {

    @Id
    private long id;

    private int totalAge;
}
