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

    public Bags1(Path path) throws IOException, BagNotFound {
        List<int[]> amounts = new ArrayList<>();
        List<Pattern[]> patterns = new ArrayList<>();
        List<Color[]> colors = new ArrayList<>();

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

                // child bags Filtering
                if (parentAndChildren[1].equals("no other bags.")) {
                    amounts.add(new int[0]);
                    patterns.add(new Pattern[0]);
                    colors.add(new Color[0]);
                } else {
                    String[] lineParts = parentAndChildren[1]
                            .substring(0, parentAndChildren[1].length() - 1) // removing last "."
                            .replaceAll(" bag[s]", "")
                            .split(", ");
                    int length = lineParts.length;
                    int[] amountsBag = new int[length];
                    Pattern[] patternBag = new Pattern[length];
                    Color[] colorsBags = new Color[length];
                    for (int i = 0; i < length; i++) {
                        String[] lineArray = lineParts[i].split(" ");
                        amountsBag[i] = Integer.parseInt(lineArray[0]);
                        patternBag[i] = Pattern.valueOf(lineArray[1].toUpperCase());
                        colorsBags[i] = Color.valueOf(lineArray[2].toUpperCase());
                    }
                    amounts.add(amountsBag);
                    patterns.add(patternBag);
                    colors.add(colorsBags);
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        this.BAGS = bagList.toArray(bagList.toArray(new Bag[0]));
        addChildContent(amounts, patterns, colors);
    }

    public int amountOfBagsThatCanCarry(Pattern pattern, Color color) throws BagNotFound {
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

    private void addChildContent(
            List<int[]> amounts,
            List<Pattern[]> patterns,
            List<Color[]> colors) throws BagNotFound {
        for (int i = 0; i < this.BAGS.length; i++) {
            HashMap<Bag, Integer> currentBagContent = new HashMap<>(); // don't forget to create a new object!
            for (int j = 0; j < amounts.get(i).length; j++) {
                currentBagContent.put(
                        getBag(patterns.get(i)[j], colors.get(i)[j]),
                        amounts.get(i)[j]);
            }
            this.BAGS[i].content = currentBagContent;
        }
    }

    private Bag getBag(Pattern pattern, Color color) throws BagNotFound {
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
        if (found) {
            return bag;
        } else {
            throw new BagNotFound(String.format("%s %s bag not found", pattern, color));
        }
    }
}
