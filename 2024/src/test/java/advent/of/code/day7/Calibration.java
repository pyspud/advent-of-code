package advent.of.code.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

record Calibration(long expected, List<Long> values) {
    enum Operation {PLUS,MULTIPLE,CONCAT}

    boolean isValidTwoOps() {
        var operations = findWithTwoOperations();
        return !operations.isEmpty();
    }

    List<Operation> findWithTwoOperations() {
        return findWithTwoOperations(values.getFirst().longValue(), sublist(values), List.of());
    }

    List<Operation> findWithTwoOperations(long current, List<Long> remainingNumbers, List<Operation> opsSoFar) {
        if (remainingNumbers.isEmpty()) {
            return List.of();
        }

        List<Operation> ops = new ArrayList<>();

        long prod = current * remainingNumbers.getFirst().longValue();
        var opsMul = new ArrayList<>(opsSoFar);
        opsMul.add(Operation.MULTIPLE);
        if (prod == expected && remainingNumbers.size()==1) {
            return opsMul;
        } else if (prod <= expected) {
            ops.addAll(findWithTwoOperations(prod, sublist(remainingNumbers), opsMul));
        }

        long sum = current + remainingNumbers.getFirst().longValue();
        var opsPlus = new ArrayList<>(opsSoFar);
        opsPlus.add(Operation.PLUS);
        if (sum == expected && remainingNumbers.size()==1) {
            return opsPlus;
        } else if (sum <= expected) {
            var temp = findWithTwoOperations(sum, sublist(remainingNumbers), opsPlus);
            if(ops.isEmpty()) {
                ops.addAll(temp);
            }
        }

        return ops;
    }

    boolean isValidThreeOps() {
        var operations = findWithThreeOperations();
        return !operations.isEmpty();
    }

    List<Operation> findWithThreeOperations() {
        return findWithThreeOperations(values.getFirst().longValue(), sublist(values), List.of());
    }

    List<Operation> findWithThreeOperations(long current, List<Long> remainingNumbers, List<Operation> opsSoFar) {
        if (remainingNumbers.isEmpty()) {
            return List.of();
        }

        List<Operation> ops = new ArrayList<>();

        long prod = current * remainingNumbers.getFirst().longValue();
        var opsMul = new ArrayList<>(opsSoFar);
        opsMul.add(Operation.MULTIPLE);
        if (prod == expected && remainingNumbers.size() == 1) {
            return opsMul;
        } else if (prod <= expected) {
            ops.addAll(findWithThreeOperations(prod, sublist(remainingNumbers), opsMul));
        }

        long sum = current + remainingNumbers.getFirst().longValue();
        var opsPlus = new ArrayList<>(opsSoFar);
        opsPlus.add(Operation.PLUS);
        if (sum == expected && remainingNumbers.size() == 1) {
            return opsPlus;
        } else if (sum <= expected) {
            var temp = findWithThreeOperations(sum, sublist(remainingNumbers), opsPlus);
            if (ops.isEmpty()) {
                ops.addAll(temp);
            }
        }

        long cat = concat(current, remainingNumbers.getFirst().longValue());
        var opsCat = new ArrayList<>(opsSoFar);
        opsCat.add(Operation.CONCAT);
        if (cat == expected && remainingNumbers.size() == 1) {
            return opsCat;
        } else if (sum <= expected) {
            var temp = findWithThreeOperations(cat, sublist(remainingNumbers), opsCat);
            if (ops.isEmpty()) {
                ops.addAll(temp);
            }
        }

        return ops;
    }

    long concat(long a, long b) {
        var first = Long.toString(a);
        var second = Long.toString(b);
        return Long.parseLong(first + second);
    }

    private List<Long> sublist(List<Long> remainingNumbers) {
        return remainingNumbers.subList(1, remainingNumbers.size());
    }

    static Calibration of(String line) {
        var parts = line.split(" ");
        var first = parts[0].substring(0, parts[0].length() - 1);
        var numbers = IntStream.range(1, parts.length)
                .mapToObj(i->parts[i])
                .map(Long::valueOf)
                .toList();
        return new Calibration(Long.parseLong(first), numbers);
    }
}
