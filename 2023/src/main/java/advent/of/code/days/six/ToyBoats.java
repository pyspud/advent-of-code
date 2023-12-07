package advent.of.code.days.six;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class ToyBoats {
    
    private List<Race> races;

    
    public record Race(long duration, long recordDistance) {
        public long distanceTravelled(long buttonHold)
        {
            if (buttonHold < 1 || buttonHold >= duration) {
                return 0;
            }
            var remainingTime = duration - buttonHold;
            return buttonHold * remainingTime;
        }
        
        public List<Long> newRecords() {
            return LongStream.range(1, duration)
                    .map(this::distanceTravelled)
                    .filter(t->t>recordDistance)
                    .boxed()
                    .toList();
        }
     }
    
    public ToyBoats(List<String> lines) {
        races = new ArrayList<>();

        var times = lines.get(0)
                .substring(9)
                .strip()
                .split("\\s+");
        var distances = lines.get(1)
                .substring(9)
                .strip()
                .split("\\s+");

        for (int i = 0; i < distances.length; i++) {
            races.add(new Race(Integer.parseInt(times[i]), Integer.parseInt(distances[i])));
        }
    }
    
    public List<Race> races() {
        return races;
    }

    public static Race singleRace(List<String> lines) {
        var time = lines.get(0)
                .substring(9)
                .strip()
                .replace(" ","");
        var distance = lines.get(1)
                .substring(9)
                .strip()
                .replace(" ", "");
        return new Race(Long.parseLong(time), Long.parseLong(distance));
    }
}