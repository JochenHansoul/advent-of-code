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
import java.util.ArrayList;
import java.util.TreeSet;

public class BagsInside {
    public static void main(String[] args) {
        final Path PATH = Paths.get("src/main/resources/day07/bag_rules.txt");
        final String START_BAG = "shiny gold";

        ArrayList<String> bags = new ArrayList<>();
        ArrayList<ArrayList<String>> valuesBagNames = new ArrayList<>();
        ArrayList<ArrayList<Integer>> valuesBagAmounts = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("[.,]", "");
                String[] lineArray = line.split(" ");
                bags.add(lineArray[0] + " " + lineArray[1]);
                ArrayList<String> currentBagValuesNames = new ArrayList<>(); // This arraylist needed to be created again i.s.o. cleared!
                ArrayList<Integer> currentBagValuesAmounts = new ArrayList<>(); // This arraylist needed to be created again i.s.o. cleared!
                int counter = 3;
                while (counter < lineArray.length) {
                    if ((lineArray[counter].equals("bags") || lineArray[counter].equals("bag")) && !lineArray[counter - 1].equals("other")) {
                        currentBagValuesNames.add(lineArray[counter - 2] + " " + lineArray[counter - 1]);
                        currentBagValuesAmounts.add(Integer.parseInt(lineArray[counter - 3]));
                    }
                    counter++;
                }
                valuesBagNames.add(currentBagValuesNames);
                valuesBagAmounts.add(currentBagValuesAmounts);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        ArrayList<ArrayList<String>> allAppliedBags = new ArrayList<>();

        ArrayList<String> currentAppliedBags = new ArrayList<>();
        currentAppliedBags.add(START_BAG);

        ArrayList<String> newBagList = new ArrayList<>();
        ArrayList<String> newAppliedBags = new ArrayList<>();
        while (currentAppliedBags.size() != 0) {
            //System.out.println(currentAppliedBags);
            for (int i = 0; i < bags.size(); i++) { // runs trough all the available bag rules
                // bag: [bag, bag, bag...]
                // checking if [goldBag, ....] is withing the values of the current bag
                for (String currentAppliedBag : currentAppliedBags) {
                    if (valuesBagNames.get(i).contains(currentAppliedBag)) {
                        newBagList.add(bags.get(i));
                        // insert values here
                    }
                }
                if (!newBagList.isEmpty()) {
                    newAppliedBags.add(newBagList.get(0));
                    newBagList = new ArrayList<>();
                }
            }
            allAppliedBags.add(newAppliedBags);
            currentAppliedBags = newAppliedBags;
            newAppliedBags = new ArrayList<>();
        }

        TreeSet<String> ts = new TreeSet<>();
        for (ArrayList<String> list : allAppliedBags) {
            ts.addAll(list);
        }

    }
}
