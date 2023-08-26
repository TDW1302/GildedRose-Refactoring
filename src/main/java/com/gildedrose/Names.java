package com.gildedrose;

public enum Names {

    SULFURAS("Sulfuras, Hand of Ragnaros"),
    AGED_BRIE("Aged Brie");

    private final String value;

    Names(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }
}
