package day05;

/*
--- Day 5: Binary Boarding ---

You board your plane only to discover a new problem: you dropped your boarding pass! You aren't sure which seat is
yours, and all of the flight attendants are busy with the flood of people that suddenly made it through passport control.
You write a quick program to use your phone's camera to scan all of the nearby boarding passes (your puzzle input);
perhaps you can find your seat through process of elimination.

Instead of zones or groups, this airline uses binary space partitioning to seat people. A seat might be specified like
FBFBBFFRLR, where F means "front", B means "back", L means "left", and R means "right".

The first 7 characters will either be F or B; these specify exactly one of the 128 rows on the plane
(numbered 0 through 127). Each letter tells you which half of a region the given seat is in. Start with the whole list
of rows; the first letter indicates whether the seat is in the front (0 through 63) or the back (64 through 127).
The next letter indicates which half of that region the seat is in, and so on until you're left with exactly one row.

For example, consider just the first seven characters of FBFBBFFRLR:
    Start by considering the whole range, rows 0 through 127.
    F means to take the lower half, keeping rows 0 through 63.
    B means to take the upper half, keeping rows 32 through 63.
    F means to take the lower half, keeping rows 32 through 47.
    B means to take the upper half, keeping rows 40 through 47.
    B keeps rows 44 through 47.
    F keeps rows 44 through 45.
    The final F keeps the lower of the two, row 44.

The last three characters will be either L or R; these specify exactly one of the 8 columns of seats on the plane
(numbered 0 through 7). The same process as above proceeds again, this time with only three steps. L means to keep
the lower half, while R means to keep the upper half.

For example, consider just the last 3 characters of FBFBBFFRLR:
    Start by considering the whole range, columns 0 through 7.
    R means to take the upper half, keeping columns 4 through 7.
    L means to take the lower half, keeping columns 4 through 5.
    The final R keeps the upper of the two, column 5.

So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.

Every seat also has a unique seat ID: multiply the row by 8, then add the column.
In this example, the seat has ID 44 * 8 + 5 = 357.

Here are some other boarding passes:
    BFFFBBFRRR: row 70, column 7, seat ID 567.
    FFFBBBFRRR: row 14, column 7, seat ID 119.
    BBFFBBFRLL: row 102, column 4, seat ID 820.

As a sanity check, look through your list of boarding passes. What is the highest seat ID on a boarding pass?
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class BinaryBoarding {
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
