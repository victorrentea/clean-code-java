package victor.training.cleancode.java.refactoring;

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
        String invoice = "Invoice for " + ticket.customerName() + "\n";
        invoice += "QR Code: " + generateQRCode(ticket.code()) + "\n";
        invoice += "Address: " + getAddress(ticket.eventId()) + "\n";
        invoice += "Please arrive 20 minutes before the start of the event\n";
        invoice += "In case of emergency, call 0899898989\n";
        return invoice;
    }
}

record Ticket(String customerName,
              String code,
              long eventId) {
}