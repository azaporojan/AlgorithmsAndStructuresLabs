package org.usm.search;

import org.usm.search.utils.PredicateComparator;
import org.usm.transfer.BankTransfer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.usm.search.utils.Utility.appendBackward;
import static org.usm.search.utils.Utility.appendForward;

public class InterpolationSearch implements Search {
    @Override
    public List<BankTransfer> searchBy(List<BankTransfer> searchInSorted, PredicateComparator<BankTransfer> predicate) {
        List<BankTransfer> found = new ArrayList<>();
        int foundIndex = searchCandidateIndex(searchInSorted, predicate, 0, searchInSorted.size() - 1);
        if (foundIndex >= 0) {
            found.add(searchInSorted.get(foundIndex));
            appendForward(found, searchInSorted, foundIndex + 1, predicate.getPredicate());
            appendBackward(found, searchInSorted, foundIndex - 1, predicate.getPredicate());
        }
        return found;
    }

    public int searchCandidateIndex(List<BankTransfer> searchInSorted, PredicateComparator<BankTransfer> p, int start, int end) {
        BankTransfer target = p.getTarget();
        Comparator<BankTransfer> comparator = p.getComparator();
        if (comparator.compare(searchInSorted.get(end), searchInSorted.get(start)) == 0) {
            return -1;
        }
        int guess = start + ((comparator.compare(target, searchInSorted.get(start))) * (end - start)) / comparator.compare(searchInSorted.get(end), searchInSorted.get(start));
        if (guess < 0) guess *= -1;
        if (end < guess) {
            return -1;
        }
        int compareRes = p.getComparator().compare(p.getTarget(), searchInSorted.get(guess));
        if (compareRes == 0) {
            return guess;
        } else if (compareRes < 0) {
            return searchCandidateIndex(searchInSorted, p, start, guess - 1);
        } else {
            return searchCandidateIndex(searchInSorted, p, guess + 1, end);
        }
    }
}
