package org.usm.main;

import org.usm.search.Search;
import org.usm.search.SequentialSearch;
import org.usm.search.utils.PredicateComparator;
import org.usm.transfer.BankTransfer;
import org.usm.transfer.utility.BankTransferCSVReader;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
        String csvFilePath = "src/main/resources/bankTransfers.csv";
        List<BankTransfer> bankTransfers = BankTransferCSVReader.readCSVFile(csvFilePath);

        boolean stopFlag = false;
        Scanner scanner = new Scanner(System.in);
        do {
            String userInput;
            System.out.println("\n\n\n************ Menu: ***************");
            System.out.println("************ Choose an option ***************\n");
            System.out.println("(1) Display Transfers");
            System.out.println("(2) Search");
            System.out.println("(c) Close\n");
            System.out.print("Your input: ");
            userInput = scanner.nextLine();

            switch (userInput.toCharArray()[0]) {
                case '1':
                    displayList(bankTransfers);
                    break;
                case '2':
                    PredicateComparator<BankTransfer> p = getPredicate(scanner);
                    Search s = getSearch(scanner);
                    System.out.println("Search result - ");
                    displayList(s.searchBy(bankTransfers, p));
                    break;
                case 'c':
                    stopFlag = true;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        } while (!stopFlag);
        scanner.close();

    }

    public static void displayList(List<BankTransfer> list) {
        list.forEach(System.out::println);
    }

    public static PredicateComparator<BankTransfer> getPredicate(Scanner scanner) {
        PredicateComparator<BankTransfer> p = new PredicateComparator<>();
        String userInput;
        System.out.println("\n\n\n************ Search Menu: ***************");
        System.out.println("************ Choose a parameter by which to search ***************\n");
        System.out.println("(1) Transaction Id");
        System.out.println("(2) Sender Bank Id");
        System.out.println("(3) Receiver Bank Id");
        System.out.println("(4) Transaction Date Time");
        System.out.println("(5) Transaction Currency");
        System.out.println("(c) Cancel\n");
        System.out.print("Your input: ");
        userInput = scanner.nextLine();
        switch (userInput.toCharArray()[0]) {
            case '1':
                System.out.print("Please insert the transaction ID: ");
                userInput = scanner.nextLine();
                String finalUserInput = userInput;
                p.setPredicate(candidate -> candidate.getTransactionId().equals(finalUserInput));
                break;
            case '2':
                System.out.print("Please insert the Sender Bank Id: ");
                userInput = scanner.nextLine();
                finalUserInput = userInput;
                p.setPredicate(candidate -> candidate.getTransactionId().equals(finalUserInput));
                break;
            case '3':
                System.out.print("Please insert the Receiver Bank Id: ");
                userInput = scanner.nextLine();
                finalUserInput = userInput;
                p.setPredicate(candidate -> candidate.getTransactionId().equals(finalUserInput));
                break;
            case '4':
                System.out.print("Please insert the Transaction Date Time: ");
                userInput = scanner.nextLine();
                finalUserInput = userInput;
                p.setPredicate(candidate -> candidate.getTransactionId().equals(finalUserInput));
                break;
            case '5':
                System.out.print("Please insert the Transaction Currency: ");
                userInput = scanner.nextLine();
                finalUserInput = userInput;
                p.setPredicate(candidate -> candidate.getTransactionCurrency().equals(finalUserInput));
                break;
            default:
                System.out.println("Canceled search request");
                System.out.println("Wrong input!");
                throw new IllegalArgumentException();
        }
        return p;
    }

    public static Search getSearch(Scanner scanner) {
        Search s;
        String userInput;
        System.out.println("************ Choose a method how to search ***************\n");
        System.out.println("(1) Sequential search");
        System.out.println("(2) Binary search");
        System.out.println("(3) Search by interpolation");
        System.out.println("(4) The Fibonacci method for searching");
        System.out.println("(c) Cancel\n");
        System.out.print("Your input: ");
        userInput = scanner.nextLine();
        switch (userInput.toCharArray()[0]) {
            case '1':
                s = new SequentialSearch();
                break;
//            case '2':
//                break;
//            case '3':
//                break;
//            case '4':
//                break;
            default:
                System.out.println("Canceled search request");
                System.out.println("Wrong input!");
                throw new IllegalArgumentException();
        }
        return s;
    }

}
