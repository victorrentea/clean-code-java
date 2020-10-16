package com.gildedrose.k.updatestrategy;

import com.gildedrose.k.Item;

/**
 * Update Strategy for Regular {@link Item} category
 */
public class RegularItemCategoryUpdateStrategy extends AbstractBaseItemUpdateStrategy{

	@Override
	public void updateItem(Item itemToUpdate) {
		
		decreaseItemQuality(itemToUpdate, BASE_DECREASE_STEPS);

		reduceItemSellInDays(itemToUpdate);

		handleItemWithSellByDatePassed(itemToUpdate);		
	}

	@Override
	protected void reduceItemQualityForExpiredItems(Item itemToUpdate) {
		
		decreaseItemQuality(itemToUpdate, BASE_DECREASE_STEPS);
	}
			
}
