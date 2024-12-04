package advent.of.code.day4;

import java.util.List;

record WordSearch(List<String> puzzle) {
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

    String wordInDirection(int l, int c, int lDelta, int cDelta) {
        StringBuffer temp = new StringBuffer(4);
        for (int i = 0; i < 4; i++) {
            temp.append(letterAt(l + (i * lDelta), c + (i * cDelta)));
        }
        return temp.toString();
    }

    long foundXmas(int l, int c) {
        long result = 0l;
        if (letterAt(l, c) == 'X') {
            for (int lDelta = -1; lDelta <= 1; lDelta++) {
                for (int cDelta = -1; cDelta <= 1; cDelta++) {
                    if("XMAS".equals(wordInDirection(l, c, lDelta, cDelta)))
                        result++;
                }
            }
        }
        return result;
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
