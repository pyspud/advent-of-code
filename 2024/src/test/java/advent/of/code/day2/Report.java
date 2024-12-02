package advent.of.code.day2;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

record Report(long[] levels) {
    @Override
    public boolean equals(Object o) {
        if (o instanceof Report r) {
            return Arrays.equals(r.levels(), levels);
        }
        return false;
    }
    static Report of(String line) {
        var levelStrings = line.split(" ");
        return new Report(Stream.of(levelStrings).mapToLong(Long::parseLong).toArray());
    }

    boolean isIncreasing() {
        var ordered = LongStream.of(levels).sorted().toArray();
        return Arrays.equals(ordered, levels);
    }

    boolean isDecreasing() {
        var ordered = LongStream.of(levels).sorted().toArray();
        var reversed = IntStream.rangeClosed(1, ordered.length)
                .mapToLong(i -> ordered[ordered.length - i])
                .toArray();
        return Arrays.equals(reversed, levels);
    }

    boolean withinRange() {
        return IntStream.rangeClosed(1, levels.length - 2)
                .allMatch(i -> {
                    var lowerDiff = Math.abs(levels[i - 1] - levels[i]);
                    var upperDiff = Math.abs(levels[i] - levels[i + 1]);
                    var minDiff = Math.min(lowerDiff, upperDiff);
                    var maxDiff = Math.max(lowerDiff, upperDiff);
                    return 0 < minDiff && maxDiff <= 3;
                });
    }

    boolean isSafe() {
        return withinRange() && (isDecreasing() || isIncreasing());
    }

    long[] copyArrayWithout(int i) {
        var levelList = LongStream.of(levels).mapToObj(Long::valueOf).collect(Collectors.toList());
        levelList.remove(i);
        return levelList.stream().mapToLong(Long::longValue).toArray();
    }

    boolean safeWithProblemDampener() {
        if (isSafe()) {
            return true;
        }

        return IntStream.range(0, levels.length)
                .mapToObj(i -> copyArrayWithout(i))
                .map(Report::new)
                .anyMatch(Report::isSafe);
    }
}
