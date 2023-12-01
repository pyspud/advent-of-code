///usr/bin/env jbang "$0" "$@" ; exit $?

// The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration value that the Elves now need to recover.
// On each line, the calibration value can be found by combining the first digit and the last digit (in that order) to form a single two-digit number.

// For example:

// 1abc2
// pqr3stu8vwx
// a1b2c3d4e5f
// treb7uchet

// In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.
// Consider your entire calibration document. What is the sum of all of the calibration values?

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day1 {

    public static void main(String... args) throws IOException {
        var inputFile = Path.of("input1.txt");
        try (var lines = Files.lines(inputFile)) {
            var result = lines.map(Day1::calibrationDigits)
                    .mapToInt(Digits::value)
                    .sum();
            System.out.println(result);
        } 
    }


    record Digits(char first, char last) {
        Integer value() {
            char[] concat =  { first, last };
            return Integer.valueOf(String.valueOf(concat));
        }
    }

    static Digits calibrationDigits(String line) {
        char first =0;
        char last =0;
        for (var charElement : line.toCharArray()) {
            if (Character.isDigit(charElement)) {
                if (first == 0) {
                    first = charElement;
                }
                last = charElement;
            }
        }
        return new Digits(first, last);
    }
}