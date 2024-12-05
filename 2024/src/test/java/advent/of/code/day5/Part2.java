package advent.of.code.day5;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import advent.of.code.Utils;

class Part2 {
    @ParameterizedTest
    @CsvSource({
        "'75,97,47,61,53', '97,75,47,61,53'",
        "'61,13,29', '61,29,13'",
        "'97,13,75,29,47', '97,75,47,29,13'",
    })
    void shouldCorrectlyOrderUpdates(String incorrectValue, String expectedValue) {
        var incorrect = ManualUpdates.listOfPagesFrom(incorrectValue);
        var expected = ManualUpdates.listOfPagesFrom(expectedValue);

        var manualUpdates = new ManualUpdates(Part1.EXAMPLE_INPUT.lines().toList());
        var actual = manualUpdates.correctOrder(incorrect);
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void whatDoYouGetIfYouAddUpTheMiddlePageNumberFromThoseCorrectlyOrderedUpdates() {
        var lines = Utils.inputLines("/advent/of/code/day5/inputPart1.txt").toList();
        var manualUpdates = new ManualUpdates(lines);
        var result = manualUpdates.allPageUpdateLists().stream()
                .filter(not(manualUpdates::isValidOrder))
                .map(manualUpdates::correctOrder)
                .mapToInt(ManualUpdates::median)
                .sum();
        assertThat(result).isEqualTo(5152);
    }
}
