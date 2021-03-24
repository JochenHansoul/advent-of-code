package day07;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BagsTryout {

    public static void main(String[] args) {
        HashSet<Bag> bags = new HashSet<>();

        // adding bags with the same color doesn't increase the amount of bags
        HashMap<Bag, Integer> childBags = new HashMap<>();
        /*childBags.put(new Bag("child dark red"), 10);
        Bag darkRedBag = new Bag("dark red", childBags);
        bags.add(darkRedBag);
        bags.add(new Bag("dark green"));
        bags.add(new Bag("dark blue"));*/ // 3 bags added

        // these 4 bags added are different to the bags that already have been added!!!
        /*HashMap<Bag, Integer> bagsHashMap1 = new HashMap<>();
        bagsHashMap1.put( new Bag("dark red"), 1);
        bagsHashMap1.put(new Bag("dark green"), 2);
        bagsHashMap1.put(new Bag("dark blue"), 2);
        bags.add(new Bag("bright red", bagsHashMap1));*/

        // adding bags content to a bag that is alreasy added (doesn't work)
        /*Bag alreadyAddedBag = new Bag("dark red");
        if (bags.contains(alreadyAddedBag)) {
            alreadyAddedBag = getAlreadyAddedBag(bags, alreadyAddedBag);
        }
        childBags = new HashMap<>();
        childBags.put( alreadyAddedBag, 1);
        bags.add(new Bag("bright red", childBags));

        // printing
        System.out.println("amount : " + bags.size());
        printBagsAndChildren(bags);*/
    }

    private static Bag getAlreadyAddedBag(HashSet<Bag> bags, Bag alreadyAddedBag) {
        Bag bag = null;
        Bag[] arrayBags = bags.toArray(new Bag[0]);
        int i = 0;
        while (i < arrayBags.length) {
            if (arrayBags[i].COLOR.equals(alreadyAddedBag.COLOR)) {
                bag = arrayBags[i];
                i = arrayBags.length;
            }
            i++;
        }
        return bag; // needs to be returned
    }

    private static void printBagsAndChildren(Set<Bag> bags) {
        for (Bag bag : bags) {
            System.out.print(bag.COLOR + " {");
            if (bag.getContent() != null) {
                printBagsAndChildren(bag.getContent().keySet());
            }
            System.out.print("} ");
        }
    }
}
