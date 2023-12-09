package advent.of.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import advent.of.code.days.seven.Card;
import advent.of.code.days.seven.Hand;

class Day7Test {
    // Part 1
    // In Camel Cards, you get a list of hands, and your goal is to order them based on the strength of each hand.
    // A hand consists of five cards labelled one of A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2.
    // The relative strength of each card follows this order, where A is the highest and 2 is the lowest.
    @Test
    void aceShouldBeHighest() {
        assertThat(Card.of('a'))
                .isGreaterThan(Card.L5)
                .isGreaterThan(Card.T)
                .isEqualTo(Card.A);
    }
    @Test
    void nineShouldBeMiddle() {
        assertThat(Card.of('9'))
                .isGreaterThan(Card.L5)
                .isLessThan(Card.T)
                .isEqualTo(Card.L9);
    }
    @Test
    void twoShouldBeLowest() {
        assertThat(Card.of('2'))
                .isLessThan(Card.L5)
                .isLessThan(Card.T)
                .isEqualTo(Card.L2);
    }
    // Every hand is exactly one type. From strongest to weakest, they are:

    // Five of a kind, where all five cards have the same label: AAAAA
    @Test
    void shouldFindFiveOfAKind() {
        var actual = Hand.of("AAAAA", 0);
        SoftAssertions.assertSoftly(s->{
            s.assertThat(actual).extracting(Hand::type).isEqualTo(Hand.Type.FIVE_OF_A_KIND);
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.FOUR_OF_A_KIND,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.FULL_HOUSE,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.TWO_PAIR,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.HIGH_CARD,0));
        });
    }
    // Four of a kind, where four cards have the same label and one card has a different label: AA8AA
    @Test
    void shouldFindFourOfAKind() {
        var actual = Hand.of("AA8AA", 0);
        SoftAssertions.assertSoftly(s->{
            s.assertThat(actual).extracting(Hand::type).isEqualTo(Hand.Type.FOUR_OF_A_KIND);
            s.assertThat(actual).isLessThan(new Hand(List.of(), Hand.Type.FIVE_OF_A_KIND,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.FULL_HOUSE,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.TWO_PAIR,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.HIGH_CARD,0));
        });
    }
    // Full house, where three cards have the same label, and the remaining two cards share a different label: 23332
    @Test
    void shouldFindFullHouse() {
        var actual = Hand.of("23332", 0);
        SoftAssertions.assertSoftly(s->{
            s.assertThat(actual).extracting(Hand::type).isEqualTo(Hand.Type.FULL_HOUSE);
            s.assertThat(actual).isLessThan(new Hand(List.of(), Hand.Type.FOUR_OF_A_KIND,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.THREE_OF_A_KIND,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.TWO_PAIR,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.HIGH_CARD,0));
        });
    }
    // Three of a kind, where three cards have the same label, and the remaining two cards are each different from any other card in the hand: TTT98
    @Test
    void shouldFindThreeOfAKind() {
        var actual = Hand.of("TTT98", 0);
        SoftAssertions.assertSoftly(s->{
            s.assertThat(actual).extracting(Hand::type).isEqualTo(Hand.Type.THREE_OF_A_KIND);
            s.assertThat(actual).isLessThan(new Hand(List.of(), Hand.Type.FOUR_OF_A_KIND,0));
            s.assertThat(actual).isLessThan(new Hand(List.of(), Hand.Type.FULL_HOUSE,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.TWO_PAIR,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.HIGH_CARD,0));
        });
    }
    // Two pair, where two cards share one label, two other cards share a second label, and the remaining card has a third label: 23432
    @Test
    void shouldFindTwoPair() {
        var actual = Hand.of("23432", 0);
        SoftAssertions.assertSoftly(s->{
            s.assertThat(actual).extracting(Hand::type).isEqualTo(Hand.Type.TWO_PAIR);
            s.assertThat(actual).isLessThan(new Hand(List.of(), Hand.Type.FOUR_OF_A_KIND,0));
            s.assertThat(actual).isLessThan(new Hand(List.of(), Hand.Type.FULL_HOUSE,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.ONE_PAIR,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.HIGH_CARD,0));
        });
    }
    // One pair, where two cards share one label, and the other three cards have a different label from the pair and each other: A23A4
    @Test
    void shouldFindOnePair() {
        var actual = Hand.of("A23A4", 0);
        SoftAssertions.assertSoftly(s->{
            s.assertThat(actual).extracting(Hand::type).isEqualTo(Hand.Type.ONE_PAIR);
            s.assertThat(actual).isLessThan(new Hand(List.of(), Hand.Type.FOUR_OF_A_KIND,0));
            s.assertThat(actual).isLessThan(new Hand(List.of(), Hand.Type.FULL_HOUSE,0));
            s.assertThat(actual).isLessThan(new Hand(List.of(), Hand.Type.TWO_PAIR,0));
            s.assertThat(actual).isGreaterThan(new Hand(List.of(), Hand.Type.HIGH_CARD,0));
        });
    }
    // High card, where all cards' labels are distinct: 23456
    @Test
    void shouldFindHighCard() {
        var actual = Hand.of("23456", 0);
        SoftAssertions.assertSoftly(s->{
            s.assertThat(actual).extracting(Hand::type).isEqualTo(Hand.Type.HIGH_CARD);
            s.assertThat(actual).isLessThan(new Hand(List.of(), Hand.Type.FOUR_OF_A_KIND,0));
            s.assertThat(actual).isLessThan(new Hand(List.of(), Hand.Type.FULL_HOUSE,0));
            s.assertThat(actual).isLessThan(new Hand(List.of(), Hand.Type.TWO_PAIR,0));
            s.assertThat(actual).isLessThan(new Hand(List.of(), Hand.Type.ONE_PAIR,0));
        });
        
    }
    // Hands are primarily ordered based on type; for example, every full house is stronger than any three of a kind.

    // If two hands have the same type, a second ordering rule takes effect. 
    // Start by comparing the first card in each hand. 
    // If these cards are different, the hand with the stronger first card is considered stronger.
    // If the first card in each hand have the same label, however, then move on to considering the second card in each hand.
    // If they differ, the hand with the higher second card wins; otherwise, continue with the third card in each hand, then the fourth, then the fifth.

    // So, 33332 and 2AAAA are both four of a kind hands,
    // but 33332 is stronger because its first card is stronger.
    @Test
    void shouldBeStrongerOnFirstCard() {
        var expectStronger = Hand.of("33332", 0);
        var expectWeaker = Hand.of("2AAAA", 0);
        SoftAssertions.assertSoftly(s->{
            s.assertThat(expectStronger).extracting(Hand::type).isEqualTo(Hand.Type.FOUR_OF_A_KIND);
            s.assertThat(expectWeaker).extracting(Hand::type).isEqualTo(Hand.Type.FOUR_OF_A_KIND);
            s.assertThat(expectStronger).isGreaterThan(expectWeaker);
        });
    }
    // Similarly, 77888 and 77788 are both a full house,
    // but 77888 is stronger because its third card is stronger (and both hands have the same first and second card).
    @Test
    void shouldBeStrongerOnThirdCard() {
        var expectStronger = Hand.of("77888", 0);
        var expectWeaker = Hand.of("77788", 0);
        SoftAssertions.assertSoftly(s->{
            s.assertThat(expectStronger).extracting(Hand::type).isEqualTo(Hand.Type.FULL_HOUSE);
            s.assertThat(expectWeaker).extracting(Hand::type).isEqualTo(Hand.Type.FULL_HOUSE);
            s.assertThat(expectStronger).isGreaterThan(expectWeaker);
        });
    }
    // To play Camel Cards, you are given a list of hands and their corresponding bid (your puzzle input). For example:
    final static List<String> INPUT = List.of(
            "32T3K 765",
            "T55J5 684",
            "KK677 28",
            "KTJJT 220",
            "QQQJA 483"
    );
    final static Hand FIRST_HAND = Hand.of("32T3K", 765);
    final static Hand SECOND_HAND = Hand.of("T55J5", 684);
    final static Hand THIRD_HAND = Hand.of("KK677", 28);
    final static Hand FOURTH_HAND = Hand.of("KTJJT", 220);
    final static Hand FIFTH_HAND = Hand.of("QQQJA", 483);

    final static List<Hand> INPUT_HANDS = List.of(FIRST_HAND, SECOND_HAND, THIRD_HAND, FOURTH_HAND, FIFTH_HAND);
    // This example shows five hands; each hand is followed by its bid amount. Each hand wins an amount equal to its bid multiplied by its rank, where the weakest hand gets rank 1, the second-weakest hand gets rank 2, and so on up to the strongest hand. Because there are five hands in this example, the strongest hand will have rank 5 and its bid will be multiplied by 5.
    @Test
    void shouldParseInput() {
        var actual = INPUT.stream()
                .map(Hand::parse)
                .toList();
        assertThat(actual).isEqualTo(INPUT_HANDS);
    }
    // So, the first step is to put the hands in order of strength:

    // 32T3K is the only one pair and the other hands are all a stronger type, so it gets rank 1.
    // KK677 and KTJJT are both two pair. Their first cards both have the same label,
    //  but the second card of KK677 is stronger (K vs T), so KTJJT gets rank 2 and KK677 gets rank 3.
    // T55J5 and QQQJA are both three of a kind.
    //  QQQJA has a stronger first card,
    //  so it gets rank 5 and T55J5 gets rank 4.
    @Test
    void shouldSortHands() {
        var expected = List.of(FIRST_HAND, FOURTH_HAND, THIRD_HAND, SECOND_HAND, FIFTH_HAND);
        var actual = Hand.sort(INPUT_HANDS);
        assertThat(actual).isEqualTo(expected);
    }
    // Now, you can determine the total winnings of this set of hands by adding up the result of multiplying each hand's bid with its rank
    // (765 * 1 + 220 * 2 + 28 * 3 + 684 * 4 + 483 * 5). So the total winnings in this example are 6440.
    @Test
    void shouldScore() {
        var game = Hand.sort(INPUT_HANDS);
        var actual = Hand.score(game);
        assertThat(actual).isEqualTo(6440);
    }
}
