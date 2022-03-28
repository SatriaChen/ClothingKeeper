package com.keeper.clothingkeeper.domain;
import lombok.Builder;


public class Clothes extends ClothesKey{
    private String cTypeid;

    private String cSeason;

    private String cStyle;

    private String cState;

    private Integer cFrequences;

    private String cTag;

    private String cRfid;

    private String cClothimg;

    public String getcTypeid() {
        return cTypeid;
    }

    public void setcTypeid(String cTypeid) {
        this.cTypeid = cTypeid == null ? null : cTypeid.trim();
    }

    public String getcSeason() {
        return cSeason;
    }

    public void setcSeason(String cSeason) {
        this.cSeason = cSeason;
    }

    public String getcStyle() {
        return cStyle;
    }

    public void setcStyle(String cStyle) {
        this.cStyle = cStyle;
    }

    public String getcState() {
        return cState;
    }

    public void setcState(String cState) {
        this.cState = cState;
    }

    public Integer getcFrequences() {
        return cFrequences;
    }

    public void setcFrequences(Integer cFrequences) {
        this.cFrequences = cFrequences;
    }

    public String getcRfid() {
        return cRfid;
    }

    public void setcRfid(String cRfid) {
        this.cRfid = cRfid == null ? null : cRfid.trim();
    }

    public String getcTag() {
        return cTag;
    }

    public void setcTag(String cTag) {
        this.cTag = cTag == null ? null : cTag.trim();
    }

    public String getcClothimg() {
        return cClothimg;
    }

    public void setcClothimg(String CclothImg) {
        this.cClothimg = CclothImg == null ? null : CclothImg.trim();
    }

    public Clothes(String cClothesid,String cUid,String cTypeid,String cSeason,String cStyle, String cState,Integer cFrequences,String cTag,String cRfid, String cClothImg){
        super(cClothesid,cUid);
        this.cTypeid=cTypeid;
        this.cSeason=cSeason;
        this.cStyle=cStyle;
        this.cState=cState;
        this.cFrequences=cFrequences;
        this.cTag=cTag;
        this.cRfid=cRfid;
        this.cClothimg=cClothImg;
    }
}