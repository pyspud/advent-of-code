package advent.of.code.day5;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import advent.of.code.Utils;
import advent.of.code.day5.ManualUpdates.PageRule;

class Part1 {
    static final String EXAMPLE_INPUT = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
            """;

    static final Set<PageRule> EXAMPLE_PAGE_ORDERING_RULES = Set.of(
        new PageRule(47,53),
        new PageRule(97,13),
        new PageRule(97,61),
        new PageRule(97,47),
        new PageRule(75,29),
        new PageRule(61,13),
        new PageRule(75,53),
        new PageRule(29,13),
        new PageRule(97,29),
        new PageRule(53,29),
        new PageRule(61,53),
        new PageRule(97,53),
        new PageRule(61,29),
        new PageRule(47,13),
        new PageRule(75,47),
        new PageRule(97,75),
        new PageRule(47,61),
        new PageRule(75,61),
        new PageRule(47,29),
        new PageRule(75,13),
        new PageRule(53,13)
        );

    @Test
    void shouldGetPageOrderingRules() {
        var manualUpdates = new ManualUpdates(EXAMPLE_INPUT.lines().toList());
        assertThat(manualUpdates.pageOrderingRuleSet()).isEqualTo(EXAMPLE_PAGE_ORDERING_RULES);
    }

    static final List<List<Integer>> EXAMPLE_PAGE_UPDATES = List.of(
            List.of(75, 47, 61, 53, 29),
            List.of(97, 61, 53, 29, 13),
            List.of(75, 29, 13),
            List.of(75, 97, 47, 61, 53),
            List.of(61, 13, 29),
            List.of(97, 13, 75, 29, 47));

    @Test
    void shouldGetPageOUpdates() {
        var manualUpdates = new ManualUpdates(EXAMPLE_INPUT.lines().toList());
        assertThat(manualUpdates.allPageUpdateLists()).isEqualTo(EXAMPLE_PAGE_UPDATES);
    }

    @ParameterizedTest
    @CsvSource({
            "'75,47,61,53,29', 61",
            "'97,61,53,29,13', 53",
            "'75,29,13', 29",
    })
    void shouldFindMedian(String pageList, int expected) {
        var pages = ManualUpdates.listOfPagesFrom(pageList);
        var actual = ManualUpdates.median(pages);
        assertThat(actual).isEqualTo(expected);
    }
    @ParameterizedTest
    @CsvSource({
            "0, true",
            "1, true",
            "2, true",
            "3, false",
            "4, false",
            "5, false",
    })
    void shouldFindMedian(int pageListLine, boolean expected) {
        var manualUpdates = new ManualUpdates(EXAMPLE_INPUT.lines().toList());
        var pageList = manualUpdates.allPageUpdateLists().get(pageListLine);
        var actual = manualUpdates.isValidOrder(pageList);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whatDoYouGetIfYouAddUpTheMiddlePageNumberFromThoseCorrectlyOrderedUpdates() {
        var lines = Utils.inputLines("/advent/of/code/day5/inputPart1.txt").toList();
        var manualUpdates = new ManualUpdates(lines);
        var result = manualUpdates.allPageUpdateLists().stream()
                .filter(manualUpdates::isValidOrder)
                .mapToInt(ManualUpdates::median)
                .sum();
        assertThat(result).isEqualTo(5064);
    }
}
