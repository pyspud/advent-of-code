package advent.of.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.LongStream;

import advent.of.code.days.five.Almanac;

public class Day5 {

    static long part1(Almanac almanac) {
        return LongStream.of(almanac.seeds())
            .map(almanac::seedToSoil)
            .map(almanac::soilToFertilizer)
            .map(almanac::fertilizerToWater)
            .map(almanac::waterToLight)
            .map(almanac::lightToTemperature)
            .map(almanac::temperatureToHumidity)
            .map(almanac::humidityToLocation)
            .map(n->n)
            .min().orElse(0);
    }

    static long part2(Almanac almanac) {
        return almanac.seedRanges().stream()
            .flatMapToLong(s->s.stream())
            .map(almanac::seedToSoil)
            .map(almanac::soilToFertilizer)
            .map(almanac::fertilizerToWater)
            .map(almanac::waterToLight)
            .map(almanac::lightToTemperature)
            .map(almanac::temperatureToHumidity)
            .map(almanac::humidityToLocation)
            .map(n->n)
            .min().orElse(0);
    }

    public static void main(String... args) throws IOException {
        var inputFile = Path.of("input.txt");
        System.out.println("Using inputFile = " + inputFile.toAbsolutePath());
        var almanac = new Almanac(inputFile);
        //Part 1
        var result1 = part1(almanac); 
        System.out.println("Part 1 = "+result1); 
        //Part 2
        var result2 = part2(almanac); 
        System.out.println("Part 2 = "+result2); 
    }
}
