package advent.of.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import advent.of.code.days.four.ScratchCard;

class Day4Test {
    private static final Set<Integer> CARD_1_MATCHING = Set.of(48, 83, 17, 86);
    private static final Set<Integer> CARD_3_MATCHING = Set.of(1,21);
    private static final Set<Integer> CARD_4_MATCHING = Set.of(84);

    // The Elf leads you over to the pile of colourful cards. There, you discover dozens of scratch-cards,
    // all with their opaque covering already scratched off. Picking one up, 
    // it looks like each card has two lists of numbers separated by a vertical bar (|):
    // a list of winning numbers and then a list of numbers you have.
    //  You organize the information into a table (your puzzle input).
    @Test
    void shouldParseLine() {
        var line = "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1";
        var winning = Set.of(1, 21, 53, 59, 44);
        var numbers = Set.of(1, 69, 82, 63, 72, 16, 21, 14);
        var expected = new ScratchCard(3, winning, numbers);
        var actual = ScratchCard.of(line);
        assertThat(actual).isEqualTo(expected);
    }
    // As far as the Elf has been able to figure out, 
    // you have to figure out which of the numbers you have appear in the list of winning numbers. 
    // The first match makes the card worth one point 
    // and each match after the first doubles the point value of that card.

    // For example:

    // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
    // Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
    // Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
    // Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
    // Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
    // Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11

    // In the above example, 
    // card 1 has five winning numbers (41, 48, 83, 86, and 17) 
    // and eight numbers you have (83, 86, 6, 31, 17, 9, 48, and 53). 
    // Of the numbers you have, four of them (48, 83, 17, and 86) are winning numbers! 
    // That means card 1 is worth 8 points (1 for the first match, 
    // then doubled three times for each of the three matches after the first).
    @ParameterizedTest
    @MethodSource("knownMatches")
    void shouldFindMatching(String line, Set<Integer> expected) {
        var card = ScratchCard.of(line);
        var actual = card.matchingNumbers();
        assertThat(actual).isEqualTo(expected);
    }
    
    static Stream<Arguments> knownMatches() {
        return Stream.of(
            arguments("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",CARD_1_MATCHING),
            arguments("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",CARD_3_MATCHING),
            arguments("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",CARD_4_MATCHING),
            arguments("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",Set.of())
        );
    }

    // Card 2 has two winning numbers (32 and 61), so it is worth 2 points.
    // Card 3 has two winning numbers (1 and 21), so it is worth 2 points.
    // Card 4 has one winning number (84), so it is worth 1 point.
    // Card 5 has no winning numbers, so it is worth no points.
    // Card 6 has no winning numbers, so it is worth no points.

    @ParameterizedTest
    @MethodSource("knownScores")
    void shouldScore(Set<Integer> numbers, int expected) {
        var actual = Day4.score(numbers);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> knownScores() {
        return Stream.of(
            arguments(CARD_1_MATCHING, 8),
            arguments(CARD_3_MATCHING, 2),
            arguments(CARD_4_MATCHING, 1),
            arguments(Set.of(), 0)
        );
    }
    
    // So, in this example, the Elf's pile of scratch-cards is worth 13 points.
    @Test
    void exampleForPart1ShouldBeWorth13() throws URISyntaxException, IOException {
        var input = Path.of(Day4Test.class.getResource("/Day4/input1.txt").toURI());
        var actual = Day4.part1(input);
        assertThat(actual).isEqualTo(13);
    }
    
    
    // Part 2
    // Copies of scratch-cards are scored like normal scratch-cards and have the same card number as the card they copied.
    //  So, if you win a copy of card 10 and it has 5 matching numbers,
    //  it would then win a copy of the same cards that the original card 10 won: cards 11, 12, 13, 14, and 15. 
    // This process repeats until none of the copies cause you to win any more cards. 
    // (Cards will never make you copy a card past the end of the table.)
    
    // This time, the above example goes differently:
    
    // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
    // Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
    // Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
    // Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
    // Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
    // Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
    
    // Card 1 has four matching numbers,
    //  so you win one copy each of the next four cards: cards 2, 3, 4, and 5.
    
    // Your original card 2 has two matching numbers,
    //  so you win one copy each of cards 3 and 4.
    // Your copy of card 2 also wins one copy each of cards 3 and 4.
    // Your four instances of card 3 (one original and three copies) have two matching numbers,
    //  so you win four copies each of cards 4 and 5.
    // Your eight instances of card 4 (one original and seven copies) have one matching number,
    //  so you win eight copies of card 5.
    // Your fourteen instances of card 5 (one original and thirteen copies)
    //  have no matching numbers and win no more cards.
    // Your one instance of card 6 (one original) has no matching numbers and wins no more cards.
    final int[] EXPECTED_INSTANCES = { 1, 2, 4, 8, 14, 1 };

    @Test
    void shouldGetCardCounts() throws IOException, URISyntaxException {
        var input = Path.of(Day4Test.class.getResource("/Day4/input1.txt").toURI());
        var lines = Files.readAllLines(input);
        var actual = Day4.getCardCounts(lines);
        assertThat(actual).hasSize(6).containsExactly(EXPECTED_INSTANCES);
    }
    // Once all of the originals and copies have been processed, 
    // you end up with 1 instance of card 1, 
    // 2 instances of card 2, 
    // 4 instances of card 3, 
    // 8 instances of card 4, 
    // 14 instances of card 5, 
    // and 1 instance of card 6. 
    
    // In total, this example pile of scratch-cards causes you to ultimately have 30 scratch-cards!
    // 
    // Process all of the original and copied scratch-cards until no more scratch-cards are won.
    @Test
    void exampleForPart2ShouldBeWorth30() throws URISyntaxException, IOException {
        var input = Path.of(Day4Test.class.getResource("/Day4/input1.txt").toURI());
        var actual = Day4.part2(input);
        assertThat(actual).isEqualTo(30);
    }
    
    // Including the original set of scratch-cards, how many total scratch-cards do you end up with?
}
