package advent.of.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day9 {
    @SuppressWarnings("java:S6218")
    record Oasis(long[] values) {
        static Oasis of(String line) {
            var numbers = line.split(" ");
            var longs = Stream.of(numbers)
                    .mapToLong(Long::parseLong)
                    .toArray();
            return new Oasis(longs);
        }

        static long nextValue(Oasis value) {
            var size = value.values.length;
            var last = value.values[size - 1];
            if (size == 1 || last == 0)
                return last;
            else
                return last+nextValue(differences(value));
        }


        static long previousValue( Oasis value) {
            var size = value.values.length;
            var last = value.values[size - 1];
            if (size == 1 || last == 0)
                return value.values[0];
            else
                return value.values[0]-previousValue(differences(value));
        }
        
        static Oasis differences(Oasis value) {
            var diffs= IntStream.range(0, value.values.length - 1)
                    .mapToLong(i -> value.values[i + 1] - value.values[i])
                    .toArray();
            // System.out.println(Arrays.toString(diffs));
            return new Oasis(diffs);
        }
    }

    static long part1(Stream<String> history) {
        return history
                .map(Oasis::of)
                .mapToLong(Oasis::nextValue)
                .sum();

    }

    static long part2(Stream<String> history) {
        return history
                .map(Oasis::of)
                .mapToLong(Oasis::previousValue)
                .sum();
}

    public static void main(String... args) throws IOException {
        var inputFile = Path.of("input.txt");
        System.out.println("Using inputFile = " + inputFile.toAbsolutePath());
        try (var lines = Files.lines(inputFile)) {
            var result1 = part1(lines); 
            System.out.println("Part 1 = "+result1); 
        }
        try (var lines = Files.lines(inputFile)) {
            //Part 2
            var result2 = part2(lines); 
            System.out.println("Part 2 = "+result2); 
        }

        //Part 1
    }
}
