package com.keeper.clothingkeeper.domain;
import java.io.Serializable;
/**
 * 响应数据
 */
public class WeatherResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Weather data;//消息数据
    private Integer status;//消息状态
    private String desc;//消息描述

    public Weather getData() {
        return data;
    }

    public void setData(Weather data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
