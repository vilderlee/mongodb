package com.vilderlee.mongodb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document
@Data
@AllArgsConstructor
public class OrderTest {
    private String key;

    private String status;

    private long amt;
}
