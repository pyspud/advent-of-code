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

    private Optional<String> findMultiply(String instruction) {
        if ("do()".equals(instruction)) {
            this.enabled = true;
        } else if ("don't()".equals(instruction)) {
            this.enabled = false;
        } else if (enabled) {
            return Optional.of(instruction);
        }
        return Optional.empty();
    }
}
