package advent.of.code.day3;

import java.util.ArrayList;
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
        var matches = FORMAT.matcher(line);
        var actual = new ArrayList<String>();
        while (matches.find()) {
            var instruction = matches.group();
            if ("do()".equals(instruction)) {
                this.enabled = true;
            } else if ("don't()".equals(instruction)) {
                this.enabled = false;
            } else if (enabled) {
                actual.add(instruction);
            }
        }
        return actual.stream();
    }
}
