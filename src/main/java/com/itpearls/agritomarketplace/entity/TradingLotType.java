package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum TradingLotType implements EnumClass<String> {

    BUY("Покупка"),
    SALE("Продажа"),
    EXCHANGE("Обмен");

    private String id;

    TradingLotType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TradingLotType fromId(String id) {
        for (TradingLotType at : TradingLotType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}