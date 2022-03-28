package com.keeper.clothingkeeper.dao;

import com.keeper.clothingkeeper.domain.Clothes;
import com.keeper.clothingkeeper.domain.ClothesKey;
import com.keeper.clothingkeeper.dto.ClothDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesMapper {
    int deleteByPrimaryKey(ClothesKey key);

    int insert(Clothes record);

    int insertSelective(Clothes record);

    Clothes selectByPrimaryKey(ClothesKey key);

    int updateByPrimaryKeySelective(Clothes record);

    int updateByPrimaryKey(Clothes record);

    List<Clothes> selectClothesRecord(String userid);

    List<Clothes> getAllClothes(String userId);//通过用户ID查询该用户的所有衣服

    /**
     * 防止id重复
     * @param id id
     * @return count
     */
    int countId(Integer id);


    /**
     * 根据 用户id 和 类型 查找
     * @param userId userID
     * @param type type
     * @return List
     */
    List<Clothes> selectByUserIdAndType(@Param(value = "userId") String userId,
                                            @Param(value = "clothType") String type);
    List<Clothes> selectByUserIdAndRfid(@Param(value = "userId") String userId,
                                        @Param(value = "cRfid") String cRfid);
    /**
     * 根据 用户id 和 包含关键字 搜索
     * @param userId 用户id
     * @param keyWord 关键字
     * @return list
     */
    List<Clothes> selectByUserIdAndKeyWordContaining(@Param(value = "userId") String userId,
                                                         @Param(value = "keyWord") String keyWord);

    int countByrfid(String userId, String cRfid);
}