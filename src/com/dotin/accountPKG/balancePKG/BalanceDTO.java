package com.dotin.accountPKG.balancePKG;

import java.io.Serializable;
import java.math.BigDecimal;

public class BalanceDTO implements Serializable {

    private String depositNumber;
    private BigDecimal amount;


    public BalanceDTO() {

    }

    public BalanceDTO(String depositNumber, BigDecimal amount) {
        this.depositNumber = depositNumber;
        this.amount = amount;

    }


    public String getDepositNumber() {
        return depositNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }


    @Override
    public String toString() {
        return depositNumber + "\t" + amount;
    }

}
