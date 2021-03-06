package com.vilderlee.mongodb.mapper;

import com.vilderlee.mongodb.domain.dto.UserOrder;
import org.springframework.stereotype.Repository;

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
@Repository
public interface UserBuyDao {

    void insertUserOrder(UserOrder userOrder);
}
