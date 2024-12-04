package advent.of.code.day4;

import java.util.List;

record XMasSearch(List<String> puzzle) {
    int lines() {
        return puzzle.size();
    }

    int chars() {
        return puzzle.get(0).length();
    }

    boolean validCoord(int l, int c) {
        return (0 <= l && l < lines()) && (0 <= c && c < chars());
    }

    char letterAt(int l, int c) {
        if (validCoord(l, c)) {
            return puzzle.get(l).charAt(c);
        }
        return ' ';
    }

    long foundX(int l, int c) {
        long result = 0l;
        if (letterAt(l, c)=='A') {
            var topLeft = letterAt(l-1, c-1);
            var topRight = letterAt(l-1, c+1);
            var bottomLeft = letterAt(l+1, c-1);
            var bottomRight = letterAt(l+1, c+1);

            // M-S left to right
            if (topLeft=='M' && topRight=='S' && bottomLeft=='M' && bottomRight=='S')
                result++;
            // M-S right to left
            if (topLeft=='S' && topRight=='M' && bottomLeft=='S' && bottomRight=='M')
                result++;
            // M-S top to bottom
            if (topLeft=='M' && topRight=='M' && bottomLeft=='S' && bottomRight=='S')
                result++;
            // M-S bottom to top
            if (topLeft=='S' && topRight=='S' && bottomLeft=='M' && bottomRight=='M')
                result++;
        }
        return result;
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
