/*
--- Part Two ---

As you finish the last group's customs declaration, you notice that you misread one word in the instructions:
You don't need to identify the questions to which anyone answered "yes"; you need to identify the questions to which
everyone answered "yes"!

Using the same example as above:
abc

a
b
c

ab
ac

a
a
a
a

b

This list represents answers from five groups:
    In the first group, everyone (all 1 person) answered "yes" to 3 questions: a, b, and c.
    In the second group, there is no question to which everyone answered "yes".
    In the third group, everyone answered yes to only 1 question, a. Since some people did not answer "yes" to b or c,
    they don't count.
    In the fourth group, everyone answered yes to only 1 question, a.
    In the fifth group, everyone (all 1 person) answered "yes" to 1 question, b.

In this example, the sum of these counts is 3 + 0 + 1 + 1 + 1 = 6.
For each group, count the number of questions to which everyone answered "yes". What is the sum of those counts?


mogelijke oplossing:
maak een array van 26 integers
voor elk antwoord in een groep verhoog de overkomstige waarde in de array met 1
if c1 == c2 {
    alphabet[c1 - a]++;
}
vergelijk waarden array met aantal groep antwoorden. als gelijk verhoog sumAllAnswers met 1
reset alfabet array
 */

package day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Part2 {

    private static ArrayList<Character> firstAnswer = new ArrayList<>();

    private static int getNumberOfUniqueAnswers(ArrayList<char[]> groupAnswers) {
        // clearing and filling firstAnswer
        firstAnswer.clear();
        for (char c : groupAnswers.get(0)) {
            firstAnswer.add(c);
        }
        // checking answers
        for (char[] currentAnswer : groupAnswers) {
            ArrayList<Character> sameAnswers = new ArrayList<>();
            int i = 0;
            while (i < currentAnswer.length) {
                char character = currentAnswer[i];
                if (firstAnswer.contains(character)) {
                    sameAnswers.add(character);
                }
                i++;
            }
            firstAnswer = sameAnswers;
        }
        return firstAnswer.size();
    }

    public static void main(String[] args) {

        final Path PATH = Paths.get("src/main/resources/day06/answers.txt");

        int sumUniqueGroupAnswers = 0;

        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            String line;
            ArrayList<char[]> groupAnswers = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    sumUniqueGroupAnswers += getNumberOfUniqueAnswers(groupAnswers);
                    groupAnswers.clear(); // I forgot to clear group answer!!!
                } else {
                    groupAnswers.add(line.toCharArray());
                }
            }
            // repeat for last answer is apparently not necessary
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(sumUniqueGroupAnswers);
        // 482: wrong (answer is too low)
        // 483: wrong (answer is too low)
        // 36 (wrong)
        // 3476 (right), I accidentally entered 3467 first
    }
}
