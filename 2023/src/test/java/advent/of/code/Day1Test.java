package advent.of.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest ;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import advent.of.code.Day1.Digits;

class Day1Test {

    @ParameterizedTest(name="first char={0}, last char={1}, should have value {2}")
    @CsvSource({
            "1,2,12",
            "3,8,38",
            "1,5,15",
            "7,7,77"
    })
    void shouldGetValueFromDigits(char first, char last, int expected) {
        var actual = new Day1.Digits(first, last);
        assertThat(actual).extracting(Day1.Digits::value).isEqualTo(expected);
    }

    @ParameterizedTest(name="expect char={1}, from string={0}")
    @CsvSource(value={
            "'','\0'",
            "two1nine,2",
            "wo1nine,'\0'",
            "o1nine,'\0'",
            "1nine,1",
            "eightwothree,8",
            "abcone2threexyz,'\0'",
            "one2threexyz,1",
            "2threexyz,2",
            "threexyz,3",
            "xtwone3four,'\0'",
            "4nineeightseven2,4",
            "zoneight234,'\0'",
            "zeroeight234,0",
            "7pqrstsixteen,7",
            "sixteen,6"
    },nullValues = "null",emptyValue = "")
    void shouldGetCharFromStringDigits(String line, char expected) {
        var actual = Day1.numberAtStart(line);
        assertThat(actual).isEqualTo(expected);
    }
    
    
    @ParameterizedTest(name = "line = {0} should return {1}")
    @MethodSource("knownCalibrations")
    void shouldGetCalibrationDigits(String line, Digits expected) {
        var actual = Day1.calibrationDigits(line);
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> knownCalibrations() {
        return Stream.of(
                arguments("two1nine", new Digits('2', '9')),
                arguments("eightwothree", new Digits('8', '3')),
                arguments("abcone2threexyz", new Digits('1', '3')),
                arguments("xtwone3four", new Digits('2', '4')),
                arguments("4nineeightseven2", new Digits('4', '2')),
                arguments("zoneight234", new Digits('1', '4')),
                arguments("7pqrstsixteen", new Digits('7', '6'))
            );
    }

    @Test
    void totalPart1ShouldBe() throws URISyntaxException, IOException {
        var input = Path.of(Day1Test.class.getResource("/Day1/input1.txt").toURI());
        assertThat(Day1.processFile(input)).isEqualTo(142);
    }
    
    @Test
    void totalPart2ShouldBe() throws URISyntaxException, IOException {
        var input = Path.of(Day1Test.class.getResource("/Day1/input2.txt").toURI());
        assertThat(Day1.processFile(input)).isEqualTo(281);
    }
}
