package com.keeper.clothingkeeper.service;

import com.keeper.clothingkeeper.domain.TravelRecommendResponse;

public interface TravelRecommendService {

    TravelRecommendResponse getTravelRecommendByCityName(String city, String startdate, String enddate,String userId);
}
