package com.gildedrose.k.updatestrategy;

import com.gildedrose.k.Item;

/**
 * Update Strategy for Conjured {@link Item} category
 */
public class ConjuredCategoryUpdateStrategy extends AbstractBaseItemUpdateStrategy {

	private static int CONJURED_DECREASE_STEPS = BASE_DECREASE_STEPS * 2;
	
	@Override
	public void updateItem(Item itemToUpdate) {
		
		decreaseItemQuality(itemToUpdate, CONJURED_DECREASE_STEPS);
		
		reduceItemSellInDays(itemToUpdate);
		
		handleItemWithSellByDatePassed(itemToUpdate);
	}

	@Override
	protected void reduceItemQualityForExpiredItems(Item itemToUpdate) {
		
		decreaseItemQuality(itemToUpdate, CONJURED_DECREASE_STEPS);
	}
	
}
