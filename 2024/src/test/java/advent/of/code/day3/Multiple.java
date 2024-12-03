package advent.of.code.day3;

import java.util.regex.Pattern;

record Multiple(long a, long b) {
    long result() {
        return a * b;
    }

    static final Pattern FORMAT = Pattern.compile("mul\\((\\d+?),(\\d+?)\\)");

    static Multiple of(String input) {
        var matches = FORMAT.matcher(input);
        matches.matches();

        var aString = matches.group(1);
        var bString = matches.group(2);
        return new Multiple(Long.parseLong(aString), Long.parseLong(bString));
    }
}
