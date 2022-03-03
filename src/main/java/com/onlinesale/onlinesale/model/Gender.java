package com.onlinesale.onlinesale.model;

public enum Gender {
    Man("Man"),
    Woman("Woman"),
    Other("Other");

    private final String value;

    Gender(String value) {
        this.value = value;
    }
}
