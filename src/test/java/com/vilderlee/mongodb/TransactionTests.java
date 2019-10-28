package com.vilderlee.mongodb;

import com.vilderlee.mongodb.domain.dto.Order;
import com.vilderlee.mongodb.domain.dto.UserOrder;
import com.vilderlee.mongodb.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

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
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTests {

    @Autowired
    private OrderService orderService;



    @Test
    public void propagation_required(){
        Order order = initOrder();
        UserOrder userOrder = initUserOrder();
        orderService.propagation_required(order, userOrder);
    }
    @Test
    public void propagation_support(){
        Order order = initOrder();
        UserOrder userOrder = initUserOrder();
        orderService.propagation_support(order, userOrder);
    }
    @Test
    public void propagation_MANDATORY() throws Exception {
        Order order = initOrder();
        UserOrder userOrder = initUserOrder();
        orderService.propagation_MANDATORY(order, userOrder);
    }

    private UserOrder initUserOrder() {
        UserOrder userOrder = new UserOrder();
        userOrder.setSystemNo(UUID.randomUUID().toString().substring(20));
        userOrder.setOrderID("000001");
        userOrder.setUserID("1");
        return userOrder;
    }

    private Order initOrder() {
        Order order = new Order();
        order.setSystemNo("0000001");
        order.setOrderID("1");
        order.setOrderName("xixixi");
        order.setCount(100);
        order.setVersion(1);
        return order;
    }
}
