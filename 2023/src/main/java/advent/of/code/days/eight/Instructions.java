package advent.of.code.days.eight;

import java.util.Iterator;

public class Instructions implements Iterator<Instructions.Direction> {
    public enum Direction {
        LEFT, RIGHT;

        public static Direction of(char c) {
            return switch(c){
                case 'l','L'->LEFT;
                case 'r','R'->RIGHT;
                default->throw new UnsupportedOperationException("Unsupported Direction: "+c);
            };
        }
    }
    
    private String line;
    private int current=0;

    private Instructions() {
    }

    private Instructions(String line) {
        this.line = line;
    }
    
    public static Instructions of(String line) {
        return new Instructions(line);
    }

    public void reset() {
        current = 0;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @SuppressWarnings("java:S2272")
    @Override
    public Direction next() {
        var c = line.charAt(current);
        current++;
        if(current==line.length()){
            current=0;
        }
        return Direction.of(c);
    }

    
    
}
