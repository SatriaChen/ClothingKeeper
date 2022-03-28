package com.keeper.clothingkeeper.domain;

public class Centigrade {
    private String high;//最高温
    private  String low;//最低温

    public Centigrade() {
    }

    public Centigrade(String high, String low) {
        this.high = high;
        this.low = low;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }
}
