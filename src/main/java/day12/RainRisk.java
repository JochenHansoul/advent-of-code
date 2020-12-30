/*
--- Day 12: Rain Risk ---

Your ferry made decent progress toward the island, but the storm came in faster than anyone expected. The ferry needs to
take evasive actions!

Unfortunately, the ship's navigation computer seems to be malfunctioning; rather than giving a route directly to safety,
it produced extremely circuitous instructions. When the captain uses the PA system to ask if anyone can help, you
quickly volunteer.

The navigation instructions (your puzzle input) consists of a sequence of single-character actions paired with integer
input values. After staring at them for a few minutes, you work out what they probably mean:

    Action N means to move north by the given value.
    Action S means to move south by the given value.
    Action E means to move east by the given value.
    Action W means to move west by the given value.
    Action L means to turn left the given number of degrees.
    Action R means to turn right the given number of degrees.
    Action F means to move forward by the given value in the direction the ship is currently facing.

The ship starts by facing east. Only the L and R actions change the direction the ship is facing. (That is, if the ship
is facing east and the next instruction is N10, the ship would move north 10 units, but would still move east if the
following action were F.)

For example:

F10
N3
F7
R90
F11

These instructions would be handled as follows:
    F10 would move the ship 10 units east (because the ship starts by facing east) to east 10, north 0.
    N3 would move the ship 3 units north to east 10, north 3.
    F7 would move the ship another 7 units east (because the ship is still facing east) to east 17, north 3.
    R90 would cause the ship to turn right by 90 degrees and face south; it remains at east 17, north 3.
    F11 would move the ship 11 units south to east 17, south 8.

At the end of these instructions, the ship's Manhattan distance (sum of the absolute values of its east/west position
and its north/south position) from its starting position is 17 + 8 = 25.

Figure out where the navigation instructions lead. What is the Manhattan distance between that location and the ship's
starting position?
 */

package day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RainRisk {
    private final static char TURN_LEFT  = 'L';
    private final static char TURN_RIGHT = 'R';

    public static void main(String[] args) {
        final Path PATH = Paths.get("src/main/resources/day12/input.txt");
        final char NORTH = 'N';
        final char SOUTH = 'S';
        final char EAST = 'E';
        final char WEST = 'W';
        final char MOVE_FORWARD = 'F';

        int horizontalDistance = 0;
        int verticalDistance = 0;
        CardinalDirections shipDirection = CardinalDirections.EAST;

        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                char action = line.substring(0, 1).toCharArray()[0];
                int amount = Integer.parseInt(line.substring(1));
                if (action == NORTH || action == MOVE_FORWARD && shipDirection == CardinalDirections.NORTH) {
                    verticalDistance += amount;
                } else if (action == EAST || action == MOVE_FORWARD && shipDirection == CardinalDirections.EAST) {
                    horizontalDistance += amount;
                } else if (action == SOUTH || action == MOVE_FORWARD && shipDirection == CardinalDirections.SOUTH) {
                    verticalDistance -= amount;
                } else if (action == WEST || action == MOVE_FORWARD && shipDirection == CardinalDirections.WEST) {
                    horizontalDistance -= amount;
                } else if (action == TURN_LEFT) {
                    shipDirection = rotate(shipDirection, amount * -1);
                } else {
                    shipDirection = rotate(shipDirection, amount);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        horizontalDistance = Math.max(horizontalDistance, horizontalDistance * -1);
        verticalDistance = Math.max(verticalDistance, verticalDistance * -1);
        System.out.println("horizontal: " + horizontalDistance);
        System.out.println("vertical: " + verticalDistance);
        System.out.println("manhattan distance: " + (horizontalDistance + verticalDistance));
    }

    private static CardinalDirections rotate(CardinalDirections direction, int amount) {
        int degrees = direction.getDegrees();
        degrees += amount;
        while (degrees > 270) {
            degrees -= 360;
        }
        while (degrees < 0) {
            degrees += 360;
        }
        if (degrees == 0) {
            return CardinalDirections.NORTH;
        } else if (degrees == 90) {
            return CardinalDirections.EAST;
        } else if (degrees == 180) {
            return CardinalDirections.SOUTH;
        } else {
            return CardinalDirections.WEST;
        }
    }
}
