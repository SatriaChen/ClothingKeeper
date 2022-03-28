package com.keeper.clothingkeeper.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keeper.clothingkeeper.domain.Centigrade;
import com.keeper.clothingkeeper.domain.Forecast;
import com.keeper.clothingkeeper.domain.WeatherResponse;
import com.keeper.clothingkeeper.service.WeatherDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.util.concurrent.TimeUnit;

/**
 * @description 根据api：http://wthrcdn.etouch.cn/weather_mini? 获取天气数据
 */

@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    protected static final Logger logger = LoggerFactory.getLogger(WeatherDataService.class);
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    @Autowired
    private RestTemplate restTemplate;

    //@Autowired
    //private StringRedisTemplate stringRedisTemplate;

    //private static final long TIME_OUT = 10L;
    //根据city名称返回完整的天气数据
    @Override
    public WeatherResponse getDataByCityName(String CityName) {
        String uri = WEATHER_URI + "city=" + CityName;
        return this.getWeatherRespnonse(uri);

    }

    /**
     * @param CityName
     * @param userId
     * @return Centigrade
     */
    @Override
    public Centigrade getCentigradeByCityName(String CityName) {
        String uri = WEATHER_URI + "city=" + CityName;
        return this.getCentigradeRespnonse(uri);
    }

    @Override
    public Centigrade getCentigrade(Forecast forecast) {
        String high = forecast.getHigh();
        String[] buffH = high.split("高温 |℃");
        String low = forecast.getLow();
        String[] buffL = low.split("低温 |℃");
        //System.out.println(cenStr);
        //Centigrade centigrade = new Centigrade(cenStr.get(0), cenStr.get(1));
        Centigrade centigrade = new Centigrade(buffH[1], buffL[1]);
        return centigrade;
    }

    private Centigrade getCentigradeRespnonse(String uri) {
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);
        String strBody = null;
        if (response.getStatusCodeValue() == 200) {
            strBody = response.getBody();
        }

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = null;

        try {
            weatherResponse = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String high = weatherResponse.getData().getForecast().get(0).getHigh();
        String[] buffH = high.split("高温 |℃");
        String low = weatherResponse.getData().getForecast().get(0).getLow();
        String[] buffL = low.split("低温 |℃");
        //System.out.println(cenStr);
        //Centigrade centigrade = new Centigrade(cenStr.get(0), cenStr.get(1));
        Centigrade centigrade = new Centigrade(buffH[1], buffL[1]);
        return centigrade;
    }

    private WeatherResponse getWeatherRespnonse(String uri) {
        /*String key = uri;
        String strBody = null;
        WeatherResponse resp = null;
        ObjectMapper objectMapper = new ObjectMapper();
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //先查缓存，如果有缓存，取缓存数据
        if (stringRedisTemplate.hasKey(key)) {
            logger.info("Redis has data");
            strBody = ops.get(key);
        }
        else {
            logger.info("Redis don't have data");
            //缓存没有，再调服务接口获取
            ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
            if (200 == respString.getStatusCodeValue()) {
                strBody = respString.getBody();
            }
            //数据写入缓存
            ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
        }

        try {
            resp = objectMapper.readValue(strBody,WeatherResponse.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return resp;*/

        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);
        String strBody = null;
        if (response.getStatusCodeValue() == 200) {
            strBody = response.getBody();
        }

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = null;

        try {
            weatherResponse = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResponse;
    }



    /*private void saveWeatherData(String uri) {
        String key = uri;
        String strBody = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }
        ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
    }*/
}
