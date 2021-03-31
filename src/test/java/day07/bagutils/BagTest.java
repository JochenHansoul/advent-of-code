package day07.bagutils;

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
        defaultBagsEmpty.put( new Bag(Pattern.valueOf("DARK"), Color.valueOf("RED")), 1);
        defaultBagsEmpty.put(new Bag(Pattern.valueOf("DARK"), Color.valueOf("BLUE")), 2);
        defaultBagsEmpty.put(new Bag(Pattern.valueOf("DARK"), Color.valueOf("GREEN")), 3);
        defaultBagsEmpty.put(new Bag(Pattern.valueOf("DARK"), Color.valueOf("YELLOW")), 4);
        defaultBagsEmpty.put(new Bag(Pattern.valueOf("DARK"), Color.valueOf("BLACK")), 5);
    }

    @Test
    public void testConstructor() {
        Bag bag = new Bag(Pattern.valueOf("DARK"), Color.valueOf("RED"));
        assertEquals(Pattern.DARK, bag.PATTERN);
        assertEquals(Color.RED, bag.COLOR);
        assertNull(bag.getContent());
    }

    @Test
    public void testSetContentBag() {
        Bag bag = new Bag(Pattern.valueOf("DARK"), Color.valueOf("RED"));
        bag.setContent(defaultBagsEmpty);
        assertEquals(defaultBagsEmpty, bag.getContent());
    }
}
