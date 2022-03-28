package com.keeper.clothingkeeper.dao;

import com.keeper.clothingkeeper.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);
//通过用户名查询
    User selectByUsername(String name);
//查询用户数
    int count();
//当前最大用户ID
    int idMax();
}