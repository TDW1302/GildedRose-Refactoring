package com.gildedrose;

import java.util.List;

class GildedRose {
    static {
        String fileName = "legendary";
        legendaries = FileHelper.readItemsFromResource(fileName);

        fileName = "aged";
        aged = FileHelper.readItemsFromResource(fileName);

        fileName = "backstage";
        backstage = FileHelper.readItemsFromResource(fileName);

        fileName = "conjured";
        conjured = FileHelper.readItemsFromResource(fileName);
    }


    static List<String> legendaries;
    static List<String> aged;
    static List<String> conjured;
    static List<String> backstage;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            String itemName = item.name;
            if (legendaries.contains(itemName)) {
                //Nothing to do because nothing changes for legendary stuff
            } else if (aged.contains(itemName)) {
                handleAged(item);
            } else if (backstage.contains(itemName)) {
                handleBackstage(item);
            } else if (conjured.contains(itemName)) {
                handleConjured(item);
            } else { // regular stuff
                handleRegular(item);
            }
        }
    }

    private void handleRegular(Item item) {
        item.sellIn--;
        item.quality = Math.max(0, item.sellIn < 0 ?  item.quality-2 : item.quality-1);
    }

    private void handleConjured(Item item) {
        item.sellIn--;
        item.quality = Math.max(0, (item.sellIn < 0 ?  item.quality-4 : item.quality-2));

    }

    private void handleBackstage(Item item) {
        item.sellIn--;
        if(item.sellIn < 0 ){
            item.quality = 0;
        } else if (item.sellIn < 5 ){
            item.quality = Math.min(50, item.quality+3);
        } else if (item.sellIn < 10 ){
            item.quality = Math.min(50, item.quality+2);
        } else {
            item.quality++;
        }
    }

    private void handleAged(Item item) {
        item.sellIn--;
        item.quality = Math.min(50, item.quality+1);
    }
}
