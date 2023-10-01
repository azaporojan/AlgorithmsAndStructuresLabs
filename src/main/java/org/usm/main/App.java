package org.usm.main;

import org.usm.transfer.BankTransfer;
import org.usm.transfer.utility.BankTransferCSVReader;

import java.util.List;

public class App {
    public static void main(String[] args) {
        String csvFilePath = "src/main/resources/bankTransfers.csv";
        List<BankTransfer> bankTransfers = BankTransferCSVReader.readCSVFile(csvFilePath);

        // Display the list of bank transfers
        for (BankTransfer transfer : bankTransfers) {
            System.out.println(transfer);
        }
    }
}
