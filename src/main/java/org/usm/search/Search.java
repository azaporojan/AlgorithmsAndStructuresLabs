package org.usm.search;

import org.usm.transfer.BankTransfer;

import java.util.List;
import java.util.function.Predicate;

public interface Search {
    List<BankTransfer> searchBy(List<BankTransfer> searchIn,Predicate<BankTransfer> predicate);
}
