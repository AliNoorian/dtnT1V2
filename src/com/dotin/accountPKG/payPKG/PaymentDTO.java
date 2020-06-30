package com.dotin.accountPKG.payPKG;

import java.io.Serializable;
import java.math.BigDecimal;

public class PaymentDTO implements Serializable {
    private String deptorOrCreditor;
    private String depositNumber;
    private BigDecimal amount;


    public PaymentDTO() {

    }

    public PaymentDTO(String deptorOrCreditor, String depositNumber, BigDecimal amount) {
        this.deptorOrCreditor = deptorOrCreditor;
        this.depositNumber = depositNumber;
        this.amount = amount;

    }

    public String getDeptorOrCreditor() {
        return deptorOrCreditor;
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

    public void setDeptorOrCreditor(String deptorOrCreditor) {
        this.deptorOrCreditor = deptorOrCreditor;
    }


    @Override
    public String toString() {
        return deptorOrCreditor + "\t" + depositNumber + "\t" + amount;
    }
}
