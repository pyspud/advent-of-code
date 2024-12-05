package advent.of.code.day3;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MemorySegment {
    static final Pattern FORMAT = Pattern.compile("(mul\\(\\d+?,\\d+?\\))");

    Matcher matches;

    MemorySegment(String input) {
        this.matches = FORMAT.matcher(input);
    }

    Stream<String> getInstructions() {
        return this.matches.results().map(MatchResult::group);
    }
}
