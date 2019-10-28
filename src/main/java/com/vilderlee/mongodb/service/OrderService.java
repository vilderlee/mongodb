package com.vilderlee.mongodb.service;

import com.vilderlee.mongodb.domain.dto.Order;
import com.vilderlee.mongodb.domain.dto.UserOrder;
import com.vilderlee.mongodb.mapper.OrderDao;
import com.vilderlee.mongodb.mapper.UserBuyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class OrderService {

    @Autowired
    private UserBuyDao userBuyDao;

    @Autowired
    private OrderDao orderDao;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void propagation_required(Order order, UserOrder userOrder) {
        updateOrder(order);
        insertUserOrder(userOrder);
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void propagation_support(Order order, UserOrder userOrder) {
        updateOrder(order);
        insertUserOrder(userOrder);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void propagation_MANDATORY(Order order, UserOrder userOrder) throws Exception {
        updateOrder(order);
        insertUserOrder(userOrder);
        int a = 5 / 0;
    }
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.MANDATORY)
    public void updateOrder(Order order){
        orderDao.updateOrderCount(order);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public void insertUserOrder(UserOrder userOrder){
        userBuyDao.insertUserOrder(userOrder);
        int a = 5 /2;
    }
}
