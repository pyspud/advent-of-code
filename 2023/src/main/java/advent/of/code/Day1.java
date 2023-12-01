package advent.of.code;
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
        var inputFile = Path.of("input.txt");
        System.out.println("Using inputFile = "+inputFile.toAbsolutePath());
        System.out.println(processFile(inputFile)); 
    }

    static int processFile(Path inputFile) throws IOException {
        try (var lines = Files.lines(inputFile)) {
            return lines.map(Day1::calibrationDigits)
                    // .peek(System.out::println)
                    .mapToInt(Digits::value)
                    .sum();
        }
    }


    record Digits(char first, char last) {
        Integer value() {
            char[] concat =  { first, last };
            return Integer.valueOf(String.valueOf(concat));
        }
    }

    static Digits calibrationDigits(String line) {
        char first = 0;
        char last = 0;
        for(var i=0; i<line.length();i++) {
            var subLine = line.substring(i);
            var charElement=numberAtStart(subLine);
            if (Character.isDigit(charElement)) {
                if (first == 0) {
                    first = charElement;
                }
                last = charElement;
            }
        }
        return new Digits(first, last);
    }
    
    // two1nine
    // eightwothree
    // abcone2threexyz
    // xtwone3four
    // 4nineeightseven2
    // zoneight234
    // 7pqrstsixteen

    static char numberAtStart(String line) {
        if(line.isEmpty()){
            return 0;
        }
        var firstChar = line.charAt(0);
        if (Character.isDigit(firstChar)) {
            return firstChar;
        } else if (line.startsWith("zero")) {
            return '0';
        } else if (line.startsWith("one")) {
            return '1';
        } else if (line.startsWith("two")) {
            return '2';
        } else if (line.startsWith("three")) {
            return '3';
        } else if (line.startsWith("four")) {
            return '4';
        } else if (line.startsWith("five")) {
            return '5';
        } else if (line.startsWith("six")) {
            return '6';
        } else if (line.startsWith("seven")) {
            return '7';
        } else if (line.startsWith("eight")) {
            return '8';
        } else if (line.startsWith("nine")) {
            return '9';
        }
        return 0;
    }
}