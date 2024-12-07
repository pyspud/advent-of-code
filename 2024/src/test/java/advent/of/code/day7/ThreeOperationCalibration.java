package advent.of.code.day7;

import java.util.List;
import java.util.stream.IntStream;

record ThreeOperationCalibration(long expected, List<Long> values) {

    boolean isSolvable() {
        return isSolvable(values.getFirst().longValue(), sublist(values));
    }

    boolean isSolvable(long current, List<Long> remainingNumbers) {
        if (remainingNumbers.isEmpty()) {
            return false;
        }
        boolean result = false;

        long prod = current * remainingNumbers.getFirst().longValue();
        if (prod == expected && remainingNumbers.size() == 1) {
            return true;
        } else if (prod <= expected) {
            result = isSolvable(prod, sublist(remainingNumbers));
        }

        long sum = current + remainingNumbers.getFirst().longValue();
        if (sum == expected && remainingNumbers.size() == 1) {
            return true;
        } else if (sum <= expected) {
            var temp = isSolvable(sum, sublist(remainingNumbers));
            if (!result) {
                result = temp;
            }
        }

        long cat = concat(current, remainingNumbers.getFirst().longValue());
        if (cat == expected && remainingNumbers.size() == 1) {
            return true;
        } else if (sum <= expected) {
            var temp = isSolvable(cat, sublist(remainingNumbers));
            if (!result) {
                result = temp;
            }
        }

        return result;
    }

    long concat(long a, long b) {
        var first = Long.toString(a);
        var second = Long.toString(b);
        return Long.parseLong(first + second);
    }

    private List<Long> sublist(List<Long> remainingNumbers) {
        return remainingNumbers.subList(1, remainingNumbers.size());
    }

    static ThreeOperationCalibration of(String line) {
        var parts = line.split(" ");
        var first = parts[0].substring(0, parts[0].length() - 1);
        var numbers = IntStream.range(1, parts.length)
                .mapToObj(i->parts[i])
                .map(Long::valueOf)
                .toList();
        return new ThreeOperationCalibration(Long.parseLong(first), numbers);
    }
}
