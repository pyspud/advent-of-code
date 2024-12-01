package advent.of.code.day1;

import java.util.regex.Pattern;

// Maybe the lists are only off by a small amount! To find out, pair up the
// numbers and measure how far apart they are.
// Pair up the smallest number in the left list with the smallest number in the
// right list,
// then the second-smallest left number with the second-smallest right number,
// and so on.
record Pair(long left, long right) {
    long distance() {
        return Math.abs(left - right);
    }

    static final Pattern INPUT_FORMAT = Pattern.compile("^(\\d+?)\\s+?(\\d+?)$");

    static Pair of(String line) {
        var matches = INPUT_FORMAT.matcher(line);
        matches.matches();
        var leftString = matches.group(1);
        var rightString = matches.group(2);
        return new Pair(Long.parseLong(leftString), Long.parseLong(rightString));
    }
}