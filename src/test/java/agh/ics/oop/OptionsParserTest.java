package agh.ics.oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    @DisplayName("OptionsParser properly parses valid input string")
    void testParseTrue() {
        String[] inputString = "f forward x y z backward b left r l right".split(" ");
        List<MoveDirection> expectedOutput =
                List.of(new MoveDirection[]{
                        MoveDirection.FORWARD,
                        MoveDirection.FORWARD,
                        MoveDirection.BACKWARD,
                        MoveDirection.BACKWARD,
                        MoveDirection.LEFT,
                        MoveDirection.RIGHT,
                        MoveDirection.LEFT,
                        MoveDirection.RIGHT,
                });

        List actualOuput = OptionsParser.parse(inputString);

        assertEquals(expectedOutput, actualOuput);
    }

    @Test
    @DisplayName("OptionsParser properly parses not valid input string")
    void testParseFalse() {
        String[] inputString = "forward f xiks y z backward b left right l r".split(" ");
        List<MoveDirection> expectedOutput =
                List.of(new MoveDirection[]{
                        MoveDirection.FORWARD,
                        MoveDirection.FORWARD,
                        MoveDirection.BACKWARD,
                        MoveDirection.BACKWARD,
                        MoveDirection.LEFT,
                        MoveDirection.RIGHT,
                        MoveDirection.RIGHT,
                        MoveDirection.FORWARD,
                });

        List actualOuput = OptionsParser.parse(inputString);

        assertNotEquals(expectedOutput, actualOuput);
    }
}
