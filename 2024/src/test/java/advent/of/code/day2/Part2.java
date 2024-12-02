package advent.of.code.day2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import advent.of.code.Utils;

class Part2 {
    @ParameterizedTest
    @CsvSource({"7 6 4 2 1",
        "1 3 2 4 5",
        "8 6 4 4 1",
        "1 3 6 7 9"})
    void shouldIdentifySafeWithProblemDampener(String line) {
        var report = Report.of(line);
        assertThat(report).returns(true, Report::safeWithProblemDampener);
    }

    @ParameterizedTest
    @CsvSource({"1 2 7 8 9",
        "9 7 6 2 1",})
    void shouldFailForNotUnsafeWithProblemDampener(String line) {
        var report = Report.of(line);
        assertThat(report).returns(false, Report::safeWithProblemDampener);
    }

    @Test
    void shouldCopyArrayWithoutElement() {
        long[] expected = { 1, 2, 4, 5 };
        var actual = Report.of("1 2 3 4 5").copyArrayWithout(2);
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void howManyReportsAreNowSafeWithProblemDampener() {
        try (var lines = Utils.inputLines("/advent/of/code/day2/inputPart1.txt")) {
            var safeReports =  lines.map(Report::of)
                    .filter(Report::safeWithProblemDampener)
                    .count();
            assertThat(safeReports).isEqualTo(566);
        }
    }
}
