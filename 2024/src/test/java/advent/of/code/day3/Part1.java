package advent.of.code.day3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import advent.of.code.Utils;

class Part1 {
    static final Supplier<Stream<Arguments>> VALID = () -> Stream.of(
        arguments("mul(44,46)", Long.valueOf(2024)),
        arguments("mul(123,4)", Long.valueOf(492)));

    @ParameterizedTest
    @FieldSource("VALID")
    void shouldParseInstruction(String instruction, Long value) {
        var actual = Multiply.of(instruction);
        assertThat(actual).returns(value, Multiply::result);
    }

    static final List<String> INVALID = List.of("mul(4*", "mul(6,9!", "?(12,34)", "mul ( 2 , 4 )");

    static final String EXAMPLE_INPUT = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
    static final List<String> EXAMPLE_INSTRUCTIONS = List.of("mul(2,4)","mul(5,5)","mul(11,8)","mul(8,5)");
    @Test
    void shouldFindInstructions() {
        var computer = new MemorySegment(EXAMPLE_INPUT);
        var actual = computer.getInstructions();

        assertThat(actual).isEqualTo(EXAMPLE_INSTRUCTIONS);
    }
    @Test
    void shouldFindResultOFInstructions() {
        var computer = new MemorySegment(EXAMPLE_INPUT);
        var actual = computer.getInstructions().map(Multiply::of).mapToLong(Multiply::result).sum();

        assertThat(actual).isEqualTo(161);
    }

    // What do you get if you add up all of the results of the multiplications?
    @Test
    void whatDoYouGetIfYouAddUpAllOfTheResultsOfTheMultiplications(){
        try (var lines = Utils.inputLines("/advent/of/code/day3/inputPart1.txt")) {
            var result = lines.map(MemorySegment::new)
                    .flatMap(MemorySegment::getInstructions)
                    .map(Multiply::of)
                    .mapToLong(Multiply::result)
                    .sum();

            assertThat(result).isEqualTo(165225049L);
        }
    }
}
