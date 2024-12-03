package advent.of.code.day3;

import java.util.regex.Pattern;

record Multiply(long a, long b) {
    long result() {
        return a * b;
    }

    static final Pattern FORMAT = Pattern.compile("mul\\((\\d+?),(\\d+?)\\)");

    static Multiply of(String input) {
        var matches = FORMAT.matcher(input);
        matches.matches();

        var aString = matches.group(1);
        var bString = matches.group(2);
        return new Multiply(Long.parseLong(aString), Long.parseLong(bString));
    }
}
