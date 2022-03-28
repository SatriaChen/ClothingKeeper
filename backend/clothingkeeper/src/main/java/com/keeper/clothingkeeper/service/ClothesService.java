package com.keeper.clothingkeeper.service;

import com.keeper.clothingkeeper.domain.Clothes;
import com.keeper.clothingkeeper.domain.ClothesResponse;
import com.keeper.clothingkeeper.dto.ClothDTO;
import com.keeper.clothingkeeper.dto.ClothForShowDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public interface ClothesService {
    List<Clothes> selectClothesRecord(String userid);
    //根据typeId获取衣服
    List<Clothes> getClothesByType(String typeId);
    //根据season获取衣服
    List<Clothes> getClothesBySeason(String season,String userid);
    //将同一季节的衣服按类型分开
    ClothesResponse getSameSeasonClothesByType(List<Clothes> SameSeasonClothes);
//
//
//
    /**
     * 增加一件衣服
     * @param ClothDto clothDTO
     *
     */
    void addCloth(ClothDTO clothDTO,String userid);

    /**
     * 永远删除一个衣服
     * @param userId user_id
     * @param clothId cloth_id
     */
    void deleteCloth(String userId,String clothId);

    /**
     * 根据衣服类型（上装 下装 套装 其他）查找衣服
     * @param userId user_id
     * @param clothType 衣服类型（上装 下装 套装 其他）
     * @return List&lt;ClothEntity&gt;
     */
    List<ClothForShowDTO> findClothByType(String userId, String clothType);

    /**
     * 根据衣服类型（上装 下装 套装 其他）查找衣服
     * @param userId user_id
     * @param clothRfid
     * @return List&lt;ClothEntity&gt;
     */
    List<ClothForShowDTO> findClothByRfid(String userId, String cRfid);

    int countByrfid(String userId, String cRfid);
//
//    /**
//     * 搜索用户的衣服
//     * @param userId user_id
//     * @param clothKeyWord 衣服关键字
//     * @return List&lt;ClothEntity&gt;
//     */
//    List<ClothForShowDTO> searchCloth(String userId, String clothKeyWord);
//
//    /**
//     * 将一个衣服移出衣柜 频率加一
//     * @param userId userId
//     * @param clothId clothId
//     */
//    void moveOutCloth(String userId, int clothId);
//
//    /**
//     * 将一个衣服移进衣柜
//     * @param userId userId
//     * @param clothId clothId
//     */
//    void moveInCloth(String userId, int clothId);
//
    /**
     * 标记一件衣服状态
     * @param userId user id
     * @param clothId cloth id
     */
    void cleanCloth(String userId, String clothId);
//
//    /**
//     * 标记一件衣服是不干净的
//     * @param userId user id
//     * @param clothId cloth id
//     */
//    void uncleanCloth(String userId, int clothId);
//
    /**
     * 上传图片文件获得相应图片id
     * @param multipartFile file
     * @return obj_id
     */
    String uploadFileGetObjId(MultipartFile multipartFile);

}
