package org.usm.search;

import org.usm.search.utils.PredicateComparator;
import org.usm.transfer.BankTransfer;

import java.util.List;

public interface Search {
    List<BankTransfer> searchBy(List<BankTransfer> searchIn, PredicateComparator<BankTransfer> predicate);
}
