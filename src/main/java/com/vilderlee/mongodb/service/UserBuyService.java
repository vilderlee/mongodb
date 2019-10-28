package com.vilderlee.mongodb.service;

import com.vilderlee.mongodb.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserBuyService {

    @Autowired
    private UserDao userDao;

    public void findUser(String userId){

        userDao.findUserById(userId);
    }
}
