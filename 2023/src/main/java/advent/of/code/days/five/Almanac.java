package advent.of.code.days.five;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Almanac {
    long[] seeds;
    List<Range> seedToSoil = new ArrayList<>();
    List<Range> soilToFertilizer = new ArrayList<>();
    List<Range> fertilizerToWater = new ArrayList<>();
    List<Range> waterToLight = new ArrayList<>();
    List<Range> lightToTemperature = new ArrayList<>();
    List<Range> temperatureToHumidity = new ArrayList<>();
    List<Range> humidityToLocation = new ArrayList<>();

    public Almanac(Path input) throws IOException {
        var lines = Files.readAllLines(input);
        // Seeds list
        var firstLine = Arrays.asList(lines.get(0).split("\\s"));
        System.out.println("firstLine = " + firstLine);
        seeds = firstLine.stream()
                .skip(1)
                .mapToLong(Long::parseLong)
                .toArray();

        // seed-to-soil map:
        var currentLine = 3;
        while (!lines.get(currentLine).isBlank()) {
            seedToSoil.add(Range.of(lines.get(currentLine)));
            currentLine++;
        }
        currentLine = currentLine + 2;

        // soil-to-fertilizer map:
        while (!lines.get(currentLine).isBlank()) {
            soilToFertilizer.add(Range.of(lines.get(currentLine)));
            currentLine++;
        }
        currentLine = currentLine + 2;

        // fertilizer-to-water map:
        while (!lines.get(currentLine).isBlank()) {
            fertilizerToWater.add(Range.of(lines.get(currentLine)));
            currentLine++;
        }
        currentLine = currentLine + 2;

        // water-to-light map:
        while (!lines.get(currentLine).isBlank()) {
            waterToLight.add(Range.of(lines.get(currentLine)));
            currentLine++;
        }
        currentLine = currentLine + 2;

        // light-to-temperature map:
        while (!lines.get(currentLine).isBlank()) {
            lightToTemperature.add(Range.of(lines.get(currentLine)));
            currentLine++;
        }
        currentLine = currentLine + 2;

        // temperature-to-humidity map:
        while (!lines.get(currentLine).isBlank()) {
            temperatureToHumidity.add(Range.of(lines.get(currentLine)));
            currentLine++;
        }
        currentLine = currentLine + 2;

        // humidity-to-location map:
        while (currentLine < lines.size()) {
            humidityToLocation.add(Range.of(lines.get(currentLine)));
            currentLine++;
        }
    }

    public record Range(long source, long destination, long length) {
        public static Range of(String line) {
            var numbers = line.split("\\s");

            var length = Integer.parseInt(numbers[2]);
            var destination = Long.parseLong(numbers[0]);
            var source = Long.parseLong(numbers[1]);

            return new Range(source, destination, length);
        }

        public Optional<Long> destinationInRange(Long location) {
            var diff = location.longValue()-source;
            if (diff >= 0l && diff < length) {
                return Optional.of(Long.valueOf(destination + diff));
            }
            return Optional.empty();
        }
    }
    
    public long[] seeds() {
        return seeds;
    }


    public long seedToSoil(long source) {
        return find(seedToSoil, source);
    }

    public long soilToFertilizer(long source) {
        return find(soilToFertilizer, source);
    }

    public long fertilizerToWater(long source) {
        return find(fertilizerToWater, source);
    }

    public long waterToLight(long source) {
        return find(waterToLight, source);
    }

    public long lightToTemperature(long source) {
        return find(lightToTemperature, source);
    }

    public long temperatureToHumidity(long source) {
        return find(temperatureToHumidity, source);
    }

    public long humidityToLocation(long source) {
        return find(humidityToLocation, source);
    }
    
    static long find(List<Range> ranges, long source) {
        return ranges.stream()
                .map(r -> r.destinationInRange(source))
                .flatMap(Optional::stream)
                .findAny().orElse(source).longValue();
    }
    
}
