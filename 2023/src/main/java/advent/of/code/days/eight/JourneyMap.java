package advent.of.code.days.eight;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JourneyMap {
    Instructions instructions;
    Map<String, MapNode> nodes;
    Set<MapNode> startNodes;

    public JourneyMap(List<String> lines) {
        instructions = Instructions.of(lines.get(0));
        nodes = HashMap.newHashMap(lines.size());
        startNodes = new HashSet<>();

        for (int i = 2; i < lines.size(); i++) {
            var node = MapNode.of(lines.get(i));
            var key = node.key();
            nodes.put(key, node);
            if (key.endsWith("A")) {
                startNodes.add(node);
            }
        }

        System.out.println("startNodes = " + startNodes);
    }

    private record End(int steps, String label) {
    }

    public int steps() {
        instructions.reset();
        var end = steps("AAA");
        return end.steps();
    }

    public End steps(String start) {
        int steps = 0;
        var current = nodes.get(start);
        while (instructions.hasNext()) {
            var next = current.next(instructions.next());
            current = nodes.get(next);
            steps++;
            if (current.key().endsWith("Z")) {
                break;
            }
        }
        return new End(steps, current.key());
    }

    public record ParallelSteps(String key, int firstSteps, int nextSteps) {
    }

    public long parallelSteps() {
        List<ParallelSteps> parallelSteps = new ArrayList<>();
        for (var node : startNodes) {
            instructions.reset();

            var end = steps(node.key());
            var initialSteps = end.steps();
            end = steps(end.label());
            var nextSteps = end.steps();

            parallelSteps.add(new ParallelSteps(node.key(), initialSteps, nextSteps));
        }
        
        BigInteger lcm = BigInteger.valueOf(parallelSteps.get(0).firstSteps());
        for (int i = 1; i < parallelSteps.size(); i++) {
            var newValue = BigInteger.valueOf(parallelSteps.get(i).firstSteps());
            var gcd = lcm.gcd(newValue);

            lcm = lcm.multiply(newValue).divide(gcd);
        }
        // System.out.println("parallelSteps = " + parallelSteps);
        // System.out.println("lcm = " + lcm);
        return lcm.longValue();
    }
}
