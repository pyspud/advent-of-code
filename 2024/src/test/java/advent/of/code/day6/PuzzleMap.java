package advent.of.code.day6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PuzzleMap {
    record Coord(int line, int column) {
        Coord move(Move direction) {
            return new Coord(line + direction.line, column + direction.column);
        }
    }

    enum Move {
        NORTH(-1, 0),
        EAST(0, 1),
        SOUTH(1, 0),
        WEST(0, -1);

        final int column;
        final int line;

        Move(int l, int c) {
            this.line = l;
            this.column = c;
        }
    }

    Set<Coord> boxes = new HashSet<>();
    int columns;
    int lines;
    Coord robot;
    Move moving = Move.NORTH;

    PuzzleMap(){}

    PuzzleMap(List<String> input) {
        this.lines = input.size();
        this.columns = input.get(0).length();

        for (int l = 0; l < lines; l++) {
            for (int c = 0; c < columns; c++) {
                if ('#' == input.get(l).charAt(c)) {
                    boxes.add(new Coord(l, c));
                } else if ('^' == input.get(l).charAt(c)) {
                    robot = new Coord(l, c);
                }
            }
        }
    }


    // Robot instructions
    // If there is something directly in front of you, turn right 90 degrees.
    // Otherwise, take a step forward.
    Coord move() {
        var next = robot.move(moving);
        if (boxes.contains(next)) {
            moving = turn(moving);
            return robot;
        } else {
            next = robot.move(moving);
            return next;
        }
    }

    Move turn(Move move) {
        return switch(move){
            case NORTH -> Move.EAST;
            case EAST -> Move.SOUTH;
            case SOUTH -> Move.WEST;
            case WEST -> Move.NORTH;
        };
    }

    boolean onMap(Coord position) {
        return 0 <= position.line() && position.line() < lines
                && 0 <= position.column() && position.column() < columns;
    }

    Set<Coord> guardVisit() {
        Set<Coord> visits = new HashSet<>();
        while (onMap(robot)) {
            visits.add(robot);
            this.robot = move();
        }
        return visits;
    }

    boolean guardLoops() {
        record Step(Coord position, Move Move) {}

        Set<Step> stepsTaken =  new HashSet<>();
        while (onMap(this.robot)) {
            var currentStep = new Step(robot, moving);
            if (stepsTaken.add(currentStep)) {
                this.robot = move();
            } else {
                return true;
            }
        }
        return false;
    }

    Set<Coord> obstructionsCauseLoop() {
        Set<Coord> obstructions = new HashSet<>();

        for (int l = 0; l < lines; l++) {
            for (int c = 0; c < columns; c++) {
                var candidate = new Coord(l, c);
                if (boxes.contains(candidate)||candidate.equals(robot))
                    continue;

                var map = this.copyWith(candidate);
                if (map.guardLoops()) {
                    obstructions.add(candidate);
                }
            }
        }
        return obstructions;
    }

    PuzzleMap copyWith(Coord additionalBox){
        var newMap = new PuzzleMap();

        newMap.columns = this.columns;
        newMap.lines = this.lines;
        newMap.robot = this.robot;

        newMap.boxes.add(additionalBox);
        newMap.boxes.addAll(this.boxes);

        return newMap;
    }

}
