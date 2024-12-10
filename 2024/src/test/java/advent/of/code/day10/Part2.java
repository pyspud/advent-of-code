package advent.of.code.day10;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import advent.of.code.Utils;

class Part2 {

    static final List<Integer> EXAMPLE_TRAIL_HEADS_RATINGS = List.of(
        20, 24, 10, 4, 1, 4, 5, 8, 5
    );

    @Test
    void shouldFindRatingsOfTrailHeads() {
        var map = new TopographicMap(Part1.EXAMPLE_INPUT.lines());
        var score = map.trailHeads().stream()
                .map(map::rating)
                .toList();
        assertThat(score).isEqualTo(EXAMPLE_TRAIL_HEADS_RATINGS);
    }

    @Test
    void shouldFindSumOfExampleTrailHeadsRatings() {
        var map = new TopographicMap(Part1.EXAMPLE_INPUT.lines());
        var result = map.trailHeads()
                .stream()
                .map(map::rating)
                .mapToInt(Integer::intValue)
                .sum();
        assertThat(result).isEqualTo(81);
    }

    @Test
    void whatIsTheSumOfTheRatingsOfAllTrailheads() {
        var input = Utils.inputLines("/advent/of/code/day10/inputPart1.txt");
        var map = new TopographicMap(input);
        var result = map.trailHeads()
                .stream()
                .map(map::rating)
                .mapToInt(Integer::intValue)
                .sum();
        assertThat(result).isEqualTo(1541);
    }
}
