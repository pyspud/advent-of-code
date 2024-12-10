package advent.of.code.day10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class TopographicMap {
    final List<String> map;
    private int lines;
    private int columns;

    record Point(int l, int c) {
        Point up() {
            return new Point(l-1, c);
        }
        Point down() {
            return new Point(l+1, c);
        }
        Point left() {
            return new Point(l, c-1);
        }
        Point right() {
            return new Point(l, c+1);
        }
    }

    public TopographicMap(Stream<String> input) {
        this.map = input.toList();
        this.lines = map.size();
        this.columns = map.get(0).length();
    }

    public List<Point> trailHeads() {
        List<Point> heads = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            var line = map.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '0') {
                    heads.add(new Point(i, j));
                }
            }
        }
        return heads;
    }

    int heightAt(Point p) {
        return map.get(p.l).charAt(p.c) - '0';
    }

    boolean offTheMap(Point p) {
        return 0 > p.l || p.l >= lines
            || 0 > p.c || p.c >= columns;
    }

    public Integer score(Point head) {
        Set<Point> trailEnds = new HashSet<>();
        int score = trailScorePath(1, head.up(), trailEnds)
                  + trailScorePath(1, head.down(), trailEnds)
                  + trailScorePath(1, head.left(), trailEnds)
                  + trailScorePath(1, head.right(), trailEnds);
        return Integer.valueOf(score);
    }

    int trailScorePath(int trailStepHeight, Point point, Set<Point> trailEnds) {
        if (offTheMap(point) || trailEnds.contains(point)) {
            return 0;
        }
        var pointHeight = heightAt(point);
        if (pointHeight != trailStepHeight) {
            return 0;
        } else if (pointHeight == 9) {
            trailEnds.add(point);
            return 1;
        }
        return trailScorePath(trailStepHeight + 1, point.up(), trailEnds)
                + trailScorePath(trailStepHeight + 1, point.down(), trailEnds)
                + trailScorePath(trailStepHeight + 1, point.left(), trailEnds)
                + trailScorePath(trailStepHeight + 1, point.right(), trailEnds);
    }

    public Integer rating(Point head) {
        List<Point> trail = new ArrayList<>();
        trail.add(head);
        Set<List<Point>> trails = new HashSet<>();
        int score = trailRatingPath(1, head.up(), trail, trails)
                  + trailRatingPath(1, head.down(), trail, trails)
                  + trailRatingPath(1, head.left(), trail, trails)
                  + trailRatingPath(1, head.right(), trail, trails);
        return Integer.valueOf(score);
    }

    int trailRatingPath(int trailStepHeight, Point currentPoint, List<Point> trail, Set<List<Point>> trails) {
        if (offTheMap(currentPoint)) {
            return 0;
        }
        var pointHeight = heightAt(currentPoint);
        if (pointHeight != trailStepHeight ) {
            return 0;
        } else if (pointHeight == 9) {
            trails.add(trail);
            return 1;
        }
        trail.add(currentPoint);
        return trailRatingPath(trailStepHeight+1, currentPoint.up(), trail, trails)
             + trailRatingPath(trailStepHeight+1, currentPoint.down(), trail, trails)
             + trailRatingPath(trailStepHeight+1, currentPoint.left(), trail, trails)
             + trailRatingPath(trailStepHeight+1, currentPoint.right(), trail, trails);
    }

}
