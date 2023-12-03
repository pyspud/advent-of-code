package advent.of.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import advent.of.code.Day3.Coord;
import advent.of.code.Day3.SchematicNumber;

class Day3Test {
    // The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one.
    // If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.

    // The engine schematic (your puzzle input) consists of a visual representation of the engine.
    // There are lots of numbers and symbols you don't really understand,
    // but apparently any number adjacent to a symbol, even diagonally,
    // is a "part number" and should be included in your sum.
    // (Periods (.) do not count as a symbol.)
    @ParameterizedTest
    @ValueSource(chars={'$','*','#','+'})
    void shouldIdentifySymbols(char c) {
        assertThat(Day3.isSymbol(c)).isTrue();
    }
    @ParameterizedTest
    @ValueSource(chars={'.','1','2','3','4','5','6','7','8','9','0'})
    void shouldIgnorePeriodsAndDigits(char c) {
        assertThat(Day3.isSymbol(c)).isFalse();
    }
    
    @ParameterizedTest
    @MethodSource("exampleLines")
    void should(String schematicLine, List<SchematicNumber> expected) {
        var actual = Day3.numbersInLine(0,schematicLine);
        assertThat(actual).isEqualTo(expected);

    }

    static Stream<Arguments> exampleLines() {
        return Stream.of(
                arguments("", List.of()),
                arguments("...", List.of()),
                arguments(".1.", List.of(new SchematicNumber(new Coord(0, 1), 1, 1))),
                arguments("111", List.of(new SchematicNumber(new Coord(0, 0), 3, 111))),
                arguments("......#...", List.of()),
                arguments("617*......", List.of(new SchematicNumber(new Coord(0, 0), 3, 617))),
                arguments(".....+.58.", List.of(new SchematicNumber(new Coord(0, 7), 2, 58))),
                arguments("..35..633.", List.of(new SchematicNumber(new Coord(0, 2), 2, 35),
                        new SchematicNumber(new Coord(0, 6), 3, 633))));
    }
    
    @Test
    void singleDigitShouldHaveEightNeighbours() {
        var digit = new SchematicNumber(new Coord(0, 0), 1, 1);
        var expected = Set.of(
            new Coord(-1, -1),
            new Coord(-1, 0),
            new Coord(-1, 1),
            new Coord(0, -1),
            new Coord(0, 1),
            new Coord(1, -1),
            new Coord(1, 0),
            new Coord(1, 1)
        );

        assertThat(digit.neighbourCoords()).hasSize(8).isEqualTo(expected);
    }
    @Test
    void multiDigitShouldHaveManyNeighbours() {
        var digit = new SchematicNumber(new Coord(100, 10), 2, 1);
        var expected = Set.of(
            new Coord(99, 9),
            new Coord(99, 10),
            new Coord(99, 11),
            new Coord(99, 12),
            new Coord(100, 9),
            new Coord(100, 12),
            new Coord(101, 9),
            new Coord(101, 10),
            new Coord(101, 11),
            new Coord(101, 12)
        );

        assertThat(digit.neighbourCoords()).hasSize(10).isEqualTo(expected);
    }

    static final List<String> SCHEMATIC_ENGINE = List.of(
            ".....",
            ".....",
            "..*..",
            ".....",
            ".....");

    @ParameterizedTest
    @MethodSource("exampleSchematics")
    void shouldFindPartNumbers(SchematicNumber number, Boolean expected) {
        assertThat(Day3.isPartNumber(SCHEMATIC_ENGINE,number)).isEqualTo(expected);

    }

    static Stream<Arguments> exampleSchematics() {
        return Stream.of(
                // Line 0 - 5 digits
                arguments(new SchematicNumber(new Coord(0,0),5,11111),false),
                // Line 1 - 1 digit
                arguments(new SchematicNumber(new Coord(1,0),1,1),false),
                arguments(new SchematicNumber(new Coord(1,1),1,1),true),
                arguments(new SchematicNumber(new Coord(1,2),1,1),true),
                arguments(new SchematicNumber(new Coord(1,3),1,1),true),
                arguments(new SchematicNumber(new Coord(1,4),1,1),false),
                // Line 1 - 2 digits
                arguments(new SchematicNumber(new Coord(1,0),2,11),true),
                arguments(new SchematicNumber(new Coord(1,3),2,11),true),
                // Line 2 - 1 digit
                arguments(new SchematicNumber(new Coord(2,0),1,1),false),
                arguments(new SchematicNumber(new Coord(2,1),1,1),true),
                arguments(new SchematicNumber(new Coord(2,3),1,1),true),
                arguments(new SchematicNumber(new Coord(2,4),1,1),false),
                // Line 2 - 2 digits
                arguments(new SchematicNumber(new Coord(2,0),2,11),true),
                arguments(new SchematicNumber(new Coord(2,3),2,11),true),
                // Line 3 - 1 digit
                arguments(new SchematicNumber(new Coord(3,0),1,1),false),
                arguments(new SchematicNumber(new Coord(3,1),1,1),true),
                arguments(new SchematicNumber(new Coord(3,2),1,1),true),
                arguments(new SchematicNumber(new Coord(3,3),1,1),true),
                arguments(new SchematicNumber(new Coord(3,4),1,1),false),
                // Line 3 - 2 digits
                arguments(new SchematicNumber(new Coord(3,0),2,11),true),
                arguments(new SchematicNumber(new Coord(3,3),2,11),true),
                // Line 4 - 5 digits
                arguments(new SchematicNumber(new Coord(4,0),5,1111),false)
            );
    }

