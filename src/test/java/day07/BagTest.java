package day07;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

// er lijkt geen manier te zijn om een array van waardes of keys die je met een indexwaarde eruit kunt halen

public class BagTest {
    private HashMap<Bag, Integer> defaultBagsEmpty = new HashMap<>();
    private HashMap<Bag, Integer> defaultBagsFull = new HashMap<>();

    @Before
    public void init() {
        defaultBagsEmpty.put( new Bag("dark red"), 1);
        defaultBagsEmpty.put(new Bag("dark blue"), 2);
        defaultBagsEmpty.put(new Bag("dark green"), 3);
        defaultBagsEmpty.put(new Bag("dark z"), 4);
        defaultBagsEmpty.put(new Bag("dark grey"), 5);

        defaultBagsFull.put(new Bag("bright red", defaultBagsEmpty), 1);
        defaultBagsFull.put(new Bag("bright blue", defaultBagsEmpty), 2);
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
        Bag darkBlackBag = new Bag("dark black", defaultBagsEmpty);
        assertEquals("dark black", darkBlackBag.COLOR);
    }

    @Test
    public void testLengthBagContent() {
        Bag darkBlackBag = new Bag("dark black", defaultBagsEmpty);
        assertEquals(defaultBagsEmpty.size(), darkBlackBag.getContent().size()); // amount of bags are the same
    }

    @Test
    public void testBagContentAmountsAreRight() {
        Bag darkBlackBag = new Bag("dark black", defaultBagsEmpty);
        HashMap<Bag, Integer> bagContent = darkBlackBag.getContent();
        for (Bag bag : bagContent.keySet()) {
            assertEquals(defaultBagsEmpty.get(bag), bagContent.get(bag)); // amount of certain bag are the same
        }
    }

    @Test
    public void testBagWithEmptyChildrenIsEmpty() {
        Bag darkBlackBag = new Bag("dark black", defaultBagsEmpty);
        for (Bag bag : darkBlackBag.getContent().keySet()) {
            assertNull(bag.getContent());
        }
    }

    @Test
    public void testBagWithFilledChildrenAreFull() {
        Bag darkBlackBag = new Bag("dark black", defaultBagsFull);
        for (Bag bag : darkBlackBag.getContent().keySet()) {
            assertNotNull(bag.getContent());
        }
    }

    @Test
    public void testSetContentBag() {
        Bag darkBlackBag = new Bag("dark black");
        darkBlackBag.setContent(defaultBagsEmpty);
        assertEquals(defaultBagsEmpty, darkBlackBag.getContent());
    }

    @Test
    public void testSettingContentOfBagThatAlreadyHasContent() {
        Bag darkBlackBag = new Bag("dark black", defaultBagsEmpty);
        darkBlackBag.setContent(defaultBagsFull);
        assertEquals(defaultBagsEmpty, darkBlackBag.getContent());
    }
}
