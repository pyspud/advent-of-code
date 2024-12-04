package advent.of.code.day4;

import java.util.List;

record XMasSearch(List<String> puzzle) {
    int lines() {
        return puzzle.size();
    }

    int chars() {
        return puzzle.get(0).length();
    }

    long foundX(int l, int c) {
        long result = 0l;
        if (isLetterAt('A', l, c)) {
            // M-S left to right
            if (isLetterAt('M', l + 1, c - 1) && isLetterAt('M', l - 1, c - 1)
                && isLetterAt('S', l + 1, c + 1) && isLetterAt('S', l-1, c + 1))
                result++;
            // M-S right to left
            if (isLetterAt('M', l + 1, c + 1) && isLetterAt('M', l - 1, c + 1)
                && isLetterAt('S', l + 1, c - 1) && isLetterAt('S', l-1, c - 1))
                result++;
            // M-S top to bottom
            if (isLetterAt('M', l + 1, c - 1) && isLetterAt('M', l + 1, c + 1)
                && isLetterAt('S', l - 1, c - 1) && isLetterAt('S', l - 1, c + 1))
                result++;
            // M-S bottom to top
            if (isLetterAt('M', l - 1, c - 1) && isLetterAt('M', l - 1, c + 1)
                && isLetterAt('S', l + 1, c - 1) && isLetterAt('S', l + 1, c + 1))
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

    long countX() {
        var result = 0l;
        for (int l = 0; l < lines(); l++) {
            for (int c = 0; c < chars(); c++) {
                result += foundX(l, c);
            }
        }
        return result;
    }
}
