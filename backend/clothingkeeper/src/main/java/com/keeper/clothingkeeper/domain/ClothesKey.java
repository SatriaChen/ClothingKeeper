package com.keeper.clothingkeeper.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClothesKey {
    protected String cClothesid;

    protected String cUid;

    public ClothesKey(String cClothesid, String cUid) {
        this.cClothesid=cClothesid;
        this.cUid=cUid;
    }

    public String getcClothesid() {
        return cClothesid;
    }

    public void setcClothesid(String cClothesid) {
        this.cClothesid = cClothesid == null ? null : cClothesid.trim();
    }

    public String getcUid() {
        return cUid;
    }

    public void setcUid(String cUid) {
        this.cUid = cUid == null ? null : cUid.trim();
    }
}