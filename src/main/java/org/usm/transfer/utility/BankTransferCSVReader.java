package org.usm.transfer.utility;

import org.usm.transfer.BankTransfer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankTransferCSVReader {
    public static List<BankTransfer> readCSVFile(String csvFilePath) {
        List<BankTransfer> bankTransfers = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    BankTransfer bankTransfer = new BankTransfer();
                    bankTransfer.setTransactionId(data[0]);
                    bankTransfer.setSenderBankId(data[1]);
                    bankTransfer.setReceiverBankId(data[2]);
                    bankTransfer.setTransactionAmount(Double.parseDouble(data[4]));
                    bankTransfer.setTransactionCurrency(data[5]);
                    LocalDateTime transactionDateTime = LocalDateTime.parse(data[3], dateTimeFormatter);
                    bankTransfer.setTransactionDateTime(transactionDateTime);

                    bankTransfers.add(bankTransfer);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return bankTransfers;
    }
}