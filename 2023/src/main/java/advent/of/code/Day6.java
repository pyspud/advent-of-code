package advent.of.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import advent.of.code.days.six.ToyBoats;
import advent.of.code.days.six.ToyBoats.Race;

public class Day6 {
    static long part1(ToyBoats competition) {
        var races = competition.races();
        return races.stream()
                .map(Race::newRecords)
                .mapToInt(List::size)
                .reduce(1, (a, b) -> a * b);
    }

    static long part2(List<String> lines) {
        var race = ToyBoats.singleRace(lines);
        return race.newRecords().size();
    }

    public static void main(String... args) throws IOException {
        var inputFile = Path.of("input.txt");
        System.out.println("Using inputFile = " + inputFile.toAbsolutePath());
        var lines = Files.readAllLines(inputFile);

        var competition = new ToyBoats(lines);
        //Part 1
        var result1 = part1(competition); 
        System.out.println("Part 1 = "+result1); 
        //Part 2
        var result2 = part2(lines); 
        System.out.println("Part 2 = "+result2); 
    }
}
