package victor.training.refactoring;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class CombineFunctionsIntoTransform {
    public String generateQRCode(String code) {
        return "QR" + code;
    }

    public String getAddress(long eventId) {
        // Call Repo or External Service
        return "Location Details of event " + eventId;
    }
    // ----------- a line -------------

    public String generateTicket(Ticket ticket) {
        TicketView ticketView = createTicketView(ticket);
        return formatTicket(ticketView);
    }

    TicketView createTicketView(Ticket ticket) {
        TicketView ticketView = new TicketView();
        ticketView.setCustomerName(ticket.getCustomerName());
        ticketView.setQr(generateQRCode(ticket.getCode()));
        ticketView.setAddress(getAddress(ticket.getEventId()));
        return ticketView;
    }

    String formatTicket(TicketView ticketView) {
        String invoice;
        invoice = "Invoice for " + ticketView.getCustomerName() + "\n";
        invoice += "QR Code: " + ticketView.getQr() + "\n";
        invoice += "Address: " + ticketView.getAddress() + "\n";
        invoice += "Please arrive 20 minutes before the start of the event\n";
        invoice += "In case of emergency, call 0899898989\n";
        return invoice;
    }
}

class TicketView {
    private String qr;
    private String address;
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
}


// ----- SUPPORTING, DUMMY CODE ------
@Data
class Ticket {
    private final String customerName;
    private final String code;
    private final long eventId;
}