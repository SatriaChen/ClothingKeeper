package com.keeper.clothingkeeper.domain;

public class TypeKey {
    private String tCtypeid;

    private Integer tTagid;

    public String gettCtypeid() {
        return tCtypeid;
    }

    public void settCtypeid(String tCtypeid) {
        this.tCtypeid = tCtypeid == null ? null : tCtypeid.trim();
    }

    public Integer gettTagid() {
        return tTagid;
    }

    public void settTagid(Integer tTagid) {
        this.tTagid = tTagid;
    }
}