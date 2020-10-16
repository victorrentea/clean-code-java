package com.gildedrose.k.updatestrategy;

import com.gildedrose.k.Item;

/**
 * Interface for updating an {@link Item}
 */
@FunctionalInterface
public interface IItemUpdateStrategy {
	
	/**
	 * Updates quality and sellIn days properties of an Item
	 * 
	 * @param itemToUpdate the {@link Item} to update
	 */
	void updateItem(Item itemToUpdate);
}
