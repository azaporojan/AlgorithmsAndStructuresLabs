package org.usm.search;

import org.usm.transfer.BankTransfer;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SequentialSearch implements Search {
    @Override
    public List<BankTransfer> searchBy(List<BankTransfer> searchIn, Predicate<BankTransfer> predicate) {
        return searchIn.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
