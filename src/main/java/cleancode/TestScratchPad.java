package cleancode;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class TestScratchPad {


    public void applyDiscount(ProductCategory category, String m, Optional<GoldCard> cardOpt, boolean usingPromoCode) {
        int discount = 0;
        if (!cardOpt.isPresent() && usingPromoCode) {
            discount = 10;
        } else if (cardOpt.isPresent()){
            discount = cardOpt.get().getPoints() / 100;
        }
        // apply discount to current cart
    }
    public void placeOrder(Order order, boolean viaOnline, Optional<String> discountCodeOpt) {
        // check items in stock..

        int discount = 0;
        if (!discountCodeOpt.isPresent() && viaOnline) {
            discount = 10;
        } else if (discountCodeOpt.isPresent()){
//            discount = discountCodeOpt.get().getPoints() / 100;
        }
        // apply discount to current cart
    }

    private List<String> mmm() {
        List<String> fileNames = asList("a");

        return fileNames.stream()
                .map(this::readFromFile)
                .filter(String::isEmpty)
                .collect(toList());
    }

    public String readFromFile(String fileName) /*throws IOException*/ {
        return null;
    }

    void f(String a, boolean before) {
        if(before) preClean();
        // 30 lines of heavy logic
        if(!before) postClean();
    }

    private void postClean() {

    }

    private void preClean() {
    }


}

class Order {}
enum ProductCategory {

}
class GoldCard {
    public int getPoints() {
        return 0;
    }
}