package org.usm.main;

import org.usm.search.BinarySearch;
import org.usm.search.InterpolationSearch;
import org.usm.search.Search;
import org.usm.search.SequentialSearch;
import org.usm.search.sort.Sort;
import org.usm.search.utils.PredicateComparator;
import org.usm.transfer.BankTransfer;
import org.usm.transfer.utility.BankTransferCSVReader;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.usm.search.sort.Sort.sortBy;
import static org.usm.search.utils.Utility.convertStringToLocalDateTime;

public class App {
    public static void main(String[] args) {
        String csvFilePath = "src/main/resources/bankTransfersExtended.csv";
//        String csvFilePath = "src/main/resources/bankTransfers.csv";
//        String csvFilePath = "src/main/resources/MOCK_DATAA.csv";
        //Zloty
        List<BankTransfer> bankTransfers = BankTransferCSVReader.readCSVFile(csvFilePath);
        Scanner scanner = new Scanner(System.in);
        boolean stopFlag = false;
        do {
            try {
                stopFlag = displayMainMenu(scanner, bankTransfers);
            } catch (IllegalArgumentException e) {
                System.out.println("Seems like something went wrong. Retry.");
            }
        } while (!stopFlag);
        scanner.close();
    }

    public static void displayList(List<BankTransfer> list) {
        list.forEach(System.out::println);
    }

