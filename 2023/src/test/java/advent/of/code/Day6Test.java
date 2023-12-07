package advent.of.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import advent.of.code.days.six.ToyBoats;
import advent.of.code.days.six.ToyBoats.Race;

class Day6Test {
    // For example:
    private static final Race FIRST_RACE = new ToyBoats.Race(7, 9);
    private static final Race SECOND_RACE = new ToyBoats.Race(15, 40);
    private static final Race THIRD_RACE = new ToyBoats.Race(30, 200);

    final static List<String> INPUT = List.of(
            "Time:      7  15   30",
            "Distance:  9  40  200"
    );;

    // This document describes three races:

    // The first race lasts 7 milliseconds. The record distance in this race is 9 millimetres.
    // The second race lasts 15 milliseconds. The record distance in this race is 40 millimetres.
    // The third race lasts 30 milliseconds. The record distance in this race is 200 millimetres.
    @Test
    void shouldParseCompetition() {
        var expected = List.of(
            FIRST_RACE,
            SECOND_RACE,
            THIRD_RACE
        );

        var competition = new ToyBoats(INPUT);
        var actual = competition.races();

        assertThat(actual).isEqualTo(expected);
    }
    // Your toy boat has a starting speed of zero millimetres per millisecond.
    // For each whole millisecond you spend at the beginning of the race holding down the button,
    // the boat's speed increases by one millimetre per millisecond.

    // So, because the first race lasts 7 milliseconds, you only have a few options:

    // Don't hold the button at all (that is, hold it for 0 milliseconds) at the start of the race.
    //  The boat won't move; it will have travelled 0 millimetres by the end of the race.
    
    // Hold the button for 1 millisecond at the start of the race.
    //  Then, the boat will travel at a speed of 1 millimetre per millisecond for 6 milliseconds,
    //  reaching a total distance travelled of 6 millimetres.
    
    // Hold the button for 2 milliseconds, giving the boat a speed of 2 millimetres per millisecond.
    //  It will then get 5 milliseconds to move, reaching a total distance of 10 millimetres.
    
    // Hold the button for 3 milliseconds. After its remaining 4 milliseconds of travel time,
    //  the boat will have gone 12 millimetres.
    
    // Hold the button for 4 milliseconds. After its remaining 3 milliseconds of travel time,
    //  the boat will have gone 12 millimetres.
    
    // Hold the button for 5 milliseconds,
    //  causing the boat to travel a total of 10 millimetres.
    
    // Hold the button for 6 milliseconds,
    //  causing the boat to travel a total of 6 millimetres.
    
    // Hold the button for 7 milliseconds.
    //  That's the entire duration of the race.
    //  You never let go of the button.
    //  The boat can't move until you let go of the button.
    //  Please make sure you let go of the button so the boat gets to move. 0 millimetres.

    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "1, 6",
        "2, 10",
        "3, 12",
        "4, 12",
        "5, 10",
        "6, 6",
        "7, 0"
    })
    void shouldCalculateDistanceTravelled(int buttonHold, int expectedDistanceTravelled) {
        var actual = FIRST_RACE.distanceTravelled(buttonHold);
        assertThat(actual).isEqualTo(expectedDistanceTravelled);
    }
    // Since the current record for this race is 9 millimetres,
    //  there are actually 4 different ways you could win:
    //  you could hold the button for 2, 3, 4, or 5 milliseconds at the start of the race.
    @Test
    void firstRaceShouldHave4WaysToWin() {
        var actual = FIRST_RACE.newRecords();
        assertThat(actual).hasSize(4);
    }
    // In the second race, you could hold the button for at least 4 milliseconds and at most 11 milliseconds and beat the record,
    //  a total of 8 different ways to win.
    @Test
    void secondRaceShouldHave8WaysToWin() {
        var actual = SECOND_RACE.newRecords();
        assertThat(actual).hasSize(8);
    }
    
    // In the third race, you could hold the button for at least 11 milliseconds and no more than 19 milliseconds and still beat the record,
    //  a total of 9 ways you could win.
    @Test
    void thirdRaceShouldHave9WaysToWin() {
        var actual = THIRD_RACE.newRecords();
        assertThat(actual).hasSize(9);
    }

    // To see how much margin of error you have, determine the number of ways you can beat the record in each race;
    //  in this example, if you multiply these values together, you get 288 (4 * 8 * 9).

    // Determine the number of ways you could beat the record in each race.
    // What do you get if you multiply these numbers together?
}
