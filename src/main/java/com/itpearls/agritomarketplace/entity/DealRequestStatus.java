package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum DealRequestStatus implements EnumClass<String> {

    NEW("Новая заявка"),
    CONSIDERATION("Рассмотрение"),
    PAUSED("Пауза"),
    PRICE_AGREEMENT("Согласование цены"),
    QUANTITY_ARGEEMENT("Согласование количества"),
    COUNTER_OFFER("Встречное предложение"),
    REJECTION("Отказ"),
    AGREEMENT("Соглашение"),
    TRANSACTION_COMPLETED("Сделка завершена");

    private String id;

    DealRequestStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DealRequestStatus fromId(String id) {
        for (DealRequestStatus at : DealRequestStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}