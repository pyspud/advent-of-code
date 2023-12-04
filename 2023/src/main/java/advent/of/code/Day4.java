package advent.of.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

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
        try (var lines = Files.lines(inputFile)) {
            return (int)lines.count();
        }
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
