package victor.training.cleancode.kata.povalidator;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class POValidator {
  private List<Validator> validators;

  public void setValidators(List<Validator> validators) {
    this.validators = validators;
  }

  public boolean validatePurchaseOrderItem(POItem poItem) {
    var result = new AtomicBoolean(Boolean.TRUE);

    validators.stream()
        .takeWhile(v -> result.get())
        .forEach(v -> {
          if (!v.validate(poItem)) {
            result.set(Boolean.FALSE);
            System.err.println("PO id=" + poItem.getId() + " failed " + v.getClass().getName());
            var message = new POItemMessage();
            message.setType(SAPMessageType.ERROR); // Hint: the following 3 lines repeat somewhere else in code
            message.setMessageClass(v.getClass().getSimpleName());
            message.setText(v.message(poItem));
            poItem.addMessage(message);
            poItem.setStatus(POItem.Status.ERROR);
          }
        });
    return result.get();
  }
}

// ========= SUPPORT CODE =======

class POItem {
  enum Status {SUCCESS, ERROR}

  private Long id;
  private Status status;
  private List<POItemMessage> messages = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Status getStatus() {
    return status;
  }

  public void addMessage(POItemMessage message) {
    messages.add(message);
  }

  public List<POItemMessage> getMessages() {
    return messages;
  }
}

enum SAPMessageType {ERROR, INFO, WARN, MESSAGE}

class POItemMessage {
  private String messageClass;
  private String text;
  private SAPMessageType type;

  public void setMessageClass(String messageClass) {
    this.messageClass = messageClass;
  }

  public String getMessageClass() {
    return messageClass;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setType(SAPMessageType type) {
    this.type = type;
  }

  public SAPMessageType getType() {
    return type;
  }
}

interface Validator {

  boolean validate(POItem purchaseOrderItem);

  String message(POItem purchaseOrderItem);
}

