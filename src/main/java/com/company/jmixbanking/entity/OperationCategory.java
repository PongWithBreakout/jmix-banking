package com.company.jmixbanking.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum OperationCategory implements EnumClass<Integer> {

    OTHER(10),
    FARMACY(20),
    GAS(30);

    private Integer id;

    OperationCategory(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static OperationCategory fromId(Integer id) {
        for (OperationCategory at : OperationCategory.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}