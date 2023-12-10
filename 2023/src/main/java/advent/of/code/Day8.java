package advent.of.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import advent.of.code.days.eight.JourneyMap;

public class Day8 {

    public static void main(String... args) throws IOException {
        var inputFile = Path.of("input.txt");
        System.out.println("Using inputFile = " + inputFile.toAbsolutePath());
        var lines = Files.readAllLines(inputFile);
        var journey = new JourneyMap(lines);
        // Part 1
        var result1 = journey.steps();
        System.out.println("Part 1 = " + result1);
        // Part 2
        var result2 = journey.parallelSteps();
        System.out.println("Part 2 = " + result2);
    }
}
