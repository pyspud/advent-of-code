package advent.of.code.day5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class ManualUpdates {
    record PageRule(int page, int before) {
        static PageRule of(String line) {
            var lineParts = line.split("\\|");
            var page = Integer.parseInt(lineParts[0]);
            var before = Integer.parseInt(lineParts[1]);
            return new PageRule(page, before);
        }
    }

    private Set<PageRule> pageOrderingRules = new HashSet<>();
    private List<List<Integer>> pageUpdates = new ArrayList<>();

    ManualUpdates(List<String> lines) {
        boolean orderRules = true;
        for (var line : lines) {
            if (orderRules && line.isEmpty()) {
                orderRules = false;
            } else if (orderRules) {
                pageOrderingRules.add(PageRule.of(line));
            } else {
                pageUpdates.add(listOfPagesFrom(line));
            }
        }
    }

    static List<Integer> listOfPagesFrom(String line) {
        var lineParts = line.split(",");
        return IntStream.range(0, lineParts.length)
                .mapToObj(i -> lineParts[i])
                .map(Integer::valueOf)
                .toList();
    }

    Set<PageRule> pageOrderingRuleSet() {
        return pageOrderingRules;
    }

    List<List<Integer>> allPageUpdateLists() {
        return pageUpdates;
    }

    boolean isValidOrder(List<Integer> pages) {
        for (int p = 0; p < pages.size(); p++) {
            var currentPage = pages.get(p);
            var others = pages.subList(p + 1, pages.size());
            for (var pageToCheck : others) {
                var rule = new PageRule(pageToCheck, currentPage);
                if (pageOrderingRules.contains(rule)) {
                    return false;
                }
            }
        }
        return true;
    }

    Comparator<Integer> order = (p1, p2) -> {
        if (pageOrderingRules.contains(new PageRule(p1, p2)))
            return -1;
        else if (pageOrderingRules.contains(new PageRule(p2, p1)))
            return 1;
        return 0;
    };

    List<Integer> correctOrder(List<Integer> pages) {
        List<Integer> ordered = new ArrayList<>(pages);
        ordered.sort(order);
        return ordered;
    }

    static Integer median(List<Integer> pages) {
        var median = pages.size() / 2;
        return pages.get(median);
    }
}
