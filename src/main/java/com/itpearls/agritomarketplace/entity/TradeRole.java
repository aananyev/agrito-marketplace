package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum TradeRole implements EnumClass<String> {

    BUYER("Покупатель"),
    SELLER("Продавец");

    private String id;

    TradeRole(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TradeRole fromId(String id) {
        for (TradeRole at : TradeRole.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}