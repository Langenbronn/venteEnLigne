package com.venteEnLigne.venteEnLigne.model;

public enum Gender {
    Man("Man"),
    Woman("Woman"),
    Other("Other");

    private final String value;

    Gender(String value) {
        this.value = value;
    }
}
