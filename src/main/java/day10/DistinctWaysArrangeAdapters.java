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
import java.math.BigInteger;
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
        System.out.println(adapters);

        long possibleWays = 1;
        BigInteger bigInteger = new BigInteger("1");
        int joltage = 0;
        int i = 0;
        while (i < adapters.size() - 3) {
            int firstAdapter = adapters.get(i);
            int secondAdapter = adapters.get(i + 1);
            int thirdAdapter = adapters.get(i + 2);

            if (firstAdapter == joltage + 1) {
                // first is +1
                if (secondAdapter == joltage + 2) {
                    if (thirdAdapter == joltage + 3) {
                        possibleWays *= 4;
                        bigInteger = bigInteger.multiply(new BigInteger("4"));
                        joltage += 3;
                        i += 3;
                    } else {
                        possibleWays *= 2;
                        bigInteger = bigInteger.multiply(new BigInteger("2"));
                        joltage += 2;
                        i += 2;
                    }
                } else if (secondAdapter == joltage + 3) {
                    possibleWays *= 2;
                    bigInteger = bigInteger.multiply(new BigInteger("2"));
                    joltage += 3;
                    i++;
                } else {
                    joltage++;
                    i++;
                }
            } else if (firstAdapter == joltage + 2) {
                // first is +2
                if (secondAdapter == 3) {
                    possibleWays *= 2;
                    bigInteger = bigInteger.multiply(new BigInteger("2"));
                    joltage += 3;
                    i += 2;
                } else {
                    joltage += 2;
                    i++;
                }
            } else {
                // first is +3
                joltage += 3;
                i++;
            }
        }

        System.out.println("amount of possible ways: " + possibleWays);
        System.out.println(bigInteger);
        // 1125899906842624 (too high)
    }
}
