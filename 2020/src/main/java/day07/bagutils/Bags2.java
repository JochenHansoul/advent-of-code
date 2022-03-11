package day07.bagutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

public class Bags2 {
    HashSet<Bag> bags = new HashSet<>();

    public Bags2(Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parentAndChildren = line.split(" bags contain ");
                String[] parentBag = parentAndChildren[0]
                        .split(" ");
                // current bag
                Bag currentBag = addOrGetBag(
                        Pattern.valueOf(parentBag[0].toUpperCase()),
                        Color.valueOf(parentBag[1].toUpperCase()));
                // child bags
                if (!parentAndChildren[1].equals("no other bags.")) {
                    String[] childBags = parentAndChildren[1]
                            .substring(0, parentAndChildren[1].length() - 1) // removing last "."
                            .replaceAll(" bag[s]", "")
                            .split(", ");
                    for (String childBagString : childBags) {
                        String[] childBagArray = childBagString.split(" ");
                        Bag childBag = addOrGetBag(
                                Pattern.valueOf(childBagArray[1].toUpperCase()),
                                Color.valueOf(childBagArray[2].toUpperCase()));
                        currentBag.content.put(childBag, Integer.parseInt(childBagArray[0]));
                    }
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public Bag getBag(Pattern pattern, Color color) {
        return getOriginalBag(new Bag(pattern, color));
    }

    private Bag addOrGetBag(Pattern pattern, Color color) {
        Bag bag = new Bag(pattern, color);
        if (this.bags.contains(bag)) {
            bag = getOriginalBag(bag);
        } else {
            this.bags.add(bag);
        }
        return bag;
    }

    private Bag getOriginalBag(Bag bag) {
        Bag[] arrayBags = this.bags.toArray(new Bag[0]);
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
