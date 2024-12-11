package advent.of.code.day11;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import advent.of.code.Utils;

class Pluto {

    @ParameterizedTest
    @CsvSource(value = {
            "0, 1",
            "2100, 21 0",
            "2, 4048",
            "0 1 10 99 999, 1 2024 1 0 9 9 2021976"
    })
    void shouldApplyRulesToAStone(String start, String expected) {
        var pebbles = new Pebbles(start);
        pebbles.blink();
        assertThat(pebbles.stones()).isEqualTo(expected);
    }

    @Test
    void shouldApplyRulesMultipleTimes() {
        var pebbles = new Pebbles("125 17");
        pebbles.blinking(6);
        assertThat(pebbles.stones()).isEqualTo("2097446912 14168 4048 2 0 2 4 40 48 2024 40 48 80 96 2 8 6 7 6 0 3 2");
        assertThat(pebbles.stones().split(" ")).hasSize(22);
    }

    @Test
    void howManyStonesWillYouHaveAfterBlinking25Times() {
        var input = Utils.inputLines("/advent/of/code/day11/inputPart1.txt").toList();
        var pebbles = new Pebbles(input.get(0));
        pebbles.blinking(25);
        var stones = pebbles.stones().split(" ");
        assertThat(stones).hasSize(172484);
    }

    @Disabled("OOM")
    @Test
    void howManyStonesWillYouHaveAfterBlinking75Times() {
        var input = Utils.inputLines("/advent/of/code/day11/inputPart1.txt").toList();
        var pebbles = new Pebbles(input.get(0));
        pebbles.blinking(75);
        var stones = pebbles.stones().split(" ");
        assertThat(stones).hasSize(172484);
    }
}
