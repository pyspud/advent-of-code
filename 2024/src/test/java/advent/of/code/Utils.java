package advent.of.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface Utils {

    static Stream<String> inputLines(String resource) {
        try {
            var inputFile = Path.of(Utils.class.getResource(resource).toURI());
            assertThat(inputFile).exists();

            return Files.lines(inputFile);
        } catch (IOException | URISyntaxException e) {
            return Stream.empty();
        }
    }

}
