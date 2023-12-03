package advent.of.code;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 {
    // To get information, once a bag has been loaded with cubes, the Elf will reach into the bag,
    // grab a handful of random cubes, show them to you, and then put them back in the bag.
    // He'll do this a few times per game.
    // 
    // You play several games and record the information from each game (your puzzle input).
    // Each game is listed with its ID number (like the 11 in Game 11: ...)
    // followed by a semicolon-separated list of subsets of cubes that were revealed from the bag (like 3 red, 5 green, 4 blue).

    sealed interface Cube {
        int number();
        record Red(int number) implements Cube{}
        record Blue(int number) implements Cube{}
        record Green(int number) implements Cube {}
        
        static Cube of(String input) {
            var parts = input.strip().split(" ");
            var number = Integer.parseInt(parts[0]);
            if("red".equalsIgnoreCase(parts[1])) {
                return new Red(number);
            } else if("blue".equalsIgnoreCase(parts[1])) {
                return new Blue(number);
            } else if ("green".equalsIgnoreCase(parts[1])) {
                return new Green(number);
            }
            throw new IllegalArgumentException("Not a colour: "+parts[1]);
        }
    }
    // For example, the record of a few games might look like this:
    // 
    // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    // Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
    // Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
    // Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
    // Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    //
    // In game 1, three sets of cubes are revealed from the bag (and then put back again).
    // The first set is 3 blue cubes and 4 red cubes; ...
    // the second set is 1 red cube, 2 green cubes, and 6 blue cubes; ...
    // the third set is only 2 green cubes.
    static Set<Cube> gameSubset(String input) {
        return Stream.of(input.split(","))
                .map(String::strip)
                .map(Cube::of)
                .collect(Collectors.toSet());
    }

    static List<Set<Cube>> gameList(String input) {
        return Stream.of(input.split(";"))
                .map(String::strip)
                .map(Day2::gameSubset)
                .toList();
    }

    record Game(int id, List<Set<Cube>> games) {
        static Game of(String line) {
            var part = line.split(":");
            var num = part[0].substring("Game ".length());
            return new Game(Integer.parseInt(num), gameList(part[1]));
        }

        Set<Cube> minimumSet() {
            int maxRed = 0;
            int maxBlue = 0;
            int maxGreen = 0;
            for (var game : games) {
                for (var cube : game) {
                    if (cube instanceof Cube.Red(var n) && n > maxRed) {
                        maxRed = n;
                    }
                    if (cube instanceof Cube.Blue(var n) && n > maxBlue) {
                        maxBlue = n;
                    }
                    if (cube instanceof Cube.Green(var n) && n > maxGreen) {
                        maxGreen = n;
                    }
                }
            }
            return Set.of(new Cube.Red(maxRed), new Cube.Blue(maxBlue), new Cube.Green(maxGreen));
        }
        
        int power() {
            return minimumSet().stream().mapToInt(Cube::number).reduce(1,(x,y)->x*y);
        }
    }

    static Stream<Game> processFile(Path inputFile) throws IOException {
        return Files.lines(inputFile).map(Game::of);
    }
    // The Elf would first like to know which games would have been possible if the bag contained only 12 red cubes, 13 green cubes, and 14 blue cubes?
    // 
    // In the example above, games 1, 2, and 5 would have been possible if the bag had been loaded with that configuration.
    // However, game 3 would have been impossible because at one point the Elf showed you 20 red cubes at once; similarly, game 4 would also have been impossible because the Elf showed you 15 blue cubes at once. If you add up the IDs of the games that would have been possible, you get 8.
    // 
    // Determine which games would have been possible if the bag had been loaded with only 12 red cubes, 13 green cubes, and 14 blue cubes.
    // What is the sum of the IDs of those games?
    static boolean ifPossible(Game check) {
        for (var subgame : check.games()) {
            for (var cube : subgame) {
                if (cube instanceof Cube.Red(var n) && n > 12) {
                    return false;
                }
                if (cube instanceof Cube.Blue(var n) && n > 14) {
                    return false;
                }
                if (cube instanceof Cube.Green(var n) && n > 13) {
                    return false;
                }
            }
        }
        return true;
    }

    static List<Integer> validGameIds(Stream<Game> input) {
        return input.filter(Day2::ifPossible).map(Game::id).toList();
    }

    static int sumGameIds(Stream<Game> input) {
        return input.filter(Day2::ifPossible).mapToInt(Game::id).sum();
    }
    
    static int sumOfPowers(Stream<Game> input) {
        return input.mapToInt(Game::power).sum();
    }

    public static void main(String... args) throws IOException {
        var inputFile = Path.of("input.txt");
        System.out.println("Using inputFile = " + inputFile.toAbsolutePath());
        //Part 1
        var result1 = sumGameIds(processFile(inputFile)); 
        System.out.println("Part 1 = "+result1); 
        //Part 2
        var result2 = sumOfPowers(processFile(inputFile)); 
        System.out.println("Part 2 = "+result2); 
    }
}
