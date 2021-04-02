package day07.bagutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Bags1 {
    public final Bag[] BAGS;

    public Bags1(Path path) throws IOException {
        ArrayList<String> childBagLines = new ArrayList<>();

        // adding bags to bag rule list
        ArrayList<Bag> bagList = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parentAndChildren = line.split(" bags contain ");
                String[] parentBag = parentAndChildren[0]
                        .split(" ");
                bagList.add(new Bag(
                        Pattern.valueOf(parentBag[0].toUpperCase()),
                        Color.valueOf(parentBag[1].toUpperCase())));
                childBagLines.add(parentAndChildren[1]);
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        this.BAGS = bagList.toArray(bagList.toArray(new Bag[0]));

        // adding child content to the bags
        for (int i = 0; i < this.BAGS.length; i++) {
            if (!childBagLines.get(i).equals("no other bags.")) {
                Bag bag = this.BAGS[i];
                String[] lineParts = childBagLines.get(i)
                        .substring(0, childBagLines.get(i).length() - 1) // removing last "."
                        .replaceAll(" bag[s]", "")
                        .split(", ");
                HashMap<Bag, Integer> currentBagContent = new HashMap<>(); // don't forget to create a new object!
                for (String linePart : lineParts) {
                    String[] lineArray = linePart.split(" ");
                    Bag childBag = getBag(
                            Pattern.valueOf(lineArray[1].toUpperCase()),
                            Color.valueOf(lineArray[2].toUpperCase()));
                    currentBagContent.put(childBag, Integer.parseInt(lineArray[0]));
                }
                bag.setContent(currentBagContent);
            }
        }
    }

    private Bag getBag(Pattern pattern, Color color) {
        Bag bag = null;
        int counter = 0;
        boolean found = false;
        while (!found && counter < this.BAGS.length) {
            if (this.BAGS[counter].PATTERN.equals(pattern) && this.BAGS[counter].COLOR.equals(color)) {
                bag = this.BAGS[counter];
                found = true;
            } else {
                counter++;
            }
        }
        return bag;
    }

    public int amountOfBagsThatCanCarry(Pattern pattern, Color color) {
        Bag startBag = getBag(pattern, color);
        HashSet<Bag> uniqueBags = new HashSet<>();
        HashSet<Bag> checkedBags = new HashSet<>();
        checkedBags.add(startBag);
        do {
            HashSet<Bag> nextCheckedBags = new HashSet<>();
            for (Bag bag : this.BAGS) {
                for (Bag checkedBag : checkedBags) {
                    HashMap<Bag, Integer> content = bag.getContent();
                    if (content.containsKey(checkedBag)) {
                        nextCheckedBags.add(bag);
                        uniqueBags.add(bag);
                    }
                }
            }
            checkedBags = nextCheckedBags;
        } while (checkedBags.size() != 0);
        return uniqueBags.size();
    }
}
