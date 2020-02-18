package victor.training.refactoring;

import lombok.Data;

public class CombineFunctionsIntoTransform {
    public String generateQRCode(String code) {
        // Call External Service
        return "QR" + code;
    }

    public String getAddress(long eventId) {
        // Call Repository
        return "Location Details of event " + eventId;
    }
    // ----------- a line -------------

    // TODO go through preserve Whole Object
    public String generateTicket(Ticket ticket) {
        TicketView ticketView = createView(ticket);
        return generateTicket(ticketView);
    }

    public TicketView createView(Ticket ticket) {
        String customerName = ticket.getCustomerName();
        String qrCode = generateQRCode(ticket.getCode());
        String address = getAddress(ticket.getEventId());
        return new TicketView(customerName, qrCode, address);
    }

    public String generateTicket(TicketView ticketView) {
        String invoice = "Invoice for " + ticketView.getCustomerName() + "\n";
        invoice += "QR Code: " + ticketView.getQrCode() + "\n";
        invoice += "Address: " + ticketView.getAddress() + "\n";
        invoice += "Please arrive 20 minutes before the start of the event\n";
        invoice += "In case of emergency, call 0899898989\n";
        return invoice;
    }
}

@Data
class TicketView {
    private final String customerName;
    private final String qrCode;
    private final String address;
}


// ----- SUPPORTING, DUMMY CODE ------
@Data
class Ticket {
    private final String customerName;
    private final String code;
    private final long eventId;
}