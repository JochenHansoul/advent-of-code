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

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BagApp2 {
    public static void main(String[] args) {
        final Path PATH = Paths.get("src/main/resources/day07/bag_rules.txt");

        HashSet<Bag> bags = new HashSet<>();
        Bag startBag = new Bag(Patterns.SHINY, Colors.GOLD);
        bags.add(startBag);

        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("[.,]", "");
                String[] lineParts = line.split(" ");

                // current bag
                Bag currentBag = new Bag(
                        Patterns.valueOf(lineParts[0].toUpperCase()),
                        Colors.valueOf(lineParts[1].toUpperCase()));
                currentBag = addBagToListOrGetAlreadyAddedBag(bags, currentBag);

                // current bag content
                HashMap<Bag, Integer> currentBagContent = new HashMap<>(); // don't forget to create a new object!
                if (!lineParts[4].equals("no")) {
                    int counter = 4;
                    while (counter < lineParts.length) {
                        if ((lineParts[counter].equals("bags") || lineParts[counter].equals("bag"))) {
                            Bag childBag = new Bag(
                                    Patterns.valueOf(lineParts[counter - 2].toUpperCase()),
                                    Colors.valueOf(lineParts[counter - 1].toUpperCase()));
                            childBag = addBagToListOrGetAlreadyAddedBag(bags, childBag);
                            currentBagContent.put(childBag, Integer.parseInt(lineParts[counter - 3]));
                        }
                        counter++;
                    }
                }
                currentBag.setContent(currentBagContent);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int amountOfBags = getAmountOfBags(startBag) - 1;
        System.out.println("amount of child bags: " + amountOfBags);
        // 801 (wrong) too low
        // 13265 (wrong) too high
        // 13264 (right) I forgot to subtract the original bag of the answer! only the bags inside had to be counted
    }

    private static int getAmountOfBags(Bag bag) {
        HashMap<Bag, Integer> content = bag.getContent();
        int counter = 1;
        for (Bag child : content.keySet()) {
            counter += content.get(child) * getAmountOfBags(child);
        }
        return counter;
    }

    private static Bag addBagToListOrGetAlreadyAddedBag(HashSet<Bag> bags, Bag bag) {
        if (bags.contains(bag)) {
            bag = getOriginalBag(bags, bag);
        } else {
            bags.add(bag);
        }
        return bag;
    }

    private static Bag getOriginalBag(HashSet<Bag> bags, Bag bag) {
        Bag[] arrayBags = bags.toArray(new Bag[0]);
        int i = 0;
        boolean found = false;
        while (!found) {
            if (arrayBags[i].equals(bag)) {
                bag = arrayBags[i];
                found = true;
            } else {
                i++;
            }
        }
        return bag;
    }
}