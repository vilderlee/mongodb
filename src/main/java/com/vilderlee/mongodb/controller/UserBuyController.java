package com.vilderlee.mongodb.controller;

import com.vilderlee.mongodb.domain.dto.Order;
import com.vilderlee.mongodb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class UserBuyController {

    @Autowired
    private OrderService orderService;

//    @RequestMapping("/order/buy")
//    public String buy(String userId, String orderId){
//        Order order =
//        orderService.updateOrder();
//    }

}
