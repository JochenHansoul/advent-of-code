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
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class BagApp2 {
    public static void main(String[] args) {
        final Path PATH = Paths.get("src/main/resources/day07/bag_rules.txt");
        final String bag = "shiny gold";

        Instant before = Instant.now();
        HashSet<Bag> bags = loadBags(PATH);
        Instant after = Instant.now();
        System.out.printf("Duration milliseconds: %.3s%n", Duration.between(before, after).getNano());
        // 180 - 220

        String[] bagArray = bag.split(" ");
        Bag startBag = addBagToListOrGetAlreadyAddedBag(
                bags,
                new Bag(Patterns.valueOf(bagArray[0].toUpperCase()), Colors.valueOf(bagArray[1].toUpperCase())));
        System.out.printf("amount of child bags: %d", getAmountOfBags(startBag) - 1);
        // 801 (wrong) too low
        // 13265 (wrong) too high
        // 13264 (right) I forgot to subtract the original bag of the answer! only the bags inside had to be counted
    }

    private static HashSet<Bag> loadBags(Path path) {
        HashSet<Bag> bags = null;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            bags = new HashSet<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parentAndChildren = line.split(" contain ");
                String[] parentBag = parentAndChildren[0]
                        .substring(0, parentAndChildren[0].length() - 5)
                        .split(" ");
                // current bag
                Bag currentBag = new Bag(
                        Patterns.valueOf(parentBag[0].toUpperCase()),
                        Colors.valueOf(parentBag[1].toUpperCase()));
                currentBag = addBagToListOrGetAlreadyAddedBag(bags, currentBag);
                // child bags
                if (parentAndChildren[1].equals("no other bags.")) {
                    currentBag.setContent(new HashMap<>());
                } else {
                    HashMap<Bag, Integer> currentBagContent = new HashMap<>(); // don't forget to create a new object!
                    String[] childBags = parentAndChildren[1]
                            .substring(0, parentAndChildren[1].length() - 2) // removing last "."
                            .replaceAll(" bag[s]", "")
                            .split(", ");
                    for (String childBagString : childBags) {
                        String[] childBagArray = childBagString.split(" ");
                        Bag childBag = new Bag(
                                Patterns.valueOf(childBagArray[1].toUpperCase()),
                                Colors.valueOf(childBagArray[2].toUpperCase()));
                        childBag = addBagToListOrGetAlreadyAddedBag(bags, childBag);
                        currentBagContent.put(childBag, Integer.parseInt(childBagArray[0]));
                    }
                    currentBag.setContent(currentBagContent);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bags;
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