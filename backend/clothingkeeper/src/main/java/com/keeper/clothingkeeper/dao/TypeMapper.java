package com.keeper.clothingkeeper.dao;

import com.keeper.clothingkeeper.domain.Type;
import com.keeper.clothingkeeper.domain.TypeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMapper {
    int deleteByPrimaryKey(TypeKey key);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(TypeKey key);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);
}