package com.keeper.clothingkeeper.service.impl;

import com.keeper.clothingkeeper.domain.*;
import com.keeper.clothingkeeper.service.DayRecommendService;
import com.keeper.clothingkeeper.service.TravelRecommendService;
import com.keeper.clothingkeeper.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class TravelRecommendServiceimpl implements TravelRecommendService {

    @Autowired
    private DayRecommendService DayRecommendService;
    @Autowired
    private WeatherDataService WeatherDataService;

    @Override
    public TravelRecommendResponse getTravelRecommendByCityName(String city, String startdate, String enddate,String userId) {
        //取出日期里的日数
        String[] buffStart = startdate.split("-");
        String start = buffStart[2];
        String[] buffEnd = enddate.split("-");
        String end = buffStart[2];


        try {
            //间隔天数
            int interval = getTermDay(startdate, enddate);
            WeatherResponse weatherResponse = WeatherDataService.getDataByCityName(city);

            //找出开始时间的天气信息
            int index = 0;
            //int size = weatherResponse.getData().getForecast().size();
            //if (index < size) {
                while(true) {
                    String dealdate = getTheDealedDate(weatherResponse.getData().getForecast().get(index));
                    if (dealdate.equals(start)){
                        break;
                    }

                    else
                        index++;


                }

                //找出从start到end之间的每天的气温，并推荐出每天的衣服
                List<ClothesResponse> travelRecommend = new ArrayList<ClothesResponse>();
                for (int i=index; i <= index + interval; i++) {
                    Forecast forecast = weatherResponse.getData().getForecast().get(i);
                    Centigrade centigrade = WeatherDataService.getCentigrade(forecast);
                    ClothesResponse clothesResponse = DayRecommendService.recommendClothes(centigrade,userId);
                    travelRecommend.add(clothesResponse);
                }
                TravelRecommendResponse travelRecommendResponse = new TravelRecommendResponse();
                travelRecommendResponse.setTravelRecommend(travelRecommend);
                travelRecommendResponse.setGanmao(weatherResponse.getData().getGanmao());
                travelRecommendResponse.setMessage("获取数据成功");
                return travelRecommendResponse;
            //}
            //else
            //   return null;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //处理天气信息中的日期，只取出数字
    //{"date":"5日星期四","high":"高温 19℃","fengxiang":"无持续风向","low":"低温 10℃","fengli":"<![CDATA[<3级]]>","type":"小雨"}
    private String getTheDealedDate(Forecast forecast) {
        String[] buff = forecast.getDate().split("日");
        String dealDate = buff[0];
        if (Integer.parseInt(dealDate) >= 1 && Integer.parseInt(dealDate) <= 9) {
            dealDate = "0" + dealDate;
        }
        return dealDate;
    }
    //计算两个日期间的间隔天数
    private int getTermDay(String start, String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startSdf =sdf.parse(start);
        Date endSdf = sdf.parse(end);
        /*规整日期
        String sstartSdf = sdf.format(startSdf);
        startSdf = sdf.parse(sstartSdf);
        String sendSdf = sdf.format(endSdf);
        endSdf = sdf.parse(sendSdf);
        */
        long days = (endSdf.getTime() - startSdf.getTime()) / (24*3600*1000);
        return (int)days;
    }

}
