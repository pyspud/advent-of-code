package advent.of.code.day4;

import java.util.List;

record WordSearch(List<String> puzzle) {
    int lines() {
        return puzzle.size();
    }

    int chars() {
        return puzzle.get(0).length();
    }

    long foundXmas(int l, int c) {
        long result = 0l;
        if (isLetterAt('X', l, c)) {
            // E
            if (isLetterAt('M', l, c + 1) && isLetterAt('A', l, c + 2) && isLetterAt('S', l, c + 3))
                result++;
            // SE
            if (isLetterAt('M', l + 1, c + 1) && isLetterAt('A', l + 2, c + 2) && isLetterAt('S', l + 3, c + 3))
                result++;
            // S
            if (isLetterAt('M', l + 1, c) && isLetterAt('A', l + 2, c) && isLetterAt('S', l + 3, c))
                result++;
            // SW
            if (isLetterAt('M', l + 1, c - 1) && isLetterAt('A', l + 2, c - 2) && isLetterAt('S', l + 3, c - 3))
                result++;
            // W
            if (isLetterAt('M', l, c - 1) && isLetterAt('A', l, c - 2) && isLetterAt('S', l, c - 3))
                result++;
            // NW
            if (isLetterAt('M', l - 1, c - 1) && isLetterAt('A', l - 2, c - 2) && isLetterAt('S', l - 3, c - 3))
                result++;
            // N
            if (isLetterAt('M', l - 1, c) && isLetterAt('A', l - 2, c) && isLetterAt('S', l - 3, c))
                result++;
            // NE
            if (isLetterAt('M', l - 1, c + 1) && isLetterAt('A', l - 2, c + 2) && isLetterAt('S', l - 3, c + 3))
                result++;
        }
        return result;
    }

    boolean isLetterAt(char letter, int l, int c) {
        if (validCoord(l, c)) {
            return puzzle.get(l).charAt(c) == letter;
        }
        return false;
    }

    boolean validCoord(int l, int c) {
        return (0 <= l && l < lines()) && (0 <= c && c < chars());
    }

    long countXmas() {
        var result = 0l;
        for (int l = 0; l < lines(); l++) {
            for (int c = 0; c < chars(); c++) {
                result += foundXmas(l, c);
            }
        }
        return result;
    }
}
