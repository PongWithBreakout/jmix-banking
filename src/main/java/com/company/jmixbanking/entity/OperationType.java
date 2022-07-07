package com.company.jmixbanking.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum OperationType implements EnumClass<Integer> {

    OPERATION_DEPOSIT(10),
    OPERATION_WITHDRAW(20);

    private Integer id;

    OperationType(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static OperationType fromId(Integer id) {
        for (OperationType at : OperationType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}