package advent.of.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class Day9Test {
    // Part 1

    // Each line in the report contains the history of a single value. For example:

    @Test
    void shouldFindPart1() {
        Stream<String> INPUT_1 = Stream.of(
                "0 3 6 9 12 15",
                "1 3 6 10 15 21",
                "10 13 16 21 30 45");
        var actual = Day9.part1(INPUT_1);
        assertThat(actual).isEqualTo(114);
    }

    @Test
    void shouldFindPart2() {
        Stream<String> INPUT_1 = Stream.of(
                "0 3 6 9 12 15",
                "1 3 6 10 15 21",
                "10 13 16 21 30 45");
        var actual = Day9.part2(INPUT_1);
        assertThat(actual).isEqualTo(2);
    }
}
