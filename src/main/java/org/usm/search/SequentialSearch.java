package org.usm.search;

import org.usm.search.utils.PredicateComparator;
import org.usm.transfer.BankTransfer;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SequentialSearch implements Search {
    @Override
    public List<BankTransfer> searchBy(List<BankTransfer> searchIn, PredicateComparator<BankTransfer> predicate) {
        Predicate<BankTransfer> p = predicate.getPredicate();
        return searchIn.stream()
                .filter(p)
                .collect(Collectors.toList());
    }
}
