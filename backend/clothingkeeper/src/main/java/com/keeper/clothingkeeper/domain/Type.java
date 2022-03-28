package com.keeper.clothingkeeper.domain;

public class Type extends TypeKey {
    private String tTag;

    public String gettTag() {
        return tTag;
    }

    public void settTag(String tTag) {
        this.tTag = tTag == null ? null : tTag.trim();
    }
}