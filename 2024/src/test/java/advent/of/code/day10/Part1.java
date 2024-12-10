package advent.of.code.day10;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import advent.of.code.Utils;
import advent.of.code.day10.TopographicMap.Point;

class Part1 {

    static final String EXAMPLE_INPUT ="""
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
            """;

    static final List<Point> EXAMPLE_TRAIL_HEADS = List.of(
        new Point(0, 2),
        new Point(0, 4),
        new Point(2, 4),
        new Point(4, 6),
        new Point(5, 2),
        new Point(5, 5),
        new Point(6, 0),
        new Point(6, 6),
        new Point(7, 1)
    );

    @Test
    void shouldFindTrailHeadsInExample() {
        var map = new TopographicMap(EXAMPLE_INPUT.lines());
        var result = map.trailHeads();
        assertThat(result).isEqualTo(EXAMPLE_TRAIL_HEADS);
    }

    static final List<Integer> EXAMPLE_TRAIL_HEADS_SCORES = List.of(
        5, 6, 5, 3, 1, 3, 5, 3, 5
    );

    static List<Arguments> exampleArguments = List.of(
        arguments(EXAMPLE_TRAIL_HEADS,EXAMPLE_TRAIL_HEADS_SCORES)
    );

    @Test
    void shouldFindScoreOfOneTrailHead() {
        var map = new TopographicMap(EXAMPLE_INPUT.lines());
        var score = map.score(EXAMPLE_TRAIL_HEADS.get(4));
        assertThat(score).isEqualTo(EXAMPLE_TRAIL_HEADS_SCORES.get(4));
    }

    @Test
    void shouldFindScoresOfTrailHeads() {
        var map = new TopographicMap(EXAMPLE_INPUT.lines());
        var score = map.trailHeads().stream().map(map::score).toList();
        assertThat(score).isEqualTo(EXAMPLE_TRAIL_HEADS_SCORES);
    }

    @Test
    void shouldFindSumOfExampleTrailHeadsScores() {
        var map = new TopographicMap(EXAMPLE_INPUT.lines());
        var result = map.trailHeads()
                .stream()
                .map(map::score)
                .mapToInt(Integer::intValue)
                .sum();
        assertThat(result).isEqualTo(36);
    }

    @Test
    void whatIsTheSumOfTheScoresOfAllTrailheadsOnYourTopographicMap() {
        var input = Utils.inputLines("/advent/of/code/day10/inputPart1.txt");
        var map = new TopographicMap(input);
        var result = map.trailHeads()
                .stream()
                .map(map::score)
                .mapToInt(Integer::intValue)
                .sum();
        assertThat(result).isEqualTo(746);
    }
}
