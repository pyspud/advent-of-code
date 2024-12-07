package advent.of.code.day7;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import advent.of.code.Utils;
import advent.of.code.day7.Calibration.Operation;

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

    static final List<Calibration> EXAMPLE_CALIBRATIONS = List.of(
        new Calibration(190, List.of( 10l, 19l)),
        new Calibration(3267, List.of( 81l, 40l, 27l)),
        new Calibration(83, List.of( 17l, 5l)),
        new Calibration(156, List.of( 15l, 6l)),
        new Calibration(7290, List.of( 6l, 8l, 6l, 15l)),
        new Calibration(161011, List.of( 16l, 10l, 13l)),
        new Calibration(192, List.of( 17l, 8l, 14l)),
        new Calibration(21037, List.of( 9l, 7l, 18l, 13l)),
        new Calibration(292, List.of( 11l, 6l, 16l, 20l))
    );

    @Test
    void anExampleShouldBePhrased()
    {
        var expected = new Calibration(123, List.of(4l, 7l));
        var result = Calibration.of("123: 4 7");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void exampleInputShouldBePhrased()
    {
        var result = EXAMPLE_INPUT.lines().map(Calibration::of).toList();
        assertThat(result).isEqualTo(EXAMPLE_CALIBRATIONS);
    }

    @Test
    void shouldFindOperationOfTwoNumbers() {
        var expected = List.of(Calibration.Operation.MULTIPLE);
        var value = Calibration.of("190: 10 19");
        assertThat(value.findWithTwoOperations()).isEqualTo(expected);
    }

    @Test
    void shouldFindOperationOfThreeNumbers() {
        var value = Calibration.of("3267: 81 40 27");
        assertThat(value.findWithTwoOperations()).containsExactlyInAnyOrder(Calibration.Operation.MULTIPLE,
                Calibration.Operation.PLUS);
    }

    @Test
    void shouldFindOperationOfFourNumbers() {
        var expected = List.of(Calibration.Operation.PLUS,Calibration.Operation.MULTIPLE,Calibration.Operation.PLUS);
        var value = Calibration.of("292: 11 6 16 20");
        assertThat(value.findWithTwoOperations()).isEqualTo(expected);
    }

    @Test
    void shouldNotFindOperationsPartialNumberList() {
        var value = Calibration.of("284530: 728 41 74 5 3");
        List<Operation> ops = value.findWithTwoOperations();
        assertThat(ops).isEmpty();
    }
    @Test
    void shouldFindOperationsWithTrailingOne() {
        var value = Calibration.of("5294894: 757 37 3 6 7 9 807 8 1");
        List<Operation> ops = value.findWithTwoOperations();
        assertThat(ops).hasSize(value.values().size() - 1);
    }

    @Test
    void exampleTotalCalibrationResult()
    {
        var result = EXAMPLE_INPUT.lines().map(Calibration::of)
                .filter(Calibration::isValidTwoOps)
                .mapToLong(Calibration::expected)
                .sum();
        assertThat(result).isEqualTo(3749);
    }

    @Test
    void whatIsTheirTotalCalibrationResult()
    {
        var input = Utils.inputLines("/advent/of/code/day7/inputPart1.txt");
        var result = input.map(Calibration::of)
                .filter(Calibration::isValidTwoOps)
                .mapToLong(Calibration::expected)
                .sum();

        assertThat(result).isEqualTo(7710205485870L);
    }
}
