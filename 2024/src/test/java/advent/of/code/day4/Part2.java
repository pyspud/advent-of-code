package advent.of.code.day4;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import advent.of.code.Utils;

class Part2 {
    // spellchecker: disable
    static final String X_MAS_9 = """
            .M.S......
            ..A..MSMS.
            .M.S.MAA..
            ..A.ASMSM.
            .M.S.M....
            ..........
            S.S.S.S.S.
            .A.A.A.A..
            M.M.M.M.M.
            ..........
            """;

    @Test
    void shouldFindXMas9Times() {
        XMasSearch puzzle = new XMasSearch(X_MAS_9.lines().toList());
        assertThat(puzzle.countX()).isEqualTo(9);
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
    void shouldFindXMas9TimesInExample() {
        XMasSearch puzzle = new XMasSearch(EXAMPLE_INPUT.lines().toList());
        assertThat(puzzle.countX()).isEqualTo(9);
    }

    @Test
    void howManyTimesDoesXMasAppear() {
        var lines = Utils.inputLines("/advent/of/code/day4/inputPart1.txt").toList();
        var puzzle = new XMasSearch(lines);
        assertThat(puzzle.countX()).isEqualTo(1967L);
    }
}
