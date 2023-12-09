package advent.of.code.days.seven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


public record Hand(List<Card> cards, Type type ,int bid) implements Comparable<Hand> {
    
    public enum Type {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND;
        
        static Type of(String s) {
            Map<Character, Integer> frequencies = s.chars()
            .mapToObj(c -> (char) c).collect(
                Collectors.groupingBy(c -> c,
                Collectors.collectingAndThen(
                    Collectors.counting(), Long::intValue)));
            
            if (frequencies.size() == 1) {
                // All same
                return FIVE_OF_A_KIND;
            }
            if (frequencies.size() == 5) {
                // All different
                return HIGH_CARD;
            }
            if (frequencies.size()==4){
                return ONE_PAIR;
            }
            if (frequencies.size() == 2) {
                // Can be 4 of a kind or full house
                for (var entry : frequencies.entrySet()) {
                    if (entry.getValue().equals(Integer.valueOf(4))) {
                        return FOUR_OF_A_KIND;
                    }
                }
                return FULL_HOUSE;
            }
            // Size of 3 could be 2 pair or 3 of a kind
            for (var entry : frequencies.entrySet()) {
                if (entry.getValue().equals(Integer.valueOf(3))) {
                    return THREE_OF_A_KIND;
                }
            }
            return TWO_PAIR;
        }
    }

    public static Hand of(String cardString, int bid) {
        List<Card> cards = IntStream.range(0, 5).mapToObj(n -> Card.of(cardString.charAt(n))).toList();
        return new Hand(cards, Type.of(cardString), bid);
    }

    public static Hand parse(String line) {
        var parts = line.split(" ");
        return of(parts[0], Integer.parseInt(parts[1]));
    }


    @Override
    public int compareTo(Hand o) {
        // Hand Type is First Ordering
        var typeCompare = type.compareTo(o.type());
        if (typeCompare != 0) {
            return typeCompare;
        }

        // Second Ordering is Card
        var other = o.cards();
        for (int i = 0; i < other.size(); i++) {
            var cardCompare = cards.get(i).compareTo(other.get(i));
            if (cardCompare != 0) {
                return cardCompare;
            }
        }
        // Same
        return 0;
    }
    
    public static List<Hand> sort(List<Hand> hand) {
        List<Hand> copy = new ArrayList<>(hand);
        Collections.sort(copy);
        return copy;
    }
    
    
    public static long score(List<Hand> game) {
        return LongStream.range(0, game.size())
            .map(n -> game.get((int)n).bid()*(n+1))
            .sum();
    }
}
