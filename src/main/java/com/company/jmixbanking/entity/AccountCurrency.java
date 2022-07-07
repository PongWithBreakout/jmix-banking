package com.company.jmixbanking.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum AccountCurrency implements EnumClass<String> {

    CURRENCY_RUB("RUB"),
    CURRENCY_USD("USD");

    private String id;

    AccountCurrency(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static AccountCurrency fromId(String id) {
        for (AccountCurrency at : AccountCurrency.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}