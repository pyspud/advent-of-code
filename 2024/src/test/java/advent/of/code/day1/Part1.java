package advent.of.code.day1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Part1 {
    static final String TWO_LISTS = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """;

    static final long[] LEFT_ORDERED = { 1l, 2l, 3l, 3l, 3l, 4l };
    static final long[] RIGHT_ORDERED = { 3l, 3l, 3l, 4l, 5l, 9l };

    @Test
    void shouldOrderLists() {

        var pairs = TWO_LISTS.lines().map(Pair::of).toList();
        var left = Utils.sortedFieldValues(pairs, Pair::left);
        var right = Utils.sortedFieldValues(pairs, Pair::right);

        assertThat(left).isEqualTo(LEFT_ORDERED);
        assertThat(right).isEqualTo(RIGHT_ORDERED);
    }

    @Test
    void shouldParseLineToPair() {
        var line = "1234   6678";
        var expected = new Pair(1234, 6678);

        assertThat(Pair.of(line)).isEqualTo(expected);
    }

    @DisplayName("Distance between pairs")
    @ParameterizedTest(name = "{index} ==> distance between left:{0} & right:{1} is {2}")
    @CsvSource({
            "3, 7, 4",
            "9, 3, 6",
            "0, 0, 0",
            "9, 0, 9",
            "0, 7, 7",
    })
    void shouldCalculateDistances(long left, long right, long expected) {
        var input = new Pair(left, right);
        assertThat(input).returns(expected, Pair::distance);
    }

    @Test
    void examplePairsHaveDistance() {
        var pairs = TWO_LISTS.lines().map(Pair::of).toList();

        var left = Utils.sortedFieldValues(pairs, Pair::left);
        var right = Utils.sortedFieldValues(pairs, Pair::right);

        var sumOfDistances = Utils.sumOfDistances(left,right);
        assertThat(sumOfDistances).isEqualTo(11);
    }

    @Test
    void whatIsTheTotalDistanceBetweenYourLists() {

        try (var lines = advent.of.code.Utils.inputLines("/advent/of/code/day1/inputPart1.txt")) {
            var pairs = lines.map(Pair::of).toList();

            var left = Utils.sortedFieldValues(pairs, Pair::left);
            var right = Utils.sortedFieldValues(pairs, Pair::right);

            var sumOfDistances = Utils.sumOfDistances(left,right);
            assertThat(sumOfDistances).isEqualTo(2113135);
        }
    }
}
