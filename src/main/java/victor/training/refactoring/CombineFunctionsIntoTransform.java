package victor.training.refactoring;

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
    public String generateTicket(Ticket ticket) { // """workflow manager""" / general / coorodnator
        InvoiceDetail invoiceDetails = extractInvoiceDetails(ticket);

        // Split Phase:
        // am spart procesarea in 2 faze distincte.
        // mi-am creeat un model date intermediar care transfera informatiile de la primul pas la al doilea
        // pot assertEquals() pe acest nou model, evitand sa folosesc mock-uri in testare.

        /// o linie -------------------------
        return invoiceFormatter.formatInvoice(invoiceDetails);
    }

    public InvoiceDetail extractInvoiceDetails(Ticket ticket) { // mai testabila daca e functie separata.
        String qrCode = generateQRCode(ticket.getCode());
        String address = getAddress(ticket.getEventId());
        String customerName = ticket.getCustomerName();
        return new InvoiceDetail(qrCode, address, customerName);
    }

    private final InvoiceFormatter invoiceFormatter = new InvoiceFormatter();

}
class InvoiceFormatter {

    public String formatInvoice(InvoiceDetail invoiceDetails) {
        String invoice = "Invoice for " + invoiceDetails.getCustomerName() + "\n";
        invoice += "QR Code: " + invoiceDetails.getQrCode() + "\n";
        invoice += "Address: " + invoiceDetails.getAddress() + "\n";
        invoice += "Please arrive 20 minutes before the start of the event\n";
        invoice += "In case of emergency, call 0899898989\n";
        return invoice;
    }
}


class InvoiceDetail {
    private final String qrCode;
    private final String address;
    private final String customerName;

    InvoiceDetail(String qrCode, String address, String customerName) {
        this.qrCode = qrCode;
        this.address = address;
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getQrCode() {
        return qrCode;
    }
//    public String toExportString() {
//        // doar daca ai de concatenat <5 stringuri
//    }
}

// ----- SUPPORTING, DUMMY CODE ------
class Ticket {
    private final String customerName;
    private final String code;
    private final long eventId;

    Ticket(String customerName, String code, long eventId) {
        this.customerName = customerName;
        this.code = code;
        this.eventId = eventId;
    }

    public long getEventId() {
        return eventId;
    }

    public String getCode() {
        return code;
    }

    public String getCustomerName() {
        return customerName;
    }
}