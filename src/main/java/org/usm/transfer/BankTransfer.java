package org.usm.transfer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
public class BankTransfer {
    private String transactionId;
    private String senderBankId;
    private String receiverBankId;
    private LocalDateTime transactionDateTime;
    private double transactionAmount;
    private String transactionCurrency;

    public BankTransfer() {

    }

    public BankTransfer(BankTransfer transfer) {
        this.transactionId = transfer.transactionId;
        this.senderBankId = transfer.senderBankId;
        this.receiverBankId = transfer.receiverBankId;
        this.transactionDateTime = transfer.transactionDateTime;
        this.transactionAmount = transfer.transactionAmount;
        this.transactionCurrency = transfer.transactionCurrency;
    }

    public BankTransfer copy() {
        return new BankTransfer(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankTransfer that = (BankTransfer) o;
        return Objects.equals(transactionId, that.transactionId);
    }

}
