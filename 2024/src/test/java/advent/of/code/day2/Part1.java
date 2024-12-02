package advent.of.code.day2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import advent.of.code.Utils;

class Part1 {
    @Test
    void shouldParseLevelToRecord() {
        long[] levels = {1l, 4l, 6l, 7l, 9l};
        var expected = new Report(levels);
        var actual = Report.of("1 4 6 7 9");
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"1 2 3 3 5",
        "1 3 6 7 9",
        "1 2 5 8 9"})
    void shouldIdentifyAlwaysIncreasing(String line) {
        var report = Report.of(line);
        assertThat(report).returns(true, Report::isIncreasing);
    }

    @ParameterizedTest
    @CsvSource({"2 1 4 3 5",
        "1 3 2 7 9",
        "1 2 5 4 9"})
    void shouldFailForNotAlwaysIncreasing(String line) {
        var report = Report.of(line);
        assertThat(report).returns(false, Report::isIncreasing);
    }

    @ParameterizedTest
    @CsvSource({"5 4 3 2 2",
        "9 7 6 3 1",
        "9 8 5 2 1"})
    void shouldIdentifyAlwaysDecreasing(String line) {
        var report = Report.of(line);
        assertThat(report).returns(true, Report::isDecreasing);
    }

    @ParameterizedTest
    @CsvSource({"5 1 3 2 2",
        "9 7 6 1 3",
        "9 7 8 2 1"})
    void shouldFailForNotAlwaysDecreasing(String line) {
        var report = Report.of(line);
        assertThat(report).returns(false, Report::isDecreasing);
    }

    @ParameterizedTest
    @CsvSource({"5 4 3 2 1",
        "1 3 5 7 9",
        "1 4 5 6 9"})
    void shouldIdentifyWithinRange(String line) {
        var report = Report.of(line);
        assertThat(report).returns(true, Report::withinRange);
    }

    @ParameterizedTest
    @CsvSource({"9 4 3 2 1",
        "1 5 6 7 8",
        "9 8 7 2 1"})
    void shouldFailForNotInRange(String line) {
        var report = Report.of(line);
        assertThat(report).returns(false, Report::withinRange);
    }

    @ParameterizedTest
    @CsvSource({"7 6 4 2 1",
        "1 3 6 7 9"})
    void shouldIdentifySafe(String line) {
        var report = Report.of(line);
        assertThat(report).returns(true, Report::isSafe);
    }

    @ParameterizedTest
    @CsvSource({"1 2 7 8 9",
        "9 7 6 2 1",
        "1 3 2 4 5",
        "8 6 4 4 1"})
    void shouldFailForNotUnsafe(String line) {
        var report = Report.of(line);
        assertThat(report).returns(false, Report::isSafe);
    }

    @Test
    void howManyReportsAreSafe() {
        try (var lines = Utils.inputLines("/advent/of/code/day2/inputPart1.txt")) {
            var safeReports =  lines.map(Report::of)
                    .filter(Report::isSafe)
                    .count();
            assertThat(safeReports).isEqualTo(526);
        }
    }

}
