package advent.of.code.day3;

import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ConditionalMemory {
    static final Pattern FORMAT = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\(\\d+?,\\d+?\\))");

    boolean enabled = true;
    Stream<String> input;

    ConditionalMemory() {}

    ConditionalMemory(Stream<String> input) {
            this.input = input;
    }


    Stream<String> getInstructions() {
        return this.input.flatMap(this::process);
    }

    Stream<String> process(String line) {
        return FORMAT.matcher(line)
                .results()
                .map(MatchResult::group)
                .map(this::findMultiply)
                .flatMap(Optional::stream);
    }

    @SuppressWarnings("java:S108")
    private Optional<String> findMultiply(String instruction) {
        switch (instruction) {
            case "do()" -> this.enabled = true;
            case "don't()" -> this.enabled = false;
            case String _ when this.enabled -> {return Optional.of(instruction);}
            default -> {}
        }
        return Optional.empty();
    }
}
