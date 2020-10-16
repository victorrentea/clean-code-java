package com.gildedrose.m;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

class GildedRose {

	private static final String CONJURED_MANA_CAKE_ITEM_NAME = "Conjured Mana Cake";
	private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT_ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert";
	private static final String AGED_BRIE_ITEM_NAME = "Aged Brie";
	private static final String SULFURAS_HAND_OF_RAGNAROS_ITEM_NAME = "Sulfuras, Hand of Ragnaros";

	private static final int MAX_ITEM_QUALITY = 50;
	private static final int CHANGE_BY_ONE = 1;
	private static final int TWO = 2;
	private static final int CHANGE_BY_THREE = 3;
	private static final int CHANGE_BY_FOUR = 4;

	private static final Predicate<Item> IS_SELL_BY_DAY_PASSED = item -> item.sellIn < 0;

	private static final Predicate<Item> HAS_MORE_THAN_10_DAYS = item -> item.sellIn > 9;
	private static final Predicate<Item> HAS_LESS_THAN_5_DAYS = item -> item.sellIn < 5;

	private static final Function<Item, Integer> ITEMS_SELL_IN_UPDATE_STRATEGY = GildedRose::defaultSellInUpdate;
	private static final Function<Item, Integer> NORMAL_ITEM_QUALITY_UPDATE_STRATEGY_BEFORE_SELL_BY_DAY = GildedRose::computeNormalItemQualityBeforeSellByDay;
	private static final Function<Item, Integer> NORMAL_ITEM_QUALITY_UPDATE_STRATEGY_AFTER_SELL_BY_DAY = GildedRose::computeNormalItemQualityAfterSellByDay;

	Item[] items;

	private Map<String, Function<Item, Integer>> updateSellInStrategy;
	private Map<String, Function<Item, Integer>> updateQualityStrategiesBeforeSellByDay;
	private Map<String, Function<Item, Integer>> updateQualityStrategiesAfterSellByDay;

	public GildedRose(Item[] items) {

		this.items = items;

		updateSellInStrategy = new HashMap<>();
		updateSellInStrategy.put(SULFURAS_HAND_OF_RAGNAROS_ITEM_NAME, this::computSulfurasSellIn);

		updateQualityStrategiesBeforeSellByDay = new HashMap<>();
		updateQualityStrategiesBeforeSellByDay.put(SULFURAS_HAND_OF_RAGNAROS_ITEM_NAME, this::computeSulfurasQuality);
		updateQualityStrategiesBeforeSellByDay.put(AGED_BRIE_ITEM_NAME, this::computeAgedBrieQualityBeforeSellByDay);
		updateQualityStrategiesBeforeSellByDay.put(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT_ITEM_NAME,
				this::computeBackstageQualityBeforeSellByDay);
		updateQualityStrategiesBeforeSellByDay.put(CONJURED_MANA_CAKE_ITEM_NAME,
				this::computeConjuredQualityBeforeSellByDay);

		updateQualityStrategiesAfterSellByDay = new HashMap<>();
		updateQualityStrategiesAfterSellByDay.put(SULFURAS_HAND_OF_RAGNAROS_ITEM_NAME, this::computeSulfurasQuality);
		updateQualityStrategiesAfterSellByDay.put(AGED_BRIE_ITEM_NAME, this::computeAgedBrieQualityAfterSellByDay);
		updateQualityStrategiesAfterSellByDay.put(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT_ITEM_NAME,
				this::computeBackstageQualityAfterSellByDay);
		updateQualityStrategiesAfterSellByDay.put(CONJURED_MANA_CAKE_ITEM_NAME,
				this::computeConjuredQualityAfterSellByDay);
	}

	public void updateQuality() {
		for (Item item : items) {
			updateItemSellInDay(item);
			updateItemQuality(item);
		}
	}

	private void updateItemSellInDay(Item item) {

		Function<Item, Integer> sellInUpdateStrategy = getSellInUpdateStrategy(item.name);

		item.sellIn = sellInUpdateStrategy.apply(item);
	}

	private void updateItemQuality(Item item) {

		if (IS_SELL_BY_DAY_PASSED.test(item)) {

			Function<Item, Integer> qualityUpdateStrategyAfterSellByDay = getQualityUpdateStrategyAfterSellByDay(
					item.name);

			item.quality = qualityUpdateStrategyAfterSellByDay.apply(item);

		} else {

			Function<Item, Integer> qualityUpdateStrategyBeforeSellByDay = getQualityUpdateStrategyBeforeSellByDay(
					item.name);

			item.quality = qualityUpdateStrategyBeforeSellByDay.apply(item);
		}
	}

	private Function<Item, Integer> getSellInUpdateStrategy(String itemName) {

		return updateSellInStrategy.getOrDefault(itemName, ITEMS_SELL_IN_UPDATE_STRATEGY);
	}

	private Function<Item, Integer> getQualityUpdateStrategyBeforeSellByDay(String itemName) {

		return updateQualityStrategiesBeforeSellByDay.getOrDefault(itemName,
				NORMAL_ITEM_QUALITY_UPDATE_STRATEGY_BEFORE_SELL_BY_DAY);
	}

	private Function<Item, Integer> getQualityUpdateStrategyAfterSellByDay(String itemName) {

		return updateQualityStrategiesAfterSellByDay.getOrDefault(itemName,
				NORMAL_ITEM_QUALITY_UPDATE_STRATEGY_AFTER_SELL_BY_DAY);
	}

	private int computSulfurasSellIn(Item item) {

		return item.sellIn;
	}

	private int computeSulfurasQuality(Item item) {

		return 80;
	}

	private int computeAgedBrieQualityBeforeSellByDay(Item item) {

		return computeIncreasedQuality(item.quality, CHANGE_BY_ONE);
	}

	private int computeAgedBrieQualityAfterSellByDay(Item item) {

		return computeIncreasedQuality(item.quality, TWO);
	}

	private int computeBackstageQualityBeforeSellByDay(Item item) {

		int quality;

		if (HAS_MORE_THAN_10_DAYS.test(item)) {

			quality = computeIncreasedQuality(item.quality, CHANGE_BY_ONE);

		} else {

			quality = computeBackstageSpecialQuality(item);
		}

		return quality;
	}

	private int computeBackstageQualityAfterSellByDay(Item item) {
		return 0;
	}

	private int computeBackstageSpecialQuality(Item item) {
		if (item.sellIn < 5) {
			return computeIncreasedQuality(item.quality, CHANGE_BY_THREE);
		}

		return computeIncreasedQuality(item.quality, TWO);
	}

	private int computeConjuredQualityBeforeSellByDay(Item item) {

		return computeDecreasedQuality(item.quality, TWO);
	}

	private int computeConjuredQualityAfterSellByDay(Item item) {

		return computeDecreasedQuality(item.quality, CHANGE_BY_FOUR);
	}

	private int computeIncreasedQuality(int quality, int delta) {
		return Math.min(50, quality + delta);
	}

	private static int defaultSellInUpdate(Item item) {
		return item.sellIn - 1;
	}

	private static int computeNormalItemQualityBeforeSellByDay(Item item) {
		return computeDecreasedQuality(item.quality, CHANGE_BY_ONE);
	}

	private static int computeNormalItemQualityAfterSellByDay(Item item) {
		return computeDecreasedQuality(item.quality, 2);
	}

	private static int computeDecreasedQuality(int quality, int delta) {
		return Math.max(0, quality - delta);
	}

}
