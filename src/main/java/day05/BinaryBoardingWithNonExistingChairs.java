package day05;

/*
--- Part Two ---

Ding! The "fasten seat belt" signs have turned on. Time to find your seat.
It's a completely full flight, so your seat should be the only missing boarding pass in your list. However, there's a
catch: some of the seats at the very front and back of the plane don't exist on this aircraft, so they'll be missing
from your list as well.
Your seat wasn't at the very front or back, though; the seats with IDs +1 and -1 from yours will be in your list.

What is the ID of your seat?
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class BinaryBoardingWithNonExistingChairs {
    public static void main(String[] args) {

        final Path PATH = Paths.get("src/main/resources/day05/boarding_passes.txt");
        final int ROWS = 128;
        final int COLUMNS = 8;
        final char UPPER_ROW_SYMBOL = 'B';
        final char LOWER_ROW_SYMBOL = 'F';
        final char UPPER_COLUMN_SYMBOL = 'R';
        final char LOWER_COLUMN_SYMBOL = 'L';

        final int ROWS_LOG2 = (int) (Math.log(ROWS) / Math.log(2));
        final int COLUMNS_LOG2 = (int) (Math.log(COLUMNS) / Math.log(2));

        ArrayList<Integer> ids = new ArrayList<>();
        // read file
        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("[" + UPPER_ROW_SYMBOL + UPPER_COLUMN_SYMBOL + "]", "1");
                line = line.replaceAll("[" + LOWER_ROW_SYMBOL + LOWER_COLUMN_SYMBOL + "]", "0");

                int row = Integer.parseInt(line.substring(0, ROWS_LOG2), 2);
                int column = Integer.parseInt(line.substring(ROWS_LOG2), 2);
                ids.add(row * 8 + column);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("highest seat ID: " + Collections.max(ids));
        // 871: to hight
        // 864: right (added 'L' to UPPER_COLUMN_SYMBOL in stead of 'R'
    }
}
