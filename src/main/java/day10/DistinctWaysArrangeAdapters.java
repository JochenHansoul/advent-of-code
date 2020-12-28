/*
--- Part Two ---

To completely determine whether you have enough adapters, you'll need to figure out how many different ways they can be
arranged. Every arrangement needs to connect the charging outlet to your device. The previous rules about when adapters
can successfully connect still apply.

The first example above (the one that starts with 16, 10, 15) supports the following arrangements:
(0), 1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19, (22)
(0), 1, 4, 5, 6, 7, 10, 12, 15, 16, 19, (22)
(0), 1, 4, 5, 7, 10, 11, 12, 15, 16, 19, (22)
(0), 1, 4, 5, 7, 10, 12, 15, 16, 19, (22)
(0), 1, 4, 6, 7, 10, 11, 12, 15, 16, 19, (22)
(0), 1, 4, 6, 7, 10, 12, 15, 16, 19, (22)
(0), 1, 4, 7, 10, 11, 12, 15, 16, 19, (22)
(0), 1, 4, 7, 10, 12, 15, 16, 19, (22)

(The charging outlet and your device's built-in adapter are shown in parentheses.) Given the adapters from the first
example, the total number of arrangements that connect the charging outlet to your device is 8.

The second example above (the one that starts with 28, 33, 18) has many arrangements. Here are a few:
(0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
32, 33, 34, 35, 38, 39, 42, 45, 46, 47, 48, 49, (52)

(0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
32, 33, 34, 35, 38, 39, 42, 45, 46, 47, 49, (52)

(0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
32, 33, 34, 35, 38, 39, 42, 45, 46, 48, 49, (52)

(0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
32, 33, 34, 35, 38, 39, 42, 45, 46, 49, (52)

(0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
32, 33, 34, 35, 38, 39, 42, 45, 47, 48, 49, (52)

(0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
46, 48, 49, (52)

(0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
46, 49, (52)

(0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
47, 48, 49, (52)

(0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
47, 49, (52)

(0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
48, 49, (52)

In total, this set of adapters can connect the charging outlet to your device in 19208 distinct arrangements.

You glance back down at your bag and try to remember why you brought so many adapters; there must be more than a
trillion valid ways to arrange them! Surely, there must be an efficient way to count the arrangements.

What is the total number of distinct ways you can arrange the adapters to connect the charging outlet to your device?
 */

package day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class DistinctWaysArrangeAdapters {
    public static void main(String[] args) {
        final Path PATH = Paths.get("src/main/resources/day10/example.txt");

        ArrayList<Integer> adapters = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                adapters.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Collections.sort(adapters);

        long possibleWays = 1;
        int joltage = 0;
        int i = 0;
        while (i < adapters.size()) {
            // 3 (no other possibilities)
            // 2 (no or one other possibilities)
            // 1 (no, one or two other possibilities)
            if (adapters.get(i) == joltage + 3
            || adapters.get(i) == joltage + 2
            && adapters.get(i + 1) > joltage + 3
            || adapters.get(i) == joltage + 1
            && adapters.get(i + 1) > joltage + 3) {
                // first 3 or first 2 and  second 4 or first 1 and second 4 ==> no possibilities
                i++;
            } else if (adapters.get(i) == joltage + 2
            || adapters.get(i) == joltage + 1
            && adapters.get(i + 1) == joltage + 3) {
                // first 2 and second 3 or first 1 and second 3 ==> two possibilities
                possibleWays *= 2;
                i += 2;
            } else {
                // first 1 and second 2 and third 3 ==> four possibilities
                possibleWays *= 4;
                i += 3;
            }
            System.out.println(possibleWays);
            joltage += 3;
        }

        System.out.println("amount of possible ways: " + possibleWays);
        // 1125899906842624 (too high)
    }
}
