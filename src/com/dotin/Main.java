package com.dotin;

import com.dotin.accountPKG.AccountDTO;
import com.dotin.accountPKG.balancePKG.BalanceDTO;
import com.dotin.accountPKG.payPKG.PaymentDTO;
import com.dotin.accountPKG.transactionPKG.TransactionDTO;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        List<PaymentDTO> payList = new ArrayList<>();
        List<BalanceDTO> balanceList = new ArrayList<>();
        List<TransactionDTO> transactionList = new ArrayList<>();
        List<AccountDTO> accountList = new ArrayList<>();
        Random rand = new Random();


        try {
            FileInputStream fis = new FileInputStream("account.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            accountList = (List<AccountDTO>) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException T) {


            saveFile("account", accountList);

            AccountDTO newAccount = new AccountDTO();
            AccountDTO newAccount2 = new AccountDTO();

            String randomDepositNumber1 = "1.10.100.1";
            newAccount2.setDepositNumber(randomDepositNumber1);
            BigDecimal bigDecimal2 = new BigDecimal((rand.nextInt() + 1000));
            newAccount2.setAmount(bigDecimal2);
            accountList.add(newAccount2);
            int randLoopLength = (rand.nextInt() + 1);

            for (int i = 0; i <= randLoopLength; i++) {
                String randomDepositNumber2 = "1.20.100." + (rand.nextInt() + 1);
                Optional<AccountDTO> first = accountList.stream()
                        .filter(x -> Objects.equals(randomDepositNumber2, x.getDepositNumber()))
                        .findFirst();
                if (!(first.isPresent())) {
                    newAccount.setDepositNumber(randomDepositNumber2);
                    BigDecimal bigDecimal = new BigDecimal((rand.nextInt() + 1));
                    newAccount.setAmount(bigDecimal);
                    accountList.add(newAccount);
                }
            }

        }


        {
            String inputDepositNumber = "1.0.100.1";
            ;
            Optional<AccountDTO> first = accountList.stream()
                    .filter(x -> Objects.equals(inputDepositNumber, x.getDepositNumber()))
                    .findFirst();
            //find deposit number that you want to pay
            if (first.isPresent()) {

                //pay(deptor)
                PaymentDTO newPay = new PaymentDTO();
                newPay.setDeptorOrCreditor("debtor");
                newPay.setDepositNumber("1.10.100.1");
                newPay.setAmount(first.get().getAmount());

                //pay(creditor)
                PaymentDTO newPay2 = new PaymentDTO();
                String inputDepositNumber2 = "1.20.100." + (rand.nextInt() + 1);
                Optional<AccountDTO> first2 = accountList.stream()
                        .filter(x -> Objects.equals(inputDepositNumber2, x.getDepositNumber()))
                        .findFirst();

                //find account(creditor) in account
                if (first2.isPresent()) {
                    newPay2.setDeptorOrCreditor("creditor");
                    newPay2.setDepositNumber(inputDepositNumber2);
                    BigDecimal bigDecimal;
                    bigDecimal = new BigDecimal(rand.nextInt() + 1);

                    if (!(accountList.get(accountList.indexOf(first.get())).getAmount().compareTo(bigDecimal) < 0)) {
                        accountList.get(accountList.indexOf(first.get())).setAmount(first.get().getAmount().subtract(bigDecimal));
                        accountList.get(accountList.indexOf(first2.get())).setAmount(first2.get().getAmount().add(bigDecimal));
                        newPay2.setAmount(bigDecimal);

                        //save deposit number that you want to pay(deptor) in to the pay list
                        payList.add(newPay);

                        //save deposit number that you want to pay(deptor) yin to the balance list
                        BalanceDTO newBalance = new BalanceDTO(first.get().getDepositNumber(), first.get().getAmount());
                        balanceList.add(newBalance);

                        //save deposit number that give pay (creditor) in to the balance list
                        BalanceDTO newBalance2 = new BalanceDTO(first2.get().getDepositNumber(), bigDecimal);
                        balanceList.add(newBalance2);

                        //save transactions in to the transaction list list
                        TransactionDTO newTransaction = new TransactionDTO(first.get().getDepositNumber(), first2.get().getDepositNumber(), bigDecimal);
                        transactionList.add(newTransaction);

                        //save deposit number that give pay (creditor) in to the pay list
                        payList.add(newPay2);

                        saveFile("pay", balanceList);
                        saveFile("balance", balanceList);
                        saveFile("transaction", balanceList);
                    }

                }

            }
        }
    }


    public static void saveFile(String fileName, List listName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName + ".tmp");
        ObjectOutputStream  oos = new ObjectOutputStream (fos);
        oos.writeObject(listName);
        oos.close();
    }
}
