package com.gildedrose.k;

import java.util.Arrays;

import com.gildedrose.k.updatestrategy.IItemUpdateStrategy;
import com.gildedrose.k.updatestrategy.factory.ItemUpdateStrategyFactory;

/**
 * Holds the inventory of items to be updated in the end of each day
 * based on appropriate update strategy
 */
class GildedRose {
	
	private final ItemUpdateStrategyFactory itemUpdateStrategyFactory;
	
    Item[] items;

    public GildedRose(Item[] items) {
    	
        this.items = items;
        this.itemUpdateStrategyFactory = new ItemUpdateStrategyFactory();
    }

    public Item[] getItemsStorage() {
		
    	return items;
	}

	public void updateQuality() {
    	
        Arrays.asList(items).stream().forEach(this::updateItem);        
    }

	private void updateItem(final Item itemToUpdate) {
			
		IItemUpdateStrategy itemUpdateStrategy = itemUpdateStrategyFactory.createItemUpdateStrategy(itemToUpdate.name);
		
		itemUpdateStrategy.updateItem(itemToUpdate);
	}
}
