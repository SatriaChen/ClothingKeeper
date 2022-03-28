package com.keeper.clothingkeeper.service;

import com.keeper.clothingkeeper.domain.Centigrade;
import com.keeper.clothingkeeper.domain.Forecast;
import com.keeper.clothingkeeper.domain.WeatherResponse;

/**
 * 天气数据接口
 */
public interface WeatherDataService {
    /**
     * 根据城市名称查询天气数据
     * @param CityName
     * @return
     */

    WeatherResponse getDataByCityName(String CityName);
    Centigrade getCentigradeByCityName(String CityName);
    Centigrade getCentigrade(Forecast forecast);
}
