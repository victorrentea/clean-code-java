package com.gildedrose.m;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

	private static final String CONJURED_ITEM = "Conjured Mana Cake";
	private static final String BACKSTAGE_ITEM = "Backstage passes to a TAFKAL80ETC concert";
	private static final String SULFURAS_ITEM = "Sulfuras, Hand of Ragnaros";
	private static final String AGED_BRIE_ITEM = "Aged Brie";
	private static final String NORMAL_ITEM = "Normal";
	
	
	private static final int SELL_BY_DATE = 0;
	private static final int DUMMY_SELL_IN_DAYS = 42;
	private static final int DAYS_BEFORE_SELL_IN_10 = 10;
	private static final int DAYS_BEFORE_SELL_IN_5 = 5;
	private static final int DUMMY_QUALITY = 10;
	private static final int MAX_QUALITY = 50;
	private static final int MIN_QUALITY = 0;
	private static final int SULFURAS_QUALITY = 80;

	@Test
	public void updateQuality_WithNormalItemAtTheEndOfTheDay_SellInDecreasedByOne() {

		int expectedSellIn = DUMMY_SELL_IN_DAYS - 1;
		Item[] items = new Item[] { new Item(NORMAL_ITEM, DUMMY_SELL_IN_DAYS, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(expectedSellIn, app.items[0].sellIn);
	}

	@Test
	public void updateQuality_WithNormalItemAtTheEndOfTheDay_QualityDecreasedByOne() {

		int expectedQuality = DUMMY_QUALITY - 1;
		Item[] items = new Item[] { new Item(NORMAL_ITEM, DUMMY_SELL_IN_DAYS, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithNormalItemAtTheEndOfTheSecondDay_SellInAndQualityDecreasedByTwo() {

		int expectedSellIn = DUMMY_SELL_IN_DAYS - 2;
		int expectedQuality = DUMMY_QUALITY - 2;
		Item[] items = new Item[] { new Item(NORMAL_ITEM, DUMMY_SELL_IN_DAYS, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(expectedSellIn, app.items[0].sellIn);
		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithNormalItemAndSellByDatePassedOneDay_QualityDecreasedByTwo() {

		int expectedQuality = DUMMY_QUALITY - 2;
		Item[] items = new Item[] { new Item(NORMAL_ITEM, SELL_BY_DATE, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithNormalItemAndSellByDateWithPassedTwoDays_QualityDecreasedByFour() {

		int expectedQuality = DUMMY_QUALITY - 4;
		Item[] items = new Item[] { new Item(NORMAL_ITEM, SELL_BY_DATE, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_HavingNormalItemWithNoQuality1AndSellInDatePassedForTwoDays_QualityStaysZerro() {

		int expectedQuality = 0;
		Item[] items = new Item[] { new Item(NORMAL_ITEM, SELL_BY_DATE, 0) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithAgedBrieItemAtTheEndOfTheDay_SellInDecreasedByOne() {

		int expectedSellIn = DUMMY_SELL_IN_DAYS - 1;
		Item[] items = new Item[] { new Item(AGED_BRIE_ITEM, DUMMY_SELL_IN_DAYS, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(expectedSellIn, app.items[0].sellIn);
	}

	@Test
	public void updateQuality_WithAgedBrieItemAtTheEndOfTheSecondDay_SellInDecreasedByTwo() {

		int expectedSellIn = DUMMY_SELL_IN_DAYS - 2;
		Item[] items = new Item[] { new Item(AGED_BRIE_ITEM, DUMMY_SELL_IN_DAYS, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(expectedSellIn, app.items[0].sellIn);
	}

	@Test
	public void updateQuality_WithAgedBrieItemAtTheEndOfTheDay_QualityIncreasedByOne() {

		int expectedQuality = DUMMY_QUALITY + 1;
		Item[] items = new Item[] { new Item(AGED_BRIE_ITEM, DUMMY_SELL_IN_DAYS, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithAgedBrieItemHAvingMaxQualityAtTheEndOfTheDay_MaxQualityCantBeIncreased() {

		int expectedQuality = MAX_QUALITY;
		Item[] items = new Item[] { new Item(AGED_BRIE_ITEM, DUMMY_SELL_IN_DAYS, MAX_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithAgedBrieItemWithMaximumQualityAtTheEndOfTheSecondDay_MaxQualityCantBeIncreased() {

		int expectedQuality = MAX_QUALITY;
		Item[] items = new Item[] { new Item(AGED_BRIE_ITEM, DUMMY_SELL_IN_DAYS, MAX_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}
	
	@Test
	public void updateQuality_WithAgedBrieItemWithMaximumQualityAndPassedSellByDay_MaxQualityCantBeIncreased() {

		int expectedQuality = MAX_QUALITY;
		Item[] items = new Item[] { new Item(AGED_BRIE_ITEM, SELL_BY_DATE, MAX_QUALITY-1) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithSulfurasItemAtTheEndOfTheDay_SellInNeverDecreasesAndQuantityStaysSame() {

		Item[] items = new Item[] { new Item(SULFURAS_ITEM, DUMMY_SELL_IN_DAYS, SULFURAS_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(DUMMY_SELL_IN_DAYS, app.items[0].sellIn);
		assertEquals(SULFURAS_QUALITY, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithSulfurasItemAtTheEndOfTheSecondDay_SellInNeverDecreasesAndQuantityStaysSame() {

		Item[] items = new Item[] { new Item(SULFURAS_ITEM, DUMMY_SELL_IN_DAYS, SULFURAS_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(DUMMY_SELL_IN_DAYS, app.items[0].sellIn);
		assertEquals(SULFURAS_QUALITY, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithBackStageItemAtTheEndOfTheDay_SellInDecreasedByOne() {

		int expectedSellIn = DUMMY_SELL_IN_DAYS - 1;
		Item[] items = new Item[] {
				new Item(BACKSTAGE_ITEM, DUMMY_SELL_IN_DAYS, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(expectedSellIn, app.items[0].sellIn);
	}

	@Test
	public void updateQuality_WithBackStageItemAtTheEndOfTheSecondDay_SellInDecreasedByTwo() {

		int expectedSellIn = DUMMY_SELL_IN_DAYS - 2;
		Item[] items = new Item[] {
				new Item(BACKSTAGE_ITEM, DUMMY_SELL_IN_DAYS, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(expectedSellIn, app.items[0].sellIn);
	}

	@Test
	public void updateQuality_WithBackStageItemAtTheEndOfTheDayAnd10DaysBeforeSellIn_QualityIncreasedByTwo() {

		int expectedQuality = DUMMY_QUALITY + 2;
		Item[] items = new Item[] {
				new Item(BACKSTAGE_ITEM, DAYS_BEFORE_SELL_IN_10, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithBackStageItemAtTheEndOfTheSecondDayAndLessThan10DaysBeforeSellIn_QualityIncreasedByTwoEachNextDay() {

		int expectedQuality = DUMMY_QUALITY + 4;
		Item[] items = new Item[] {
				new Item(BACKSTAGE_ITEM, DAYS_BEFORE_SELL_IN_10 - 1, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithBackStageItemAtTheEndOfTheDayAnd5DaysBeforeSellIn_QualityIncreasedByThree() {

		int expectedQuality = DUMMY_QUALITY + 3;
		Item[] items = new Item[] {
				new Item(BACKSTAGE_ITEM, DAYS_BEFORE_SELL_IN_5, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithBackStageItemAtTheEndOfTheSecondDayAndLessThan5DaysBeforeSellIn_QualityIncreasedByThreeEachNextDay() {

		int expectedQuality = DUMMY_QUALITY + 6;
		Item[] items = new Item[] {
				new Item(BACKSTAGE_ITEM, DAYS_BEFORE_SELL_IN_5 - 1, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithBackStageItemAtTheEndOfTheSecondDayAndLessThan5DaysBeforeSellIn_QualityCantBeMoreThan50() {

		Item[] items = new Item[] {
				new Item(BACKSTAGE_ITEM, DAYS_BEFORE_SELL_IN_5 - 1, MAX_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(MAX_QUALITY, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithBackStageItemAfterSellByDate_QualityIsZerro() {

		Item[] items = new Item[] { new Item(BACKSTAGE_ITEM, SELL_BY_DATE, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(MIN_QUALITY, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithBackStageItemTheNextAfterSellByDate_QualityIsZerro() {

		int expectedQuality = 0;
		Item[] items = new Item[] { new Item(BACKSTAGE_ITEM, SELL_BY_DATE, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithAgedBrieItemAfterSellByDate_QualityIncreaseTwiceFaster() {

		int expectedQuality = DUMMY_QUALITY + 2;
		Item[] items = new Item[] { new Item(AGED_BRIE_ITEM, SELL_BY_DATE, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}
	
	@Test
	public void updateQuality_WithConjuredItemAtTheEndOfTheDay_QualityDecreasedByOne() {

		int expectedQuality = DUMMY_QUALITY - 2;
		Item[] items = new Item[] { new Item(CONJURED_ITEM, DUMMY_SELL_IN_DAYS, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}
	
	@Test
	public void updateQuality_WithConjuredItemAtTheEndOfTheSecondDay_SellInAndQualityDecreasedByTwo() {

		int expectedSellIn = DUMMY_SELL_IN_DAYS - 2;
		int expectedQuality = DUMMY_QUALITY - 4;
		Item[] items = new Item[] { new Item(CONJURED_ITEM, DUMMY_SELL_IN_DAYS, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(expectedSellIn, app.items[0].sellIn);
		assertEquals(expectedQuality, app.items[0].quality);
	}
	
	@Test
	public void updateQuality_WithConjuredItemAndSellByDatePassedOneDay_QualityDecreasedByTwo() {

		int expectedQuality = DUMMY_QUALITY - 4;
		Item[] items = new Item[] { new Item(CONJURED_ITEM, SELL_BY_DATE, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}

	@Test
	public void updateQuality_WithConjuredItemAndSellByDateWithPassedTwoDays_QualityDecreasedByFour() {

		int expectedQuality = DUMMY_QUALITY - 8;
		Item[] items = new Item[] { new Item(CONJURED_ITEM, SELL_BY_DATE, DUMMY_QUALITY) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();
		app.updateQuality();

		assertEquals(expectedQuality, app.items[0].quality);
	}
	
	@Test
	public void updateQuality_WithConjuredItemHavingQualityOneAndSellByDatePassedOneDay_QualityCantGetLessThanZerro() {

		Item[] items = new Item[] { new Item(CONJURED_ITEM, SELL_BY_DATE, 2) };
		GildedRose app = new GildedRose(items);

		app.updateQuality();

		assertEquals(MIN_QUALITY, app.items[0].quality);
	}
}
