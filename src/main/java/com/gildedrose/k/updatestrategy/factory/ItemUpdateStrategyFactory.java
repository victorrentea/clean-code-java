package com.gildedrose.k.updatestrategy.factory;

import com.gildedrose.k.Item;
import com.gildedrose.k.updatestrategy.AgedBreeCategoryUpdateStrategy;
import com.gildedrose.k.updatestrategy.BackstagePassesCategoryUpdateStrategy;
import com.gildedrose.k.updatestrategy.ConjuredCategoryUpdateStrategy;
import com.gildedrose.k.updatestrategy.IItemUpdateStrategy;
import com.gildedrose.k.updatestrategy.RegularItemCategoryUpdateStrategy;

/**
 * Factory for creating {@link Item} update strategy based on the category of the Item
 * 
 */
public class ItemUpdateStrategyFactory {
	
	private static final IItemUpdateStrategy NO_OPERATION = itemToUpdate -> {};
	
	public IItemUpdateStrategy createItemUpdateStrategy(String itemName) {
		
		switch (itemName) {
			case "Aged Brie":
				return new AgedBreeCategoryUpdateStrategy();
			case "Backstage passes to a TAFKAL80ETC concert":
				return new BackstagePassesCategoryUpdateStrategy();
			case "Conjured Mana Cake":
				return new ConjuredCategoryUpdateStrategy();	
			case "Sulfuras, Hand of Ragnaros":
				return NO_OPERATION;
			default:
				return new RegularItemCategoryUpdateStrategy();
		}
	}
	
}
