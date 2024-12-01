package advent.of.code.day1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class Part2 {
    // The Historians can't agree on which group made the mistakes or how to read most of the Chief's handwriting,
    // but in the commotion you notice an interesting detail: a lot of location IDs appear in both lists!
    // Maybe the other numbers aren't location IDs at all but rather misinterpreted handwriting.

    // This time, you'll need to figure out exactly how often each number from the left list appears in the right list. Calculate a total similarity score by adding up each number in the left list after multiplying it by the number of times that number appears in the right list.

    // Here are the same example lists again:
    static final String TWO_LISTS = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """;

    // For these example lists, here is the process of finding the similarity score:

    // The first number in the left list is 3. It appears in the right list three times, so the similarity score increases by 3 * 3 = 9.
    // The second number in the left list is 4. It appears in the right list once, so the similarity score increases by 4 * 1 = 4.
    // The third number in the left list is 2. It does not appear in the right list, so the similarity score does not increase (2 * 0 = 0).
    // The fourth number, 1, also does not appear in the right list.
    // The fifth number, 3, appears in the right list three times; the similarity score increases by 9.
    // The last number, 3, appears in the right list three times; the similarity score again increases by 9.
    // So, for these example lists, the similarity score at the end of this process is 31 (9 + 4 + 0 + 0 + 9 + 9).
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

    // Once again consider your left and right lists. What is their similarity score?
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

        try (var lines = Utils.inputLines("/advent/of/code/day1/inputPart1.txt")) {
            var pairs = lines.map(Pair::of).toList();

            var rightCounts = Utils.getCounts(pairs);

            var similarityScore = Utils.similarityScore(rightCounts);
            assertThat(similarityScore).isEqualTo(19097157);
        }
    }
}
