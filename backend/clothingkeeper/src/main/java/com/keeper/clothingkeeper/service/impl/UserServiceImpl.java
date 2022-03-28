package com.keeper.clothingkeeper.service.impl;
import com.keeper.clothingkeeper.dao.UserMapper;
import com.keeper.clothingkeeper.domain.User;

import com.keeper.clothingkeeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper UserMapper;

    @Override
    public User findUserById(String id) {
       return UserMapper.selectByPrimaryKey(id);
    }

    public User findByUsername(String name){
        return UserMapper.selectByUsername(name);
    }

    public int insertSelective(User record){
        return UserMapper.insertSelective(record);
    }

    public int count(){
        return UserMapper.count();
    }

    public int idMax(){
        return UserMapper.idMax();
    }

    public int deleteByPrimaryKey(String userid){
        return UserMapper.deleteByPrimaryKey(userid);
    }
}
