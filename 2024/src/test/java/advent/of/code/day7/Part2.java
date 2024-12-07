package advent.of.code.day7;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import advent.of.code.Utils;
import advent.of.code.day7.Calibration.Operation;

class Part2 {
    @ParameterizedTest
    @ValueSource(strings = {
        "156: 15 6",
        "7290: 6 8 6 15",
        "192: 17 8 14",
    })
    void shouldValidateUsingThreeOperations(String line) {
        var value = Calibration.of(line);
        List<Operation> ops = value.findWithThreeOperations();
        assertThat(ops).hasSize(value.values().size() - 1);
    }

    @Test
    void exampleTotalCalibrationResult()
    {
        var result = Part1.EXAMPLE_INPUT.lines().map(Calibration::of)
                .filter(Calibration::isValidThreeOps)
                .mapToLong(Calibration::expected)
                .sum();
        assertThat(result).isEqualTo(11387);
    }

    @Test
    void whatIsTheirTotalCalibrationResult()
    {
        var input = Utils.inputLines("/advent/of/code/day7/inputPart1.txt");
        var result = input.map(Calibration::of)
                .filter(Calibration::isValidThreeOps)
                .mapToLong(Calibration::expected)
                .sum();

        assertThat(result).isEqualTo(20928985450275L);
    }
}
