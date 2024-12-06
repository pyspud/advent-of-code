package advent.of.code.day6;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;

import advent.of.code.Utils;
import advent.of.code.day6.PuzzleMap.Coord;

class Part1 {
    static final String EXAMPLE_INPUT = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """;
    static final Coord EXAMPLE_ROBOT_START = new Coord(6, 4);
    static final Set<Coord> EXAMPLE_BOXES = Set.of(
            new Coord(0, 4),
            new Coord(1, 9),
            new Coord(3, 2),
            new Coord(4, 7),
            new Coord(6, 1),
            new Coord(7, 8),
            new Coord(8, 0),
            new Coord(9, 6)
    );

    @Test
    void shouldFindRobotStart() {
        var map = new PuzzleMap(EXAMPLE_INPUT.lines().toList());
        assertThat(map.robot).isEqualTo(EXAMPLE_ROBOT_START);
    }

    @Test
    void shouldFindBoxes() {
        var map = new PuzzleMap(EXAMPLE_INPUT.lines().toList());
        assertThat(map.boxes).isEqualTo(EXAMPLE_BOXES);
    }

    @Test
    void guardShouldVisit41PositionsInExample() {
        var puzzle = new PuzzleMap(EXAMPLE_INPUT.lines().toList());
        var result = puzzle.guardVisit();
        assertThat(result).hasSize(41);
    }

    @Test
    void howManyDistinctPositionsWillTheGuardVisitBeforeLeavingTheMappedArea()
    {
        var input = Utils.inputLines("/advent/of/code/day6/inputPart1.txt");
        var puzzle = new PuzzleMap(input.toList());
        var result = puzzle.guardVisit();
        assertThat(result).hasSize(4665);
    }
}
