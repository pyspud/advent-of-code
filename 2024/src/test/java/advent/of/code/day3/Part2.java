package advent.of.code.day3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import advent.of.code.Utils;

class Part2 {

    static final String EXAMPLE_INPUT = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
    static final List<String> EXAMPLE_INSTRUCTIONS = List.of("mul(2,4)", "mul(8,5)");

    @Test
    void shouldFindEnabledInstructions() {
        var computer = new ConditionalMemory();
        var actual = computer.process(EXAMPLE_INPUT).toList();

        assertThat(actual).isEqualTo(EXAMPLE_INSTRUCTIONS);
    }
    @Test
    void shouldFindResultOfEnabledInstructions() {
        var computer = new ConditionalMemory();
        var actual = computer.process(EXAMPLE_INPUT).map(Multiple::of).mapToLong(Multiple::result).sum();

        assertThat(actual).isEqualTo(48);
    }

    // what do you get if you add up all of the results of just the enabled multiplications
    @Test
    void whatDoYouGetIfYouAddUpAllOfTheResultsOfJustTheEnabledMultiplications(){
        try (var lines = Utils.inputLines("/advent/of/code/day3/inputPart1.txt")) {
            var memory = new ConditionalMemory(lines);
            var result = memory.getInstructions()
                    .map(Multiple::of)
                    .mapToLong(Multiple::result)
                    .sum();

            assertThat(result).isEqualTo(108830766L);
        }
    }
}
