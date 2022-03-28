package com.keeper.clothingkeeper.service;

import com.keeper.clothingkeeper.domain.Centigrade;
import com.keeper.clothingkeeper.domain.ClothesResponse;
import com.keeper.clothingkeeper.domain.DayRecommendResponse;

/**
 * 根据温度返回推荐衣服
 */
public interface DayRecommendService {
    ClothesResponse recommendClothes(Centigrade centigrade,String userid);
    DayRecommendResponse getDayRecommendByCityName(String city,String userid);
}
