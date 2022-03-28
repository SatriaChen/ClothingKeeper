package com.keeper.clothingkeeper.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 将衣服按类型返回
 * 上装：0 下装：1 套装：2 其他：3
 */
public class ClothesResponse {
    private List<Clothes> upper;//上装
    private List<Clothes> bottom;//下装
    private List<Clothes> suit;//套装
    private List<Clothes> elseCloth;//其他

    public ClothesResponse() {
        this.upper = new ArrayList<Clothes>();
        this.bottom = new ArrayList<Clothes>();
        this.suit = new ArrayList<Clothes>();
        this.elseCloth = new ArrayList<Clothes>();
    }

    public List<Clothes> getUpper() {
        return upper;
    }

    public void setUpper(List<Clothes> upper) {
        this.upper.addAll(upper);

    }

    public List<Clothes> getBottom() {
        return bottom;
    }

    public void setBottom(List<Clothes> bottom) {
        this.bottom.addAll(bottom);
    }

    public List<Clothes> getSuit() {
        return suit;
    }

    public void setSuit(List<Clothes> suit) {
        this.suit.addAll(suit);
    }

    public List<Clothes> getElseCloth() {
        return elseCloth;
    }

    public void setElseCloth(List<Clothes> elseCloth) {
        this.elseCloth.addAll(elseCloth);
    }
}
