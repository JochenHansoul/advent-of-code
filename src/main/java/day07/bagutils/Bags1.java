package day07.bagutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Bags1 {
    public final Bag[] BAGS;

    public Bags1(Path path) {
        ArrayList<Bag> bagList = new ArrayList<>();
        ArrayList<String[]> arrayLines = new ArrayList<>();

        // adding bags to bag rules (but not yet content)
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("[.,]", "");
                String[] lineParts = line.split(" ");
                bagList.add(new Bag(
                        Pattern.valueOf(lineParts[0].toUpperCase()),
                        Color.valueOf(lineParts[1].toUpperCase())));
                arrayLines.add(lineParts);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // adding child content
        Bag[] bagArray = bagList.toArray(bagList.toArray(new Bag[0]));
        for (int i = 0; i < bagList.size(); i++) {
            Bag bag = bagArray[i];
            String[] lineParts = arrayLines.get(i);
            HashMap<Bag, Integer> currentBagContent = new HashMap<>(); // don't forget to create a new object!
            if (!lineParts[4].equals("no")) {
                for (int j = 4; j < lineParts.length; j++) {
                    if ((lineParts[j].equals("bags") || lineParts[j].equals("bag"))) {
                        Bag childBag = null;
                        Pattern pattern = Pattern.valueOf(lineParts[j - 2].toUpperCase());
                        Color color = Color.valueOf(lineParts[j - 1].toUpperCase());
                        int counter = 0;
                        while (counter < bagArray.length) {
                            if (bagArray[counter].PATTERN.equals(pattern) && bagArray[counter].COLOR.equals(color)) {
                                childBag = bagArray[counter];
                                counter = bagArray.length;
                            } else {
                                counter++;
                            }
                        }
                        currentBagContent.put(childBag, Integer.parseInt(lineParts[j - 3]));
                    }
                }
            }
            bag.setContent(currentBagContent);
        }
        this.BAGS = bagList.toArray(new Bag[0]);
    }

    public Bag getBag(Pattern pattern, Color color) {
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
}
