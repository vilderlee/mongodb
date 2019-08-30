package com.vilderlee.mongodb.mapper;

import com.vilderlee.mongodb.domain.FundIn;
import org.springframework.stereotype.Repository;

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
@Repository
public interface FundInDao {
    List<FundIn> getAll();
}
