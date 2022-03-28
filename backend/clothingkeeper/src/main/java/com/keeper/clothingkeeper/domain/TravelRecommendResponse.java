package com.keeper.clothingkeeper.domain;

import java.util.ArrayList;
import java.util.List;

public class TravelRecommendResponse {

    private List<ClothesResponse> travelRecommend;//旅行推荐的衣服
    private String ganmao;//感冒提醒信息
    private String message;

    public TravelRecommendResponse(List<ClothesResponse> travelRecommend, String ganmao, String message) {
        travelRecommend = travelRecommend;
        this.ganmao = ganmao;
        this.message = message;
    }

    public TravelRecommendResponse() {
        this.travelRecommend = new ArrayList<ClothesResponse>();
        this.ganmao = "无信息";
        this.message = null;
    }

    public List<ClothesResponse> getTravelRecommend() {
        return travelRecommend;
    }

    public void setTravelRecommend(List<ClothesResponse> travelRecommend) {
        this.travelRecommend.addAll(travelRecommend);
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
