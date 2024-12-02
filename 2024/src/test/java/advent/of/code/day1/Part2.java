package advent.of.code.day1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class Part2 {
    static final String TWO_LISTS = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """;

    @Test
    void shouldFindTheCountsOfExample() {
        final  List<Pair> expected = List.of(
            new Pair(3,3),
            new Pair(4,1),
            new Pair(2,0),
            new Pair(1,0),
            new Pair(3,3),
            new Pair(3,3)
        );

        try (var lines = TWO_LISTS.lines()) {
            var pairs = lines.map(Pair::of).toList();

            var rightCounts = Utils.getCounts(pairs);

            assertThat(rightCounts).isEqualTo(expected);
        }
    }

    @Test
    void shouldFindTheSimilarityScoreOfExample() {

        try (var lines = TWO_LISTS.lines()) {
            var pairs = lines.map(Pair::of).toList();

            var rightCounts = Utils.getCounts(pairs);

            var similarityScore = Utils.similarityScore(rightCounts);
            assertThat(similarityScore).isEqualTo(31);
        }
    }

    @Test
    void whatIsTheSimilarityScoreOfYourLists() {

        try (var lines = advent.of.code.Utils.inputLines("/advent/of/code/day1/inputPart1.txt")) {
            var pairs = lines.map(Pair::of).toList();

            var rightCounts = Utils.getCounts(pairs);

            var similarityScore = Utils.similarityScore(rightCounts);
            assertThat(similarityScore).isEqualTo(19097157);
        }
    }
}
