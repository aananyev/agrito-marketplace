package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum BiddingStatus implements EnumClass<String> {

    APPROVE("Согласована цена"),
    КEJECT("Цена отклонена");

    private String id;

    BiddingStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static BiddingStatus fromId(String id) {
        for (BiddingStatus at : BiddingStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}