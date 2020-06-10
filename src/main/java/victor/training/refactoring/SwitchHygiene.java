package victor.training.refactoring;

public class SwitchHygiene {
    enum MessageType {
        PLACE_ORDER,
        CANCEL_ORDER,
        SHIP_ORDER//, RETURN_ORDER -- pericol
    }
    public void f(MessageType messageType) {
        System.out.println("Cod inainte");
        System.out.println("Cod inainte");
        String response = processByType(messageType);
        System.out.println("Cod dupa");
    }

    private String processByType(MessageType messageType) {
//        return switch (messageType) { // nu compileaza daca nu acoperi toate enumurile
//            case PLACE_ORDER -> handlePlaceOrder();
//            case CANCEL_ORDER -> handleCancelOrder();
//            case SHIP_ORDER -> handleShipOrder();
//        };
        // nu compileaza daca nu acoperi toate enumurile
        //            default -> throw new IllegalStateException("JDD: Unexpected value: " + messageType);
        switch (messageType) {
            case PLACE_ORDER:
                return handlePlaceOrder();
            case CANCEL_ORDER:
                return handleCancelOrder();
            case SHIP_ORDER:
                return handleShipOrder();
            default:
                throw new IllegalArgumentException();
        }
    }

    private String handleShipOrder() {
        System.out.println("COD3\n");
        return "ship";
    }

    private String handleCancelOrder() {
        System.out.println("COD2\n");
        return "cancel";
    }

    private String handlePlaceOrder() {
        System.out.println("Marcel: pun #sieu aici ceva \n");
        if (true) {
            try {
                System.out.println("Maricica: pun #sieu aici ceva \n");
            } catch (Exception $e) {
            }
        }

        System.out.println("Florin: pun #sieu aici ceva \n");
        System.out.println("Marcel: pun #sieu aici ceva \n");
        System.out.println("COD\n");
        return "place";
    }
}
