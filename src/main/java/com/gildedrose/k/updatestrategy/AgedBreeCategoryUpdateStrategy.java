package com.gildedrose.k.updatestrategy;

import com.gildedrose.k.Item;

/**
 * Update Strategy for AgedBree {@link Item} category
 */
public class AgedBreeCategoryUpdateStrategy extends AbstractBaseItemUpdateStrategy{

	
	@Override
	public void updateItem(Item itemToUpdate) {
		
		increaseItemQuality(itemToUpdate, BASE_INCREASE_STEPS);    
		
	    reduceItemSellInDays(itemToUpdate);
	
	    handleItemWithSellByDatePassed(itemToUpdate);
	}

	@Override
	protected void reduceItemQualityForExpiredItems(Item itemToUpdate) {
	
		decreaseItemQuality(itemToUpdate, BASE_DECREASE_STEPS);
	}
			
}
