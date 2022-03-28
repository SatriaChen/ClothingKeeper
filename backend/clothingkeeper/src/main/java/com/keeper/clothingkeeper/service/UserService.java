package com.keeper.clothingkeeper.service;

import com.keeper.clothingkeeper.domain.User;

//用户业务
public interface UserService {
    User findUserById(String id);
    User findByUsername(String name);
    int insertSelective(User record);
    int count();
    int idMax();
    int deleteByPrimaryKey(String userid);
}
