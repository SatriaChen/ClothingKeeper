package com.keeper.clothingkeeper.dto;

import lombok.Data;

import java.util.List;

/**
 * @program: clothingkeeper
 * @description: ClothForShowDTO 用于返回前端的DTO
 * @author: Vaskka
 * @create: 2020/3/7 7:39 PM
 **/

@Data
public class ClothForShowDTO {
    /**
     * 关联的用户id
     */
    private String userId;

    private String clothId;

    private String cTypeid;

    private String cSeason;

    private String cStyle;

    private String cState;

    private Integer cFrequences;

    private String cTag;

    private String cRfid;

    private String cClothImgUrl;

    public ClothForShowDTO(String userId,String clothId,String cTypeid,String cSeason,String cStyle, String cState,Integer cFrequences,String cTag,String cRfid, String cClothImgUrl){
        this.userId=userId;
        this.clothId=clothId;
        this.cTypeid=cTypeid;
        this.cSeason=cSeason;
        this.cStyle=cStyle;
        this.cState=cState;
        this.cFrequences=cFrequences;
        this.cTag=cTag;
        this.cRfid=cRfid;
        this.cClothImgUrl=cClothImgUrl;
    }
    public String getClothId() {
        return clothId;
    }

    public void setClothId(String cClothesid) {
        this.clothId = cClothesid == null ? null : cClothesid.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String cUid) {
        this.userId= cUid == null ? null : cUid.trim();
    }


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
        if(cSeason.equals("0"))
        {
            this.cSeason="春秋";
        }else if (cSeason.equals("1")){
            this.cSeason="夏季";
        }else if (cSeason.equals("2")){
            this.cSeason="冬季";
        }
    }

    public String getcStyle() {
        return cStyle;
    }

    public void setcStyle(String cStyle) {
        if(cStyle.equals("0"))
        {
            this.cStyle="正式";
        }else if (cStyle.equals("1")){
            this.cStyle="休闲";
        }else if (cStyle.equals("2")){
            this.cStyle="运动";
        }
    }

    public String getcState() {
        return cState;
    }

    public void setcState(String cState) {
        if(cState.equals("0"))
        {
            this.cState="可穿";
        }else if (cState.equals("1")){
            this.cState="待洗";
        }
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

    public String getcClothImgUrl() {
        return cClothImgUrl;
    }

    public void setcClothImgUrl(String CclothImg) {
        this.cClothImgUrl = CclothImg == null ? null : CclothImg.trim();
    }
}
