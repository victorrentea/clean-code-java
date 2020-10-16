package com.gildedrose.k.updatestrategy;


import com.gildedrose.k.Item;

/**
 * Update Strategy for Backstage Passes {@link Item} category
 */
public class BackstagePassesCategoryUpdateStrategy extends AbstractBaseItemUpdateStrategy{
		
	private static final int INCREASE_BY_TWO = 2;
	private static final int INCREASE_BY_THREE = 3;
	
	@Override
	public void updateItem(Item itemToUpdate) {
		
		final int increaseSteps = calculateIncreaseSteps(itemToUpdate.sellIn);
		increaseItemQuality(itemToUpdate, increaseSteps);
		
		reduceItemSellInDays(itemToUpdate);

		handleItemWithSellByDatePassed(itemToUpdate);
	}
		
	@Override
	protected void reduceItemQualityForExpiredItems(Item itemToUpdate) {
		
		setItemQualityToMinimum(itemToUpdate);
	}
	
	private int calculateIncreaseSteps(int sellInDays) {
	
		int increaseSteps = BASE_INCREASE_STEPS;
		
		if (checkBetweenSixAndTenDaysRemaining(sellInDays)) {
			
			increaseSteps =  INCREASE_BY_TWO;
		}
		else if (checkLessThanFiveDaysRemaining(sellInDays)) {
			
			increaseSteps =  INCREASE_BY_THREE;
		}
		
		return increaseSteps;
	}

	private void setItemQualityToMinimum(final Item itemToUpdate) {
		
		itemToUpdate.quality = MINIMUM_QUALITY;
	}

	private boolean checkBetweenSixAndTenDaysRemaining(final int sellInDays) {
		
		return sellInDays >=6 && sellInDays <=10;	
	}
	
	private boolean checkLessThanFiveDaysRemaining(final int sellInDays) {
		
		return sellInDays <=6;	
	}
}
