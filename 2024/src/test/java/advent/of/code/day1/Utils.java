package advent.of.code.day1;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.ToLongFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

interface Utils {
    static Stream<String> inputLines(String resource) {
        try {
            var inputFile = Path.of(Utils.class.getResource(resource).toURI());
            assertThat(inputFile).exists();

            return Files.lines(inputFile);
        } catch (IOException | URISyntaxException e) {
            return Stream.empty();
        }
    }

    static long[] sortedFieldValues(List<Pair> pairs, ToLongFunction<Pair> func) {
        return pairs.stream().mapToLong(func).sorted().toArray();
    }

    static long sumOfDistances(long[] left, long[] right) {
        return IntStream.range(0, left.length).mapToLong(i -> Math.abs(left[i] - right[i])).sum();
    }

    static List<Pair> getCounts(List<Pair> pairs) {
        return pairs.stream()
                .map(pair -> new Pair(pair.left(),
                            pairs.stream()
                                .map(Pair::right)
                                .filter(n -> n.equals(pair.left()))
                                .count())
                ).toList();
    }

    static long similarityScore(List<Pair> rightCounts) {
        return rightCounts.stream()
                .mapToLong(p -> p.left() * p.right()).sum();
    }
}
