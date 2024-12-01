package advent.of.code.day1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Part1 {
    // Throughout the Chief's office, the historically significant locations are
    // listed not by name
    // but by a unique number called the location ID. To make sure they don't miss
    // anything,
    // The Historians split into two groups, each searching the office and trying to
    // create their own complete list of location IDs.

    // There's just one problem: by holding the two lists up side by side (your
    // puzzle input),
    // it quickly becomes clear that the lists aren't very similar.
    // Maybe you can help The Historians reconcile their lists?

    // For example:

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

    // Within each pair, figure out how far apart the two numbers are; you'll need
    // to add up all of those distances.
    // For example,
    // if you pair up a 3 from the left list with a 7 from the right list, 
    // the distance apart is 4; if you pair up a 9 with a 3, the distance apart is 6.
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

    // In the example list above, the pairs and distances would be as follows:

    // The smallest number in the left list is 1, and the smallest number in the
    // right list is 3. The distance between them is 2.
    // The second-smallest number in the left list is 2, and the second-smallest
    // number in the right list is another 3. The distance between them is 1.
    // The third-smallest number in both lists is 3, so the distance between them is
    // 0.
    // The next numbers to pair up are 3 and 4, a distance of 1.
    // The fifth-smallest numbers in each list are 3 and 5, a distance of 2.
    // Finally, the largest number in the left list is 4, while the largest number
    // in the right list is 9; these are a distance 5 apart.
    // To find the total distance between the left list and the right list, add up
    // the distances between all of the pairs you found. In the example above, this
    // is 2 + 1 + 0 + 1 + 2 + 5, a total distance of 11!

    @Test
    void examplePairsHaveDistance() {
        var pairs = TWO_LISTS.lines().map(Pair::of).toList();
        
        var left = Utils.sortedFieldValues(pairs, Pair::left);
        var right = Utils.sortedFieldValues(pairs, Pair::right);

        var sumOfDistances = Utils.sumOfDistances(left,right);
        assertThat(sumOfDistances).isEqualTo(11);
    }

    // Your actual left and right lists contain many location IDs. What is the total
    // distance between your lists?
    @Test
    void whatIsTheTotalDistanceBetweenYourLists() {

        try (var lines = Utils.inputLines("/advent/of/code/day1/inputPart1.txt")) {
            var pairs = lines.map(Pair::of).toList();

            var left = Utils.sortedFieldValues(pairs, Pair::left);
            var right = Utils.sortedFieldValues(pairs, Pair::right);
    
            var sumOfDistances = Utils.sumOfDistances(left,right);
            assertThat(sumOfDistances).isEqualTo(2113135);
        }
    }
}
