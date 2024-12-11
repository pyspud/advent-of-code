package advent.of.code.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Pebbles {

    String stones;

    Pebbles(String initial) {
        this.stones = initial;
    }

    void blink() {
        List<String> newStones = new ArrayList<>();
        // Every time you blink, the stones each simultaneously change according to the first applicable rule in this list:
        for (var stone : Arrays.asList(stones.split(" "))) {
            newStones.addAll(rule(stone));
        }
        this.stones = line(newStones);
    }

    public void blinking(int times) {
        for (int i = 0; i < times; i++) {
            blink();
        }
    }

    String stones() {
        return this.stones;
    }

    static List<String> rule(String stone) {
        if ("0".equals(stone)) {
            // If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1.
            return List.of("1");
        } else if (stone.length() % 2 == 0) {
            // If the stone is engraved with a number that has an even number of digits, it is replaced by two stones.
            return twoStones(stone);
        }
        //If none of the other rules apply, the stone is replaced by a new stone multiplied by 2024
        return List.of(multiplied(stone, 2024));
    }

    // The left half of the digits are engraved on the new left stone,
    // and the right half of the digits are engraved on the new right stone.
    // (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
    static List<String> twoStones(String stone) {
        var half = stone.length() / 2;
        var first = Long.parseLong(stone.substring(0, half));
        var second = Long.parseLong(stone.substring(half, stone.length()));
        return List.of(Long.toString(first), Long.toString(second));
    }

    // the old stone's number multiplied by 2024 is engraved on the new stone
    static String multiplied(String stone, int value) {
        long result = Long.parseLong(stone) * value;
        return Long.toString(result);
    }

    static String line(List<String> stones) {
        return String.join(" ", stones);
    }
}
