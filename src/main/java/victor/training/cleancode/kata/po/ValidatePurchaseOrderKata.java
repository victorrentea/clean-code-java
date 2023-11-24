package victor.training.cleancode.kata.po;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ValidatePurchaseOrderKata {
  private static final Logger log = LoggerFactory.getLogger(ValidatePurchaseOrderKata.class);

  private Collection<Validator> getPurchaseOrderItemValidators() {
    throw new RuntimeException("Method not implemented");
  }

  public boolean validatePurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {

    var result = new AtomicBoolean(Boolean.TRUE);

    getPurchaseOrderItemValidators().stream()
            .takeWhile(v -> result.get())
            .forEach(v -> {
              if (!v.validate(purchaseOrderItem)) {
                result.set(Boolean.FALSE);
                log.warn(
                        "Purchase order item validation failed. id: {}. Validation: {}",
                        purchaseOrderItem.getId(),
                        v.getClass().getName()
                );
                var message = new PurchaseOrderItemMessage();
                message.setMessageClass(v.getClass().getSimpleName());
                message.setText(v.message(purchaseOrderItem));
                message.setType(SAPMessageType.ERROR); // these 3 lines repeat somewhere else in code
                purchaseOrderItem.addMessage(message);
                purchaseOrderItem.setStatus(PurchaseOrderItemStatus.ERROR);
              }
            });
    return result.get();
  }
}

// ========= SUPPORT CODE =======

class PurchaseOrderItem {

  private Long id;
  private PurchaseOrderItemStatus status;
  private List<PurchaseOrderItemMessage> messages = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setStatus(PurchaseOrderItemStatus status) {
    this.status = status;
  }

  public PurchaseOrderItemStatus getStatus() {
    return status;
  }

  public void addMessage(PurchaseOrderItemMessage message) {
    messages.add(message);
  }
}

enum SAPMessageType {ERROR, INFO, WARN, MESSAGE}

enum PurchaseOrderItemStatus {ERROR}

class PurchaseOrderItemMessage {

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

  boolean validate(PurchaseOrderItem purchaseOrderItem);

  String message(PurchaseOrderItem purchaseOrderItem);
}

