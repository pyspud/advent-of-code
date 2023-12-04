package advent.of.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import advent.of.code.days.four.ScratchCard;

public class Day4 {

    static int score(Set<Integer> matchingNumbers) {
        if (matchingNumbers.isEmpty()) {
            return 0;
        } else if (matchingNumbers.size() == 1) {
            return 1;
        }
        var matchesAfterFirst= matchingNumbers.size()-2;
        return 2 << matchesAfterFirst;
    }

    static int part1(Path inputFile) throws IOException {
        try(var lines = Files.lines(inputFile)) {

            var scratchCards= lines.map(ScratchCard::of).toList();
            var matchingNumbers = scratchCards.stream().map(ScratchCard::matchingNumbers);
            return matchingNumbers.mapToInt(Day4::score).sum();
        }
    }

    static int part2(Path inputFile) throws IOException {
        var lines = Files.readAllLines(inputFile);

        int[] cardCounts = getCardCounts(lines);
        return Arrays.stream(cardCounts).sum();
    }

    static int[] getCardCounts(List<String> lines) {
        int[] cardCounts = IntStream.range(0, lines.size()).toArray();
        Arrays.fill(cardCounts, 1);

        for (int original = 0; original < lines.size(); original++) {
            var card = ScratchCard.of(lines.get(original));
            var numberOfMatches = card.matchingNumbers();

            for (int copy = 1; copy <= numberOfMatches.size(); copy++) {
                cardCounts[original+copy]=cardCounts[original+copy]+cardCounts[original];
            }
        }
        return cardCounts;
    }

    public static void main(String... args) throws IOException {
        var inputFile = Path.of("input.txt");
        System.out.println("Using inputFile = " + inputFile.toAbsolutePath());
        //Part 1
        var result1 = part1(inputFile); 
        System.out.println("Part 1 = "+result1); 
        //Part 2
        var result2 = part2(inputFile); 
        System.out.println("Part 2 = "+result2); 
    }
}
