package advent.of.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import advent.of.code.days.seven.Hand;

public class Day7 {
    static long part1(List<String> hands) {
        var deal = hands.stream()
                .map(Hand::parse)
                .toList();

        var game = Hand.sort(deal);
        return Hand.score(game);
    }

    static int part2(List<String> lines) {
        // TODO
        return 0;
}

    public static void main(String... args) throws IOException {
        var inputFile = Path.of("input.txt");
        System.out.println("Using inputFile = " + inputFile.toAbsolutePath());
        var lines = Files.readAllLines(inputFile);

        //Part 1
        var result1 = part1(lines); 
        System.out.println("Part 1 = "+result1); 
        //Part 2
        var result2 = part2(lines); 
        System.out.println("Part 2 = "+result2); 
    }
}
