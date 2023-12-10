package advent.of.code.days.eight;

import java.util.regex.Pattern;

public record MapNode(String key, String left, String right) {
    public String next(Instructions.Direction d) {
        return switch (d) {
            case LEFT -> left;
            case RIGHT -> right;
        };
    }
    
    private static final Pattern MAP_NODE_PATTERN = Pattern.compile("(?<key>\\w+) = \\((?<left>\\w+), (?<right>\\w+)\\)");

    public static MapNode of(String line) {
        var match = MAP_NODE_PATTERN.matcher(line);
        match.matches();

        var key = match.group("key");
        var left = match.group("left");
        var right = match.group("right");
        return new MapNode(key, left, right);
    }
}
