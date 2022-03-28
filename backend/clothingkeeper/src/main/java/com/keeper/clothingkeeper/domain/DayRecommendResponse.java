package com.keeper.clothingkeeper.domain;

public class DayRecommendResponse {
    private Centigrade centigrade;//温度
    private ClothesResponse clothesResponse;
    private String massage;

    public Centigrade getCentigrade() {
        return centigrade;
    }

    public void setCentigrade(Centigrade centigrade) {
        this.centigrade = centigrade;
    }

    public ClothesResponse getClothesResponse() {
        return clothesResponse;
    }

    public void setClothesResponse(ClothesResponse clothesResponse) {
        this.clothesResponse = clothesResponse;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
