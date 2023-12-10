package advent.of.code.days.seven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


public record Hand(List<Card> cards, Type type, int bid, Rule rule) implements Comparable<Hand> {
    
    public Hand(List<Card> cards, Type type, int bid) {
        this(cards, type, bid, Rule.STANDARD);
    }
    enum Rule {
        STANDARD, JOKER;
    }
    
    public enum Type {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND;
        
        static Type of(String s) {
            return of(s, Rule.STANDARD);
        }

        @SuppressWarnings("java:S3776")
        static Type of(String s,Rule rule) {
            Map<Character, Integer> frequencies = s.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(
                        c -> c,Collectors.collectingAndThen(
                                    Collectors.counting(), Long::intValue)));
            var jokers = 0;
            if (rule == Rule.JOKER && frequencies.containsKey(Character.valueOf('J'))) {
                jokers = frequencies.get(Character.valueOf('J'));
            }
            if (frequencies.containsValue(Integer.valueOf(5))) {
                // All same
                return FIVE_OF_A_KIND;
            } else if (frequencies.containsValue(Integer.valueOf(4))) {
                if (jokers == 1 || jokers == 4) {
                    return FIVE_OF_A_KIND;
                }
                return FOUR_OF_A_KIND;
            } else if (frequencies.containsValue(Integer.valueOf(3))){
                if (frequencies.size() == 2 && jokers >= 2) {
                    return FIVE_OF_A_KIND;
                } else if(frequencies.size() == 3 && jokers==1) {
                    return FOUR_OF_A_KIND;
                } else if(frequencies.size() == 2 && jokers==0) {
                    return FULL_HOUSE;
                }
                return THREE_OF_A_KIND;
            } else if(frequencies.containsValue(Integer.valueOf(2))){
                if (frequencies.size() == 4 && jokers == 2) {
                    return THREE_OF_A_KIND;
                } else if (frequencies.size() == 3 && jokers == 2) {
                    return FOUR_OF_A_KIND;
                } else if (frequencies.size() == 3 && jokers == 1) {
                    return FULL_HOUSE;
                } else if(frequencies.size() == 3 && jokers == 0) {
                    return TWO_PAIR;
                } else if (frequencies.size() == 4 && jokers == 1) {
                    return THREE_OF_A_KIND;
                }
                return ONE_PAIR;
            } else if (jokers == 1) {
                return ONE_PAIR;
            }
            return HIGH_CARD;
        }
    }

    public static Hand of(String cardString, int bid) {
        List<Card> cards = IntStream.range(0, 5).mapToObj(n -> Card.of(cardString.charAt(n))).toList();
        return new Hand(cards, Type.of(cardString), bid, Rule.STANDARD);
    }

    public static Hand withJoker(String cardString, int bid) {
        List<Card> cards = IntStream.range(0, 5).mapToObj(n -> Card.of(cardString.charAt(n))).toList();
        return new Hand(cards, Type.of(cardString,Rule.JOKER), bid, Rule.JOKER);
    }

    public static Hand parse(String line) {
        var parts = line.split(" ");
        return of(parts[0], Integer.parseInt(parts[1]));
    }
    public static Hand parseWithJoker(String line) {
        var parts = line.split(" ");
        return withJoker(parts[0], Integer.parseInt(parts[1]));
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
            var thisCard = cards.get(i);
            var otherCard = other.get(i);
            if (rule == Rule.JOKER && thisCard != Card.J && otherCard == Card.J) {
                return 1;
            }
            else if (rule == Rule.JOKER && thisCard == Card.J && otherCard != Card.J) {
                return -1;
            } else {
                var cardCompare = thisCard.compareTo(otherCard);
                if (cardCompare != 0) {
                    return cardCompare;
                }
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
