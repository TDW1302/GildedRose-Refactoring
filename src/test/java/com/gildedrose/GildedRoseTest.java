package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 5, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(19, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }

    @Test
    void fooLastDay() {
        Item[] items = new Item[] { new Item("foo", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        //Currently, the quality is decreased twice : 1 as usual, and 1 because sellIn < 0
        assertEquals(18, app.items[0].quality);
    }

    @Test
    void fooQualityNeverNegative() {
        Item[] items = new Item[] { new Item("foo", 20, 5) };
        GildedRose app = new GildedRose(items);

        for (int i = 0; i < 15; i++){
            app.updateQuality();
        }
        assertEquals("foo", app.items[0].name);
        assertEquals(0, app.items[0].quality);
        assertEquals(5, app.items[0].sellIn);
    }

    @Test
    void testSulfuras() {
        String sulfurasName = Names.SULFURAS.getValue();
        Item[] items = new Item[] { new Item(sulfurasName, 100, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(sulfurasName, app.items[0].name);
        assertEquals(80, app.items[0].quality);
        assertEquals(100, app.items[0].sellIn);

        for (int i = 0; i <= 50; i++){
            app.updateQuality();
        }

        //Sulfuras should never change as it is legendary
        assertEquals(sulfurasName, app.items[0].name);
        assertEquals(80, app.items[0].quality);
        assertEquals(100, app.items[0].sellIn);

    }

    @Test
    void testAgedBrie() {
        String brieName = Names.AGED_BRIE.getValue();
        Item[] items = new Item[] { new Item(brieName, 15, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(brieName, app.items[0].name);
        assertEquals(41, app.items[0].quality);
        assertEquals(14, app.items[0].sellIn);

        for (int i = 0; i < 10; i++){
            app.updateQuality();
        }

        assertEquals(brieName, app.items[0].name);
        assertEquals(50, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);

    }

    @Test
    void testBackstage() {
        String backstageName = "Backstage passes to a TAFKAL80ETC concert";
        Item[] items = new Item[] { new Item(backstageName, 11, 35) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        int currentQuality = 36;
        int currentSellIn = 10;
        assertEquals(backstageName, app.items[0].name);
        assertEquals(currentQuality, app.items[0].quality);
        assertEquals(currentSellIn, app.items[0].sellIn);


        for (int i = 0; i <= 4; i++){
            app.updateQuality();
            currentQuality += 2;
            currentSellIn -=1;
            assertEquals(currentQuality, app.items[0].quality);
            assertEquals(currentSellIn, app.items[0].sellIn);
        }

        for (int i = 0; i <= 4; i++){
            app.updateQuality();
            currentQuality += 3;
            currentSellIn -=1;
            currentQuality = Math.min(currentQuality,50);
            assertEquals(currentQuality, app.items[0].quality);
            assertEquals(currentSellIn, app.items[0].sellIn);
        }

        assertEquals(currentQuality, 50);
        assertEquals(currentSellIn, 0);

        app.updateQuality();
        //Quality should be 0 after the concert
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }
}
