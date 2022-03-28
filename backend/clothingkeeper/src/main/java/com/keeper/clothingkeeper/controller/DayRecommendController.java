package com.keeper.clothingkeeper.controller;

import com.auth0.jwt.JWT;
import com.keeper.clothingkeeper.annotation.UserLoginToken;
import com.keeper.clothingkeeper.domain.*;
import com.keeper.clothingkeeper.service.DayRecommendService;
import com.keeper.clothingkeeper.service.WeatherDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
//@RequestMapping("/weather")
public class DayRecommendController {
    @Autowired
    private WeatherDataService WeatherDataService;

    @Autowired
    private DayRecommendService DayRecommendService;


    /*
    public WeatherResponse get(@PathVariable("city") String city) {
        return WeatherDataService.getDataByCityName(city);
    }*/
    //@UserLoginToken
    @ApiOperation(value = "衣服推荐", notes = "衣服推荐")
    @GetMapping("/api/recommend")
    public DayRecommendResponse getDayRecommendByCityName(String city, HttpServletRequest httpServletRequest) {
        //WeatherResponse weatherResponse = weatherDataService.getDataByCityName(city);
        //String token =httpServletRequest.getHeader("Authorization");
        //String userId = JWT.decode(token).getAudience().get(0);
        String token = httpServletRequest.getHeader("Authorization");
        String userId;
        userId = JWT.decode(token).getAudience().get(0);
        return DayRecommendService.getDayRecommendByCityName(city,userId);
    }






}
