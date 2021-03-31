/*
--- Part Two ---

It's getting pretty expensive to fly these days - not because of ticket prices, but because of the ridiculous number of
bags you need to buy!

Consider again your shiny gold bag and the rules from the above example:
    faded blue bags contain 0 other bags.
    dotted black bags contain 0 other bags.
    vibrant plum bags contain 11 other bags: 5 faded blue bags and 6 dotted black bags.
    dark olive bags contain 7 other bags: 3 faded blue bags and 4 dotted black bags.

So, a single shiny gold bag must contain 1 dark olive bag (and the 7 bags within it) plus 2 vibrant plum bags
(and the 11 bags within each of those): 1 + 1*7 + 2 + 2*11 = 32 bags!

Of course, the actual rules have a small chance of going several levels deeper than this example; be sure to count all
of the bags, even if the nesting becomes topologically impractical!

Here's another example:
shiny gold bags contain 2 dark red bags.
dark red bags contain 2 dark orange bags.
dark orange bags contain 2 dark yellow bags.
dark yellow bags contain 2 dark green bags.
dark green bags contain 2 dark blue bags.
dark blue bags contain 2 dark violet bags.
dark violet bags contain no other bags.

In this example, a single shiny gold bag must contain 126 other bags.

How many individual bags are required inside your single shiny gold bag?
 */

package day07;

import day07.bagutils.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

public class BagApp2 {
    public static void main(String[] args) {
        final Path PATH = Paths.get("src/main/resources/day07/bag_rules.txt");
        final String bagName = "shiny gold";

        try {
            Instant before = Instant.now();
            Bags bags = new Bags(PATH);
            Instant after = Instant.now();
            System.out.printf("Duration milliseconds: %.3s%n", Duration.between(before, after).getNano());
            // 180 - 220

            String[] bagArray = bagName.split(" ");
            Bag bag = bags.getBag(
                    Pattern.valueOf(bagArray[0].toUpperCase()),
                    Color.valueOf(bagArray[1].toUpperCase()));
            System.out.printf("%s bag contains %d bags%n", bag.nameToString(), bag.getAmountOfBags() - 1);
            // 801 (wrong) too low
            // 13265 (wrong) too high
            // 13264 (right) I forgot to subtract the original bag of the answer! only the bags inside had to be counted
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}