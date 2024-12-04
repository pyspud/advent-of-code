package advent.of.code.day4;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import advent.of.code.Utils;

class Part1 {

    // spellchecker: disable
    static final String XMAS_4 = """
            ..X...
            .SAMX.
            .A..A.
            XMAS.S
            .X....
            """;

    @Test
    void shouldFindXmas4Times() {
        WordSearch puzzle = new WordSearch(XMAS_4.lines().toList());
        assertThat(puzzle.countXmas()).isEqualTo(4);
    }
    static final String XMAS_18 = """
            ....XXMAS.
            .SAMXMS...
            ...S..A...
            ..A.A.MS.X
            XMASAMX.MM
            X.....XA.A
            S.S.S.S.SS
            .A.A.A.A.A
            ..M.M.M.MM
            .X.X.XMASX
            """;

    @Test
    void shouldFindXmas18Times() {
        WordSearch puzzle = new WordSearch(XMAS_18.lines().toList());
        assertThat(puzzle.countXmas()).isEqualTo(18);
    }

    static final String EXAMPLE_INPUT = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
            """;
    // spellchecker: enable

    @Test
    void shouldFindXmas18TimesInExample() {
        WordSearch puzzle = new WordSearch(EXAMPLE_INPUT.lines().toList());
        assertThat(puzzle.countXmas()).isEqualTo(18);
    }

    @Test
    void howManyTimesDoesXmasAppear() {
        var lines = Utils.inputLines("/advent/of/code/day4/inputPart1.txt").toList();
        var puzzle = new WordSearch(lines);
        assertThat(puzzle.countXmas()).isEqualTo(2496L);
    }
}
