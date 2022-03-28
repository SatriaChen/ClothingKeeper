package com.keeper.clothingkeeper.service.impl;

import com.keeper.clothingkeeper.domain.Centigrade;
import com.keeper.clothingkeeper.domain.Clothes;
import com.keeper.clothingkeeper.domain.ClothesResponse;
import com.keeper.clothingkeeper.domain.DayRecommendResponse;
import com.keeper.clothingkeeper.service.ClothesService;
import com.keeper.clothingkeeper.service.DayRecommendService;
import com.keeper.clothingkeeper.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 按该天温度获得推荐衣服
 */
@Service
public class DayRecommendServiceimpl implements DayRecommendService {

    @Autowired
    private ClothesService ClothesService;

    @Autowired
    private WeatherDataService WeatherDataService;

    //@Autowired
    //private DayRecommendService DayRecommendService;

    @Override
    public ClothesResponse recommendClothes(Centigrade centigrade,String userid) {

        int high = Integer.parseInt(centigrade.getHigh());
        int low = Integer.parseInt(centigrade.getLow());
        ClothesResponse clothesResponse = new ClothesResponse();

        if (high < 10) {
            //冬季，获取cSeason为冬季的衣服: 2
            List<Clothes> clothes = ClothesService.getClothesBySeason("2",userid);
            clothesResponse = ClothesService.getSameSeasonClothesByType(clothes);


        }
        else if (high < 25 && low <=25 && high >= 10) {
            //春秋: 0
            List<Clothes> clothes = ClothesService.getClothesBySeason("0",userid);
            clothesResponse = ClothesService.getSameSeasonClothesByType(clothes);
        }
        else if (low >= 25) {
            //夏季: 1
            List<Clothes> clothes = ClothesService.getClothesBySeason("1",userid);
            clothesResponse = ClothesService.getSameSeasonClothesByType(clothes);
        }
        return clothesResponse;
    }

    @Override
    public DayRecommendResponse getDayRecommendByCityName(String city,String userid) {
        DayRecommendResponse dayRecommendResponse = new DayRecommendResponse();
        Centigrade centigrade = new Centigrade();
        ClothesResponse clothesResponse = new ClothesResponse();
        centigrade.setHigh(WeatherDataService.getCentigradeByCityName(city).getHigh());
        centigrade.setLow(WeatherDataService.getCentigradeByCityName(city).getLow());

        clothesResponse.setUpper(recommendClothes(centigrade,userid).getUpper());
        clothesResponse.setBottom(recommendClothes(centigrade,userid).getBottom());
        clothesResponse.setSuit(recommendClothes(centigrade,userid).getSuit());
        clothesResponse.setElseCloth(recommendClothes(centigrade,userid).getElseCloth());

        dayRecommendResponse.setCentigrade(centigrade);
        dayRecommendResponse.setClothesResponse(clothesResponse);

        if (isEmpty(clothesResponse)) {
            dayRecommendResponse.setMassage("没有数据");
        }
        return dayRecommendResponse;
    }
    public boolean isEmpty(ClothesResponse clothesResponse) {
        if (clothesResponse.getBottom().isEmpty() && clothesResponse.getUpper().isEmpty() && clothesResponse.getSuit().isEmpty() && clothesResponse.getElseCloth().isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

}
