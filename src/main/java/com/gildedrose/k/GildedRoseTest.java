package com.gildedrose.k;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {


	/**
	 *  Note for Victor: These tests were written first thing, before starting any of the refactorings
	 *  Ideally they should be splitted as well to each strategy class with appropriate use of Mocks.
	 *  This was not done as I think the purpose of the exercise was different. 
	 */
	

    @Test
    public void updateQuality_ForNormalItemsWithSellDateNotPassed_QualityDecreasesByOneEachTime() {

    	final int testSellInDays = 4;
		final int testQuality = 3;
		final int expectedQuality = 1;
		Item testItem = new Item("normal item", testSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        int actualQuality = app.getItemsStorage()[0].quality;
		
        assertEquals(expectedQuality, actualQuality);
    }

    @Test
    public void updateQuality_ForAllItemsWithSellDateNotPassed_SellInDaysDecreasesByOneEachTime() {

    	final int tstSellInDays = 4;
		final int testQuality = 3;
		final int expectedSellIn = 2;
		Item testItem = new Item("normal item", tstSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        int actualSellIn = app.getItemsStorage()[0].sellIn;
		
        assertEquals(expectedSellIn, actualSellIn);
    }


    @Test
    public void updateQuality_ForNormalItemsItemsWithSellDatePassed_QualityDecreasesByTwoEachTime() {

    	final int testSellInDays = 0;
		final int testQuality = 6;
		final int expectedQuality = 2;
		Item testItem = new Item("normal item", testSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        final int actualQuality = app.getItemsStorage()[0].quality;
		
        assertEquals(expectedQuality, actualQuality);
    }


    @Test
    public void updateQuality_ForItemsWithQualityAlreadyZero_QualityDoesNotBecomeNegativeAfterConsecutiveUpdates() {

    	final int testSellInDays = 1;
		final int testQuality = 0;
		final int expectedQuality = 0;
		Item testItem = new Item("normal item", testSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        final int actualQuality = app.getItemsStorage()[0].quality;
		
        assertEquals(expectedQuality, actualQuality);
    }

    @Test
    public void updateQuality_ForItemsWithQualityOneAndSellDateHasPassed_QualityDoesNotBecomeNegativeAfterConsecutiveUpdates() {

    	final int testSellInDays = 0;
		final int testQuality = 1;
		final int expectedQuality = 0;
		Item testItem = new Item("normal item", testSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        final int actualQuality = app.getItemsStorage()[0].quality;
		
        assertEquals(expectedQuality, actualQuality);
    }
    
    @Test
    public void updateQuality_ForAgedBrieItems_QualityIncreasesWhileGettingOlder() {

    	final int testSellInDays = 4;
		final int testQuality = 4;
		final int expectedQuality = 6;
		Item testItem = new Item("Aged Brie", testSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        final int actualQuality = app.getItemsStorage()[0].quality;
		
        assertEquals(expectedQuality, actualQuality);
    }
    
    @Test
    public void updateQuality_ForItemsWhereQualityIncreasesByTime_QualityNeverGetsMoreThan50() {

    	final int testSellInDays = 4;
		final int testQuality = 49;
		final int expectedQuality = 50;
		Item testItem = new Item("Aged Brie", testSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        final int actualQuality = app.getItemsStorage()[0].quality;
		
        assertEquals(expectedQuality, actualQuality);
    }
    
    @Test
    public void updateQuality_ForSulfurasItems_QualityNeverDecreases() {

    	final int testSellInDays = 0;
		final int testQuality = 80;
		final int expectedQuality = 80;
		Item testItem = new Item("Sulfuras, Hand of Ragnaros", testSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        final int actualQuality = app.getItemsStorage()[0].quality;
		
        assertEquals(expectedQuality, actualQuality);
    }
    
    @Test
    public void updateQuality_ForBackstagePassesWhenThereAreMoreThan10DaysRemaining_QualityIncreasesByOneEachTime() {

    	final int testSellInDays = 12;
		final int testQuality = 20;
		final int expectedQuality = 22;
		Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", testSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        final int actualQuality = app.getItemsStorage()[0].quality;
		
        assertEquals(expectedQuality, actualQuality);
    }
    
    @Test
    public void updateQuality_ForBackstagePassesWhenThereAre10DaysOrLessRemaining_QualityIncreasesByTwoEachTime() {

    	final int testSellInDays = 10;
		final int testQuality = 20;
		final int expectedQuality = 24;
		Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", testSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        final int actualQuality = app.getItemsStorage()[0].quality;
		
        assertEquals(expectedQuality, actualQuality);
    }
    
    @Test
    public void updateQuality_ForBackstagePassesWhenThereAre5DaysOrLessRemaining_QualityIncreasesByThreeEachTime() {

    	final int testSellInDays = 5;
		final int testQuality = 20;
		final int expectedQuality = 26;
		Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", testSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        final int actualQuality = app.getItemsStorage()[0].quality;
		
        assertEquals(expectedQuality, actualQuality);
    }
    
    @Test
    public void updateQuality_ForBackstagePassesWhenDateIsPassed_QualityDropsToZero() {

    	final int testSellInDays = 1;
		final int testQuality = 20;
		final int expectedQuality = 0;
		Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", testSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        final int actualQuality = app.getItemsStorage()[0].quality;
		
        assertEquals(expectedQuality, actualQuality);
    }
    
    @Test
    public void updateQuality_ForConjuredItemsWithSellDateNotPassed_QualityDecreasesByTwoEachTime() {

    	final int testSellInDays = 4;
		final int testQuality = 5;
		final int expectedQuality = 1;
		Item testItem = new Item("Conjured Mana Cake", testSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        int actualQuality = app.getItemsStorage()[0].quality;
		
        assertEquals(expectedQuality, actualQuality);
    }
    
    @Test
    public void updateQuality_ForConjuredItemsWithSellDatePassed_QualityDecreasesByFourEachTime() {

    	final int testSellInDays = 0;
		final int testQuality = 12;
		final int expectedQuality = 4;
		Item testItem = new Item("Conjured Mana Cake", testSellInDays, testQuality);
		Item[] items = new Item[] { testItem };
        GildedRose app = new GildedRose(items);
        
        app.updateQuality();
        app.updateQuality();
        final int actualQuality = app.getItemsStorage()[0].quality;
		
        assertEquals(expectedQuality, actualQuality);
    }

}
