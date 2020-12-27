/*
--- Part Two ---

The final step in breaking the XMAS encryption relies on the invalid number you just found: you must find a contiguous
set of at least two numbers in your list which sum to the invalid number from step 1.

Again consider the above example:
35
20
15
25
47
40
62
55
65
95
102
117
150
182
127
219
299
277
309
576

In this list, adding up all of the numbers from 15 through 40 produces the invalid number from step 1, 127.
(Of course, the contiguous set of numbers in your actual list might be much longer.)

To find the encryption weakness, add together the smallest and largest number in this contiguous range; in this example,
these are 15 and 47, producing 62.

What is the encryption weakness in your XMAS-encrypted list of numbers?
 */

package day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FindingContiguousNumbers {
    public static void main(String[] args) {
        final Path PATH = Paths.get("src/main/resources/day09/numbers.txt");
        final int PREAMBLE = 25; // don't forget to change!!!!!!!!!!!

        ArrayList<Long> numbers = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Long.parseLong(line));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int index = PREAMBLE;
        boolean numberIsCorrect = true;
        while (index < numbers.size() && numberIsCorrect) {
            numberIsCorrect = false;
            long number = numbers.get(index);
            // checking if number is correct
            int firstNumber = index - PREAMBLE;
            while (firstNumber < index - 1) {
                int secondNumber = firstNumber + 1;
                while (secondNumber < index) {
                    if (number == numbers.get(firstNumber) + numbers.get(secondNumber)) {
                        numberIsCorrect = true;
                        firstNumber = index;
                        secondNumber = index;
                    }
                    secondNumber++;
                }
                firstNumber++;
            }
            index++;
        }

        System.out.println("wrong number is: " + numbers.get(index - 1));

    }
}
