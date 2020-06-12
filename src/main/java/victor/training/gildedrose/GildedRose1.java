package victor.training.gildedrose;

public class GildedRose1 {
   private final Item[] items;

   public GildedRose1(Item[] items) {
      this.items = items;
   }

   public void updateQuality() {
      for (Item item : items) {
         item.sellIn--;
         calculateNewQuality(item);
      }
   }

   private void calculateNewQuality(Item item) {
      switch (item.name) {
         case "Backstage passes to a TAFKAL80ETC concert":
            increaseBackstageQuality(item);
            break;
         case "Aged Brie":
            increaseQuality(item);
            break;
         case "Sulfuras, Hand of Ragnaros":
            item.quality=80;
            break;
         case "Conjured Mana Cake":
            doubleDegrade(item);
            break;
         default:
            doubleDegrade(item);
            doubleDegrade(item);
            break;
      }
   }

   private void doubleDegrade(Item item) {
      degrade(item);
      if (wasNotSoldOnTime(item)) degrade(item);
   }

   private boolean wasNotSoldOnTime(Item item) {
      return item.sellIn < 0;
   }

   private void degrade(Item item) {
      if (item.quality > 0) item.quality--;
   }

   private void increaseBackstageQuality(Item item) {
      if (item.sellIn <= 5) increaseQuality(item);
      if (item.sellIn <= 10) increaseQuality(item);
      increaseQuality(item);
      if (wasNotSoldOnTime(item)) {
         item.quality = 0;
      }
   }

   private void increaseQuality(Item item) {
      if (item.quality < 50) {
         item.quality++;
      }
   }
}