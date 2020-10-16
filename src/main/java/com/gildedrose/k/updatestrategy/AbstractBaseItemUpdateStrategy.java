package com.gildedrose.k.updatestrategy;

import com.gildedrose.k.Item;

/**
 * Provides base common functionality for all update strategies
 */
public abstract class AbstractBaseItemUpdateStrategy implements IItemUpdateStrategy {
	
	protected static int MINIMUM_QUALITY = 0;
	protected static int MAXIMUM_QUALITY = 50;
	
	protected static int BASE_INCREASE_STEPS = 1;
	protected static int BASE_DECREASE_STEPS = 1;
	
	protected abstract void reduceItemQualityForExpiredItems(Item itemToUpdate);
	
	protected void reduceItemSellInDays(final Item itemToUpdate) {
		
		itemToUpdate.sellIn -=  1;
	}
	
	protected void increaseItemQuality(final Item itemToUpdate, final int increaseSteps) {
		
		for (int step = 1; step <= increaseSteps; step++) {
			
			increaseItemQualityByOne(itemToUpdate);
		}	
	}
	
	protected void decreaseItemQuality(final Item itemToUpdate, final int decreaseSteps) {
		
		for (int step = 1; step <= decreaseSteps; step++) {
			
			decreaseItemQualityByOne(itemToUpdate);
		}
	}
	
	protected boolean hasSellByDatePassed(final Item itemToUpdate) {

		return itemToUpdate.sellIn < 0;
	}

	protected void handleItemWithSellByDatePassed(final Item itemToUpdate) {
		
		if (hasSellByDatePassed(itemToUpdate)) {

			reduceItemQualityForExpiredItems(itemToUpdate);
		}
	}
	
	private void decreaseItemQualityByOne(final Item itemToUpdate) {
		
		if (itemToUpdate.quality > MINIMUM_QUALITY) {

			itemToUpdate.quality -= 1;
		}
	}
	
	private void increaseItemQualityByOne(final Item itemToUpdate) {
		
		if (itemToUpdate.quality < MAXIMUM_QUALITY) {
		
			itemToUpdate.quality += 1;
		}
	}
		
}
