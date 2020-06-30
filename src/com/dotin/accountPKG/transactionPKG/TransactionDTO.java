package com.dotin.accountPKG.transactionPKG;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionDTO implements Serializable {


    private String debtorDepositNumber;
    private String creditorDepositNumber;
    private BigDecimal amount;


    public TransactionDTO() {

    }

    public TransactionDTO(String debtorDepositNumber, String creditorDepositNumber, BigDecimal amount) {
        this.debtorDepositNumber = debtorDepositNumber;
        this.creditorDepositNumber = creditorDepositNumber;
        this.amount = amount;

    }

    public String getDebtorDepositNumber() {
        return debtorDepositNumber;
    }

    public String getCreditorDepositNumber() {
        return creditorDepositNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCreditorDepositNumber(String creditorDepositNumber) {
        this.creditorDepositNumber = creditorDepositNumber;
    }

    public void setDebtorDepositNumber(String debtorDepositNumber) {
        this.debtorDepositNumber = debtorDepositNumber;
    }


    @Override
    public String toString() {
        return debtorDepositNumber + "\t" + creditorDepositNumber + "\t" + amount;
    }
}