    public static boolean displayMainMenu(Scanner scanner, List<BankTransfer> bankTransfers) {
        boolean stopFlag = false;
        String userInput;
        System.out.println("\n\n\n************ Menu: ***************");
        System.out.println("************ Choose an option ***************\n");
        System.out.println("(1) Display Transfers");
        System.out.println("(2) Display Sorted");
        System.out.println("(3) Search");
        System.out.println("(4) Test all searches");
        System.out.println("(c) Close\n");
        System.out.print("Your input: ");
        userInput = scanner.nextLine();
        switch (userInput.toCharArray()[0]) {
            case '1':
                displayList(bankTransfers);
                break;
            case '2':
                PredicateComparator<BankTransfer> p1 = getPredicate(scanner);
                displayList(sortBy(bankTransfers, p1.getComparator()));
                break;
            case '3':
                PredicateComparator<BankTransfer> p = getPredicate(scanner);
                List<BankTransfer> searchInSorted = Sort.sortBy(bankTransfers, p.getComparator());
                Search s = getSearch(scanner);
                long startTime = System.nanoTime();
                List<BankTransfer> searchResult = s.searchBy(searchInSorted, p);
                long endTime = System.nanoTime();

                long durationInNanos = endTime - startTime;
                long durationInMicros = TimeUnit.NANOSECONDS.toMicros(durationInNanos);
                long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNanos);
                long durationInSeconds = TimeUnit.NANOSECONDS.toSeconds(durationInNanos);

                System.out.printf("Found: %d%n", searchResult.size());
                System.out.printf("Duration: %d microseconds%n", durationInMicros);
                System.out.printf("Or: %d milliseconds%n", durationInMillis);
                System.out.printf("Or: %d seconds%n", durationInSeconds);
                Scanner displayScanner = new Scanner(System.in);
                System.out.println("Display Full result? (y/n)");
                String displayOr = displayScanner.nextLine();
                if (displayOr.contains("y")) {
                    displayList(searchResult);
                }
                break;
            case '4':
                p = getPredicate(scanner);
                searchInSorted = Sort.sortBy(bankTransfers, p.getComparator());
                BinarySearch binarySearch = new BinarySearch();
                InterpolationSearch interpolationSearch = new InterpolationSearch();
                SequentialSearch sequentialSearch = new SequentialSearch();

                evaluateExecutionTime("Sequential Search", (search) -> search.searchBy(searchInSorted, p), sequentialSearch);
                evaluateExecutionTime("Binary Search", (search) -> search.searchBy(searchInSorted, p), binarySearch);
                evaluateExecutionTime("Interpolation Search", (search) -> search.searchBy(searchInSorted, p), interpolationSearch);

                break;
            case 'c':
                return true;
            default:
                System.out.println("Wrong input! \nRetry.");
                return false;
        }
        return false;
    }

    public static void evaluateExecutionTime(String name, Function<Search, List<BankTransfer>> function, Search search) {
        long startTime = System.nanoTime();
        List<BankTransfer> searchResult = function.apply(search);
        long endTime = System.nanoTime();

        long durationInNanos = endTime - startTime;
        long durationInMicros = TimeUnit.NANOSECONDS.toMicros(durationInNanos);
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNanos);
        long durationInSeconds = TimeUnit.NANOSECONDS.toSeconds(durationInNanos);
        System.out.println("=========================================");
        System.out.printf(name);
        System.out.printf("Found: %d%n", searchResult.size());
        System.out.println("********************************");
        System.out.printf("Duration: %d microseconds%n", durationInMicros);
        System.out.printf("Or: %d milliseconds%n", durationInMillis);
        System.out.printf("Or: %d seconds%n", durationInSeconds);
        System.out.println("=========================================");
    }

    public static PredicateComparator<BankTransfer> getPredicate(Scanner scanner) {
        PredicateComparator<BankTransfer> p = new PredicateComparator<>();
        String userInput;
        System.out.println("\n\n\n************ Search Menu: ***************");
        System.out.println("************ Choose a parameter by which to search/sort ***************\n");
        System.out.println("(1) Transaction Id");
        System.out.println("(2) Sender Bank Id");
        System.out.println("(3) Receiver Bank Id");
        System.out.println("(4) Transaction Date Time");
        System.out.println("(5) Transaction Amount");
        System.out.println("(6) Transaction Currency");
        System.out.println("(c) Cancel\n");
        System.out.print("Your input: ");
        userInput = scanner.nextLine();
        BankTransfer target = new BankTransfer();
        switch (userInput.toCharArray()[0]) {
            case '1':
                System.out.print("Please insert the transaction ID: ");
                userInput = scanner.nextLine();
                String finalUserInput = userInput;
                target.setTransactionId(finalUserInput);
                p.setPredicate(candidate -> candidate.getTransactionId().equals(finalUserInput));
                p.setComparator(Comparator.comparing(BankTransfer::getTransactionId));
                break;
            case '2':
                System.out.print("Please insert the Sender Bank Id: ");
                userInput = scanner.nextLine();
                finalUserInput = userInput;
                target.setSenderBankId(finalUserInput);
                p.setPredicate(candidate -> candidate.getSenderBankId().equals(finalUserInput));
                p.setComparator(Comparator.comparing(BankTransfer::getSenderBankId));
                break;
            case '3':
                System.out.print("Please insert the Receiver Bank Id: ");
                userInput = scanner.nextLine();
                finalUserInput = userInput;
                target.setReceiverBankId(finalUserInput);
                p.setPredicate(candidate -> candidate.getReceiverBankId().equals(finalUserInput));
                p.setComparator(Comparator.comparing(BankTransfer::getReceiverBankId));
                break;
            case '4':
                System.out.print("Please insert the Transaction Date Time: ");
                userInput = scanner.nextLine();
                finalUserInput = userInput;
                LocalDateTime dateTime = convertStringToLocalDateTime(finalUserInput);
                target.setTransactionDateTime(dateTime);
                p.setPredicate(candidate -> candidate.getTransactionDateTime().equals(dateTime));
                p.setComparator(Comparator.comparing(BankTransfer::getTransactionDateTime));
                break;
            case '5':
                System.out.print("Please insert the Transaction Amount: ");
                userInput = scanner.nextLine();
                finalUserInput = userInput;
                target.setTransactionAmount(Double.parseDouble(finalUserInput));
                p.setPredicate(candidate -> candidate.getTransactionAmount() == Double.parseDouble(finalUserInput));
                p.setComparator(Comparator.comparing(BankTransfer::getTransactionAmount));
                break;
            case '6':
                System.out.print("Please insert the Transaction Currency: ");
                userInput = scanner.nextLine();
                finalUserInput = userInput;
                target.setTransactionCurrency(userInput);
                p.setPredicate(candidate -> candidate.getTransactionCurrency().equals(finalUserInput));
                p.setComparator(Comparator.comparing(BankTransfer::getTransactionCurrency));
                break;
            default:
                System.out.println("Canceled search request");
                System.out.println("Wrong input!");
                throw new IllegalArgumentException();
        }
        p.setTarget(target);
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
            case '2':
                s = new BinarySearch();
                break;
            case '3':
                s = new InterpolationSearch();
                break;
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