    @Test
    void shouldSumSchematicNumbers() {
        var numbers = List.of(new SchematicNumber(new Coord(0,2), 2, 35), new SchematicNumber(new Coord(35,6), 3, 633));
        assertThat(Day3.sumSchematicNumbers(numbers)).isEqualTo(668);
    }
    // Here is an example engine schematic:

    // 467..114..
    // ...*......
    // ..35..633.
    // ......#...
    // 617*......
    // .....+.58.
    // ..592.....
    // ......755.
    // ...$.*....
    // .664.598..

    // In this schematic, two numbers are not part numbers because they are not adjacent to a symbol: 
    // 114 (top right) and 58 (middle right). 
    // Every other number is adjacent to a symbol and so is a part number; their sum is 4361.

    @Test
    void inputShouldMatchPart1() throws URISyntaxException, IOException {
        var inputFile = Path.of(Day3Test.class.getResource("/Day3/input1.txt").toURI());
        var sum = Day3.sumOfPartNumbersInFile(inputFile);
        assertThat(sum).isEqualTo(4361);
    }
    // Of course, the actual engine schematic is much larger. What is the sum of all of the part numbers in the engine schematic?
    
    
    // Part 2
    // The missing part wasn't the only issue - one of the gears in the engine is wrong. 
    // A gear is any * symbol that is adjacent to exactly two part numbers. 
    // Its gear ratio is the result of multiplying those two numbers together.
    
    // This time, you need to find the gear ratio of every gear and add them all up so that the engineer can figure out which gear needs to be replaced.
    @Test
    void shouldGetCoordsOfPossibleGears() {
        var gears = Day3.possibleGearsInEngine(SCHEMATIC_ENGINE);
        assertThat(gears).contains(new Coord(2, 2));
    }
    @Test
    void shouldGetCoordsOfGearsInInput() throws URISyntaxException, IOException {
        var inputFile = Path.of(Day3Test.class.getResource("/Day3/input1.txt").toURI());
        var engine = Files.readAllLines(inputFile);
        var actualGears = Day3.possibleGearsInEngine(engine);
        assertThat(actualGears).containsExactly(new Coord(1, 3),new Coord(4, 3),new Coord(8, 5));
    }
    // Consider the same engine schematic again:
    
    // 467..114..
    // ...*......
    // ..35..633.
    // ......#...
    // 617*......
    // .....+.58.
    // ..592.....
    // ......755.
    // ...$.*....
    // .664.598..

    // In this schematic, there are two gears. 
    // The first is in the top left; it has part numbers 467 and 35, so its gear ratio is 16345. 
    // The second gear is in the lower right; its gear ratio is 451490. 
    // (The * adjacent to 617 is not a gear because it is only adjacent to one part number.) 
    // Adding up all of the gear ratios produces 467835.

    @Test
    void shouldFindPossibleRatioNumber() {
        var expected = new Coord(1, 1);
        var gears = List.of(expected);
        var number = new SchematicNumber(new Coord(0, 0), 1, 1);

        assertThat(number.possibleRatio(gears)).isEqualTo(expected);
    }
    @Test
    void shouldFindPossibleRatioNumbers() throws IOException, URISyntaxException {
        // 467, 35; 617; 755,598
        var inputFile = Path.of(Day3Test.class.getResource("/Day3/input1.txt").toURI());
        var engine = Files.readAllLines(inputFile);

        var allNumbers = Day3.allNumbersIn(engine);
        var possibleGears = Day3.possibleGearsInEngine(engine);

        var possibleRatios = Day3.possibleRatios(allNumbers,possibleGears);
        assertThat(possibleRatios).hasSize(5);
    }
    // @Test
    // void shouldFindActualGears() throws IOException, URISyntaxException {
    //     var inputFile = Path.of(Day3Test.class.getResource("/Day3/input1.txt").toURI());
    //     var engine = Files.readAllLines(inputFile);
    //     var actualGears = Day3.findGears(engine);
    //     assertThat(actualGears).containsExactly(new Coord(1, 3),new Coord(8, 5));
    // }
    
    @Test
    void inputShouldMatchPart2() throws URISyntaxException, IOException {
        var inputFile = Path.of(Day3Test.class.getResource("/Day3/input1.txt").toURI());
        var sum = Day3.sumOfGearRatiosInFile(inputFile);
        assertThat(sum).isEqualTo(467835);
    }
    
    // What is the sum of all of the gear ratios in your engine schematic?
}
