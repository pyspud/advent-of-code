package advent.of.code.days.four;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public record ScratchCard(int card, Set<Integer> winning, Set<Integer> numbers) {
    
    public Set<Integer> matchingNumbers() {
        var intersection = new HashSet<>(winning);
        intersection.retainAll(numbers);
        return intersection;
    }

    public static ScratchCard of(String line) {
        var parts = line.split("[:\\|]");
        var num = Integer.parseInt(parts[0].substring("Game ".length()).strip());

        return new ScratchCard(num, parseNumbersString(parts[1]), parseNumbersString(parts[2]));
    }

    private static Set<Integer> parseNumbersString(String line) {
        var numberStings = line.split(" +");
        return Arrays.stream(numberStings)
                .filter(Predicate.not(String::isBlank))
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
    }

}
