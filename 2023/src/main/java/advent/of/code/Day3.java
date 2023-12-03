package advent.of.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 {
    // The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one.
    // If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.
    record Coord(int line, int start) {}
    record SchematicNumber(Coord position, int length, int value) {
        Set<Coord> neighbourCoords() {
            Set<Coord> neighbours = HashSet.newHashSet(6+2*length);
            // Coords beside
            neighbours.add(new Coord(position.line(), position.start()-1));
            neighbours.add(new Coord(position.line(), position.start()+length));
            for (int i = -1; i <= length; i++) {
                // Coords above
                neighbours.add(new Coord(position.line()-1, position.start()+i));
                // Coords below
                neighbours.add(new Coord(position.line()+1, position.start()+i));
            }
            return neighbours;
        }
    }
    // The engine schematic (your puzzle input) consists of a visual representation of the engine.
    // There are lots of numbers and symbols you don't really understand,
    // but apparently any number adjacent to a symbol, even diagonally,
    // is a "part number" and should be included in your sum.
    // (Periods (.) do not count as a symbol.)
    static boolean isSymbol(char c) {
        return !(c == '.' || Character.isLetterOrDigit(c));
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
    static List<SchematicNumber> numbersInLine(int lineNumber, String lineContent) {
        List<SchematicNumber> found = new ArrayList<>();
        StringBuilder numberString = new StringBuilder();
        Coord startPos = null;
        
        // Scan the line for numbers
        for (int i = 0; i < lineContent.length(); i++) {
            var c = lineContent.charAt(i);
            if (startPos ==null && Character.isDigit(c)) {
                // Found start of new number
                startPos = new Coord(lineNumber,i);
                numberString.append(c);
            } else if (startPos != null && Character.isDigit(c)) {
                // In the middle of a number
                numberString.append(c);
            } else if (startPos !=null) {
                // Not ad Digit so number has finished
                found.add(new SchematicNumber(startPos,i-startPos.start(), Integer.parseInt(numberString.toString())));
                startPos = null;
                numberString = new StringBuilder();
            }
        }
        if (startPos != null) {
            // Finished line but still have digit found
            found.add(new SchematicNumber(startPos, lineContent.length()-startPos.start(), Integer.parseInt(numberString.toString())));
        }
        return found;
    }
    // Of course, the actual engine schematic is much larger. What is the sum of all of the part numbers in the engine schematic?

    static boolean isPartNumber(List<String> engineSchematic, SchematicNumber number) {
        var neighbours = number.neighbourCoords();
        for (Coord coord : neighbours) {
            if (coord.line() > 0 && coord.line() < engineSchematic.size()) {
                var line = engineSchematic.get(coord.line());
                if (coord.start() > 0 && coord.start() < line.length()) {
                    var charAt = line.charAt(coord.start());
                    if (isSymbol(charAt)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static List<SchematicNumber> allNumbersIn(List<String> engine) {
        List<SchematicNumber> numbers = new ArrayList<>();
        for (var l = 0; l < engine.size(); l++) {
            numbers.addAll(numbersInLine(l, engine.get(l)));
        }
        return numbers;
    }
    
    static int sumSchematicNumbers(List<SchematicNumber> numbers) {
        return numbers.stream()
                .mapToInt(SchematicNumber::value)
                .sum();
    }

    static int processFile(Path inputFile) throws IOException {
        var engine = Files.readAllLines(inputFile);
        var allNumbers = Day3.allNumbersIn(engine);
        var partNumbers = allNumbers.stream().filter(n -> Day3.isPartNumber(engine, n)).toList();
        return sumSchematicNumbers(partNumbers);
    }

    public static void main(String... args) throws IOException {
        var inputFile = Path.of("input.txt");
        System.out.println("Using inputFile = " + inputFile.toAbsolutePath());
        //Part 1
        var result1 = processFile(inputFile); 
        System.out.println("Part 1 = "+result1); 
        // //Part 2
        // var result2 = sumOfPowers(processFile(inputFile)); 
        // System.out.println("Part 2 = "+result2); 
    }
}
