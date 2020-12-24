package day07;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class BagTest {
    private HashMap<Bag, Integer> bags = new HashMap<>();

    @Before
    public void init() {
        bags.put( new Bag("dark red"), 1);
        bags.put(new Bag("dark blue"), 2);
        bags.put(new Bag("dark green"), 3);
        bags.put(new Bag("dark z"), 4);
        bags.put(new Bag("dark grey"), 5);
    }

    @Test
    public void testConstructorWithoutParameters() {
        Bag bag = new Bag("dark red");
        assertEquals("dark red", bag.COLOR);
        assertNull(bag.getContent());
    }

    @Test
    public void testConstructorWithParameters() {
        Bag bag = new Bag("dark red");
        assertEquals("dark red", bag.COLOR);
    }

    @Test
    public void testConstructorWithChildBags() {
        Bag bag1 = new Bag("dark black", bags);
        assertEquals("dark black", bag1.COLOR);

        // must find all bags and it's amount
        //bags.containsKey()
        for (Bag bag : bags.keySet()) {
            System.out.println(bags.get(bag) + " " + bag.COLOR);
        }

        /*
        // er lijkt geen manier te zijn om een array van waardes of keys die je met een indexwaarde eruit kunt halen
        Set<Bag> bagKeys = bags.keySet();
        Collection<Integer> bagValues = bags.values();
        Integer[] bvArray = (Integer[]) bagValues.toArray();
        */
    }

    // test setting
    // test setting already set


    /*@Test
    public void testBagWithEmptyChildrenAreEmpty() {
        Bag bag = new Bag("dark yellow", bags);
        for (int i = 0; i < bag.getContent().size(); i++) {
            assertNull(bag.getContent().get("dark red"));
        }
    }*/
}
