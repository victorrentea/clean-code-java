package victor.training.refactoring;

public class SwitchHygiene {
    public void f(String messageType) {
        System.out.println("Cod inainte");
        System.out.println("Cod inainte");
        System.out.println("Cod inainte");
        System.out.println("Cod inainte");
        System.out.println("Cod inainte");
        System.out.println("Cod inainte");
        System.out.println("Cod inainte");
        System.out.println("Cod inainte");
        System.out.println("Cod inainte");
        processByType(messageType);
        System.out.println("Cod dupa");
    }

    private void processByType(String messageType) {
        switch (messageType) {
            case "PLACE_ORDER":
                handlePlaceOrder();
                break;
            case "CANCEL_ORDER":
                System.out.println("COD2\n");
                break;
            case "SHIP_ORDER":
                System.out.println("COD3\n");
                break;

            default:
                throw new IllegalStateException("JDD: Unexpected value: " + messageType);
        }
    }

    private void handlePlaceOrder() {
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
    }
}
