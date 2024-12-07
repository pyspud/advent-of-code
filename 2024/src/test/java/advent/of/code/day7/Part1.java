package advent.of.code.day7;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import advent.of.code.Utils;

class Part1 {
    static final String EXAMPLE_INPUT = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """;

    static final List<TwoOperationCalibration> EXAMPLE_CALIBRATIONS = List.of(
        new TwoOperationCalibration(190, List.of( 10l, 19l)),
        new TwoOperationCalibration(3267, List.of( 81l, 40l, 27l)),
        new TwoOperationCalibration(83, List.of( 17l, 5l)),
        new TwoOperationCalibration(156, List.of( 15l, 6l)),
        new TwoOperationCalibration(7290, List.of( 6l, 8l, 6l, 15l)),
        new TwoOperationCalibration(161011, List.of( 16l, 10l, 13l)),
        new TwoOperationCalibration(192, List.of( 17l, 8l, 14l)),
        new TwoOperationCalibration(21037, List.of( 9l, 7l, 18l, 13l)),
        new TwoOperationCalibration(292, List.of( 11l, 6l, 16l, 20l))
    );

    @Test
    void anExampleShouldBePhrased()
    {
        var expected = new TwoOperationCalibration(123, List.of(4l, 7l));
        var result = TwoOperationCalibration.of("123: 4 7");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void exampleInputShouldBePhrased()
    {
        var result = EXAMPLE_INPUT.lines().map(TwoOperationCalibration::of).toList();
        assertThat(result).isEqualTo(EXAMPLE_CALIBRATIONS);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "190: 10 19",
            "3267: 81 40 27",
            "292: 11 6 16 20",
            "5294894: 757 37 3 6 7 9 807 8 1"
    })
    void shouldFindValuesAreSolvable(String line) {
        var value = TwoOperationCalibration.of(line);
        assertThat(value.isSolvable()).isTrue();
    }


    @Test
    void shouldNotFindOperationsPartialNumberList() {
        var value = TwoOperationCalibration.of("284530: 728 41 74 5 3");
        assertThat(value.isSolvable()).isFalse();
    }

    @Test
    void exampleTotalCalibrationResult()
    {
        var result = EXAMPLE_INPUT.lines().map(TwoOperationCalibration::of)
                .filter(TwoOperationCalibration::isSolvable)
                .mapToLong(TwoOperationCalibration::expected)
                .sum();
        assertThat(result).isEqualTo(3749);
    }

    @Test
    void whatIsTheirTotalCalibrationResult()
    {
        var input = Utils.inputLines("/advent/of/code/day7/inputPart1.txt");
        var result = input.map(TwoOperationCalibration::of)
                .filter(TwoOperationCalibration::isSolvable)
                .mapToLong(TwoOperationCalibration::expected)
                .sum();

        assertThat(result).isEqualTo(7710205485870L);
    }
}
