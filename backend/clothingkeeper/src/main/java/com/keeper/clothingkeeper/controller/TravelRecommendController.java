package com.keeper.clothingkeeper.controller;


import com.auth0.jwt.JWT;
import com.keeper.clothingkeeper.annotation.UserLoginToken;
import com.keeper.clothingkeeper.domain.TravelRecommendResponse;
import com.keeper.clothingkeeper.service.TravelRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController

public class TravelRecommendController {

    @Autowired
    private TravelRecommendService TravelRecommendService;

    @UserLoginToken
    @GetMapping("/api/travel")
    public TravelRecommendResponse getTravelRecommendByCityName(String city, String startdate, String enddate, HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        String userId;
        userId = JWT.decode(token).getAudience().get(0);
        return TravelRecommendService.getTravelRecommendByCityName(city, startdate, enddate,userId);

    }
}
