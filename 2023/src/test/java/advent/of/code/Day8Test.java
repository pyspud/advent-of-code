package advent.of.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import advent.of.code.days.eight.Instructions;
import advent.of.code.days.eight.Instructions.Direction;
import advent.of.code.days.eight.JourneyMap;
import advent.of.code.days.eight.MapNode;

class Day8Test {
    // Part 1
    // This format defines each node of the network individually. For example:

    final static String INPUT_1 = """
        RL
        
        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)
        """;
    @Test
    void shouldProcessDirectionLine() {
        var expect = List.of(
                Direction.LEFT,
                Direction.LEFT,
                Direction.RIGHT,
                Direction.LEFT,
                Direction.LEFT,
                Direction.RIGHT);
        var instruction = Instructions.of("LLR");

        var actual = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            actual.add(instruction.next());
        }

        assertThat(actual).isEqualTo(expect);
    }
    
    @Test
    void shouldProcessNodeLine() {
        var expected = new MapNode("CCC","ZZZ", "GGG");
        var actual = MapNode.of("CCC = (ZZZ, GGG)");
        
        assertThat(actual).isEqualTo(expected);
    }

    // Starting with AAA, you need to look up the next element based on the next
    // left/right instruction in your input. In this example, start with AAA and go
    // right (R) by choosing the right element of AAA, CCC. Then, L means to choose
    // the left element of CCC, ZZZ. By following the left/right instructions, you
    // reach ZZZ in 2 steps.

    // Of course, you might not find ZZZ right away. If you run out of left/right
    // instructions, repeat the whole sequence of instructions as necessary: RL
    // really means RLRLRLRLRLRLRLRL... and so on. For example, here is a situation
    // that takes 6 steps to reach ZZZ:
    final static String INPUT_2 = """
        LLR
        
        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)
                """;
        
    @Test
    void shouldMatchInput1(){
        var j = new JourneyMap(INPUT_1.lines().toList());
        assertThat(j.steps()).isEqualTo(2);
    }
        
    @Test
    void shouldMatchInput2() {
        var j = new JourneyMap(INPUT_2.lines().toList());
        assertThat(j.steps()).isEqualTo(6);
    }
    
    // Part2
    final static String INPUT_3 = """
        LR

        11A = (11B, XXX)
        11B = (XXX, 11Z)
        11Z = (11B, XXX)
        22A = (22B, XXX)
        22B = (22C, 22C)
        22C = (22Z, 22Z)
        22Z = (22B, 22B)
        XXX = (XXX, XXX)
                """;
        
    @Test
    void shouldMatchInput3() throws IOException {
        var j = new JourneyMap(INPUT_3.lines().toList());
        assertThat(j.parallelSteps()).isEqualTo(6);
    }
        
}
