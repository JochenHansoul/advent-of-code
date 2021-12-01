package day03;

/*
You start on the open square (.) in the top-left corner and need to reach the bottom (below the bottom-most row on your map).

The toboggan can only follow a few specific slopes (you opted for a cheaper model that prefers rational numbers);
start by counting all the trees you would encounter for the slope right 3, down 1:
From your starting position at the top-left, check the position that is right 3 and down 1. Then, check the position
that is right 3 and down 1 from there, and so on until you go past the bottom of the map.
The locations you'd check in the above example.txt are marked here with O where there was an open square and X where there
was a tree:
..##.........##.........##.........##.........##.........##.......  --->
#..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
.#....X..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
..#.#...#O#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
.#...##..#..X...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
..#.##.......#.X#.......#.##.......#.##.......#.##.......#.##.....  --->
.#.#.#....#.#.#.#.O..#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
.#........#.#........X.#........#.#........#.#........#.#........#
#.##...#...#.##...#...#.X#...#...#.##...#...#.##...#...#.##...#...
#...##....##...##....##...#X....##...##....##...##....##...##....#
.#..#...#.#.#..#...#.#.#..#...X.#.#..#...#.#.#..#...#.#.#..#...#.#  --->

--- Part Two ---

Time to check the rest of the slopes - you need to minimize the probability of a sudden arboreal stop, after all.
Determine the number of trees you would encounter if, for each of the following slopes, you start at the top-left corner
and traverse the map all the way to the bottom:

    Right 1, down 1.
    Right 3, down 1. (This is the slope you already checked.)
    Right 5, down 1.
    Right 7, down 1.
    Right 1, down 2.

In the above example.txt, these slopes would find 2, 7, 3, 4, and 2 tree(s) respectively; multiplied together, these
produce the answer 336.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.OptionalInt;

public class TreeCounterMultipleSlope {

    private static void fillArrayList(Path path, ArrayList<String> list) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        final Path PATH = Paths.get("src/main/resources/day03/tree_field.txt");
        final char TREE = '#';
        final int[] HORIZONTAL_SLOPES = {1, 3, 5, 7, 1}; // move right
        final int[] VERTICAL_SLOPES = {1, 1, 1, 1, 2}; // move down

        ArrayList<String> field = new ArrayList<>();
        fillArrayList(PATH, field);

        // making sure field is long enough
        int maxHorizontal = Arrays.stream(HORIZONTAL_SLOPES).max().getAsInt();
        int minVertical = Arrays.stream(VERTICAL_SLOPES).min().getAsInt();
        int multiplyWidthCoefficient = Math.round(maxHorizontal - minVertical) + 1;
        multiplyWidthCoefficient = (multiplyWidthCoefficient > 0) ? multiplyWidthCoefficient : 1;
        int fieldLengthVertical = field.size();
        int fieldLengthHorizontal = field.get(0).length();
        int multiply = fieldLengthVertical * multiplyWidthCoefficient / fieldLengthHorizontal + 1;
        for (int i = 0; i < field.size(); i++) {
            field.set(i, field.get(i).repeat(multiply));
        }
        System.out.printf("horizontal: %s, vertical: %s%n", field.get(0).length(), field.size());

        // running trough slopes
        int[] treesDuringEachSlope = new int[HORIZONTAL_SLOPES.length];
        for (int i = 0; i < HORIZONTAL_SLOPES.length; i++) {
            // calculating amount of tree's per slope
            int treesDuringSlope = 0;
            int horizontal = 0;
            for (int j = 0; j < field.size(); j += VERTICAL_SLOPES[i]) { // vertical
                if (field.get(j).charAt(horizontal) == TREE) {
                    treesDuringSlope++;
                }
                horizontal += HORIZONTAL_SLOPES[i];
            }
            System.out.printf("Trees encountered during slope %s: %s%n", i + 1, treesDuringSlope);
            treesDuringEachSlope[i] = treesDuringSlope;
        }
        long result = 1L;
        for (int treesDuringSlope : treesDuringEachSlope) {
            result *= treesDuringSlope;
        }
        System.out.println("Product trees encountered: " + result);
        // fout: 469. Answer is to low (had eerst som van trees gemaakt)
    }
}