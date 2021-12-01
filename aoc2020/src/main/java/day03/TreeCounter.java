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
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TreeCounter {

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

    /*private static void addStringsToArrayList(Path path, ArrayList<String> list) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                list.set(i, list.get(i) + line);
                i++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }*/

    public static void main(String[] args) {
        final char TREE = '#';
        final int HORIZONTAL = 3; // three right
        final int VERTICAL = 1; // one down
        final Path PATH = Paths.get("src/main/resources/day03/tree_field.txt");

        ArrayList<String> field = new ArrayList<>();

        fillArrayList(PATH, field);
        /*while (field.get(0).length() < field.size() * HORIZONTAL) {
            addStringsToArrayList(path, field);
        }*/

        int multiply = (field.size() * HORIZONTAL) / field.get(0).length() + 1;
        for (int i = 0; i < field.size(); i++) {
            field.set(i, field.get(i).repeat(multiply));
        }
        System.out.printf("horizontal: %s, vertical: %s%n", field.get(0).length(), field.size());

        int treesEncountered = 0;
        int horizontal = 0;
        for (String s : field) {
            if (s.charAt(horizontal) == TREE) {
                treesEncountered++;
            }
            horizontal += HORIZONTAL;
        }
        System.out.println("trees encountered: " + treesEncountered);
        // dit is ook mijn eerste foute antwoord (te hoog)
    }
}