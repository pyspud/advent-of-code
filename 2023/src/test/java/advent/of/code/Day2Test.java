package advent.of.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import advent.of.code.Day2.Cube;
import advent.of.code.Day2.Cube.Blue;
import advent.of.code.Day2.Cube.Green;
import advent.of.code.Day2.Cube.Red;
import advent.of.code.Day2.Game;

class Day2Test {
    // Game cubes:
    // 8 green
    // 6 blue
    // 20 red
    // 5 blue
    // 4 red
    // 13 green
    // 5 green
    // 1 red
    static Stream<Arguments> knownCubes() {
        return Stream.of(
                arguments("8 green", new Green(8)),
                arguments("6 blue", new Blue(6)),
                arguments("20 red", new Red(20)),
                arguments("5 blue", new Blue(5)),
                arguments("4 red", new Red(4)),
                arguments("13 green", new Green(13)),
                arguments("5 green", new Green(5)),
                arguments("1 red", new Red(1)));
    }

    @ParameterizedTest
    @MethodSource("knownCubes")
    void shouldIdentifyCubes(String input, Cube expected) {
        var actual = Cube.of(input);
        assertThat(actual).isEqualTo(expected);
    }
    // Each game is listed with its ID number (like the 11 in Game 11: ...)
    // followed by a semicolon-separated list of subsets of cubes that were revealed from the bag (like 3 red, 5 green, 4 blue).
    // 
    // For example, the record of a few games might look like this:
    // 
    // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    // Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
    // Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
    // Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
    // Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    @Test
    void shouldExtractSingleGameSubset() {
        var expected = Set.of(new Green(99));
        var actual = Day2.gameSubset("99 green");
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void shouldExtractGameSubset() {
        var expected = Set.of(new Green(8), new Red(20), new Blue(6));
        var actual = Day2.gameSubset("8 green,6 blue, 20 red");
        assertThat(actual).isEqualTo(expected);
    }
    // In game 1, three sets of cubes are revealed from the bag (and then put back again).
    // The first set is 3 blue cubes and 4 red cubes; ...
    // the second set is 1 red cube, 2 green cubes, and 6 blue cubes; ...
    // the third set is only 2 green cubes.

    @Test
    void shouldExtractListGameSingleSet() {
        var expected = List.of(
                Set.of(new Green(2)));
        var actual = Day2.gameList("2 green");
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void shouldExtractListGameSets() {
        var expected = List.of(
                Set.of(new Red(4), new Blue(3)),
                Set.of(new Green(2), new Red(1), new Blue(6)),
                Set.of(new Green(2)));
        var actual = Day2.gameList("3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    void shouldGetGameFromLine() {
        var expected = new Game(5, List.of(
                Set.of(new Green(3), new Red(6), new Blue(1)),
                Set.of(new Green(2), new Red(1), new Blue(2))));
        var actual = Game.of("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldProcessFile() throws URISyntaxException, IOException {
        var input = Path.of(Day1Test.class.getResource("/Day2/test.txt").toURI());
        assertThat(Day2.processFile(input)).containsAnyOf(
                new Game(9, List.of( Set.of(new Green(1)))),
                new Game(10, List.of( Set.of(new Red(2),new Blue(3)) )),
                new Game(222, List.of( Set.of(new Green(4), new Red(5), new Blue(6)) ))
                );
    }
    // The Elf would first like to know which games would have been possible if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?
    // 
    // In the example above, games 1, 2, and 5 would have been possible if the bag had been loaded with that configuration.
    // However, game 3 would have been impossible because at one point the Elf showed you 20 red cubes at once; similarly,
    // game 4 would also have been impossible because the Elf showed you 15 blue cubes at once.
    // If you add up the IDs of the games that would have been possible, you get 8.
    // 
    // Determine which games would have been possible if the bag had been loaded with only 12 red cubes, 13 green cubes, and 14 blue cubes.
    // What is the sum of the IDs of those games?
    @Test
    void validIdsPart1ShouldBe() throws URISyntaxException, IOException {
        var expected = List.of(1, 2, 5);
        var input = Path.of(Day1Test.class.getResource("/Day2/input1.txt").toURI());
        var actual = Day2.validGameIds(Day2.processFile(input));
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void totalPart1ShouldBe() throws URISyntaxException, IOException {
        var input = Path.of(Day1Test.class.getResource("/Day2/input1.txt").toURI());
        var actual = Day2.sumGameIds(Day2.processFile(input));
        assertThat(actual).isEqualTo(8);
    }
}
