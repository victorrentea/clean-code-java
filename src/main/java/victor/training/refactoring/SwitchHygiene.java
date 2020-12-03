package victor.training.refactoring;

public class SwitchHygiene {
    enum MessageType {
        PLACE_ORDER,
        CANCEL_ORDER,
        SHIP_ORDER,
        ALT_MESAGE
    }
    public static void main(String[] args) {
        new SwitchHygiene().f("CANCEL_Order");
    }
    public void f(String messageTypeStr) {
        MessageType messageType = MessageType.valueOf(messageTypeStr);
        System.out.println("Cod inainte");
        handleMessage(messageType);
        System.out.println("Cod dupa");
    }

    private void handleMessage(MessageType messageType) {
        switch (messageType) {
            case PLACE_ORDER:
                handlePlaceOrder();
                break;
            case CANCEL_ORDER:
                handleCancelOrder();
                break;
            case SHIP_ORDER:
                handleShipOrder();
                break;
            default:
                throw new IllegalStateException("CAnd te vad in teste (nu in prod), beau!.Unexpected value: " + messageType);
        }
    }

    private void handleShipOrder() {
        System.out.println("COD3\n");
    }

    private void handleCancelOrder() {
        System.out.println("COD2\n");
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
        System.out.println("COD\n");
    }
}
