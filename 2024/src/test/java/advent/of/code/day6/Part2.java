package advent.of.code.day6;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;

import advent.of.code.Utils;
import advent.of.code.day6.PuzzleMap.Coord;

class Part2 {

    static final Set<Coord> LOOPING_OBSTRUCTIONS = Set.of(
        new Coord(6, 3),
        new Coord(7, 6),
        new Coord(7, 7),
        new Coord(8, 1),
        new Coord(8, 3),
        new Coord(9, 7)
    );

    @Test
    void guardShouldLoopWithExtraObstruction6PositionsInExample() {
        var puzzle = new PuzzleMap(Part1.EXAMPLE_INPUT.lines().toList());
        var result = puzzle.obstructionsCauseLoop();
        assertThat(result).hasSize(6).isEqualTo(LOOPING_OBSTRUCTIONS);
    }

    @Test
    void howManyDifferentPositionsCouldYouChooseForThisObstruction()
    {
        var input = Utils.inputLines("/advent/of/code/day6/inputPart1.txt");
        var puzzle = new PuzzleMap(input.filter(not(String::isBlank)).toList());
        var result = puzzle.obstructionsCauseLoop();
        assertThat(result).hasSize(1688);
    }
}
