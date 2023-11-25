package org.usm.search;

import org.usm.search.utils.PredicateComparator;
import org.usm.transfer.BankTransfer;

import java.util.ArrayList;
import java.util.List;

import static org.usm.search.utils.Utility.appendBackward;
import static org.usm.search.utils.Utility.appendForward;

public class BinarySearch implements Search {
    @Override
    public List<BankTransfer> searchBy(List<BankTransfer> searchInSorted, PredicateComparator<BankTransfer> predicate) {
        List<BankTransfer> found = new ArrayList<>();
        int foundIndex = searchCandidateIndex(searchInSorted, predicate, 0, searchInSorted.size());
        if (foundIndex >= 0) {
            found.add(searchInSorted.get(foundIndex));
            appendForward(found, searchInSorted, foundIndex + 1, predicate.getPredicate());
            appendBackward(found, searchInSorted, foundIndex - 1, predicate.getPredicate());
        }
        return found;
    }

    public int searchCandidateIndex(List<BankTransfer> searchInSorted, PredicateComparator<BankTransfer> p, int start, int end) {
        int middle = (end - start) / 2;
        middle = middle + start;
        int compareRes = p.getComparator().compare(p.getTarget(), searchInSorted.get(middle));
        if (middle == start) {
            return -1;
        } else if (compareRes == 0) {
            return middle;
        } else if (compareRes < 0) {
            return searchCandidateIndex(searchInSorted, p, start, middle);
        } else {
            return searchCandidateIndex(searchInSorted, p, middle, end);
        }
    }
}
