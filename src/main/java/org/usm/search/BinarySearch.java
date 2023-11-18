package org.usm.search;

import org.usm.search.utils.PredicateComparator;
import org.usm.transfer.BankTransfer;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class BinarySearch implements Search {
    @Override
    public List<BankTransfer> searchBy(List<BankTransfer> searchIn, PredicateComparator<BankTransfer> predicate) {
        return null;
    }

//    public List<BankTransfer> sortBinary(int start, int end, List<BankTransfer> source, List<BankTransfer> foundItems, Comparator<BankTransfer> comparator) {
//        int candidateIndex = end / 2;
//        BankTransfer candidate = source.get(candidateIndex);
//        if(comparator.compare(candidate)){
//            foundItems.add(candidate);
//        }else {
//            comparator.
//        }
//        return foundItems;
//    }

    public static void main(String[] args) {
        int a = 3/2;
        System.out.println(a);
    }
}
