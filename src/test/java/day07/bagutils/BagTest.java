package day07.bagutils;

import day07.bagutils.Bag;
import day07.bagutils.Colors;
import day07.bagutils.Patterns;
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
        defaultBagsEmpty.put( new Bag(Patterns.valueOf("DARK"), Colors.valueOf("RED")), 1);
        defaultBagsEmpty.put(new Bag(Patterns.valueOf("DARK"), Colors.valueOf("BLUE")), 2);
        defaultBagsEmpty.put(new Bag(Patterns.valueOf("DARK"), Colors.valueOf("GREEN")), 3);
        defaultBagsEmpty.put(new Bag(Patterns.valueOf("DARK"), Colors.valueOf("YELLOW")), 4);
        defaultBagsEmpty.put(new Bag(Patterns.valueOf("DARK"), Colors.valueOf("BLACK")), 5);

        defaultBagsFull.put(new Bag(Patterns.valueOf("BRIGHT"), Colors.valueOf("RED"), defaultBagsEmpty), 1);
        defaultBagsFull.put(new Bag(Patterns.valueOf("BRIGHT"), Colors.valueOf("BLUE"), defaultBagsEmpty), 2);
    }

    @Test
    public void testConstructor() {
        Bag bag = new Bag(Patterns.valueOf("DARK"), Colors.valueOf("RED"));
        assertEquals(Patterns.DARK, bag.PATTERN);
        assertEquals(Colors.RED, bag.COLOR);
        assertNull(bag.getContent());
    }

    @Test
    public void testConstructorWithChildBags() {
        Bag bag = new Bag(Patterns.valueOf("DARK"), Colors.valueOf("RED"), defaultBagsEmpty);
        assertEquals(Patterns.DARK, bag.PATTERN);
        assertEquals(Colors.RED, bag.COLOR);
        assertEquals(defaultBagsEmpty, bag.getContent());
    }

    @Test
    public void testSetContentBag() {
        Bag bag = new Bag(Patterns.valueOf("DARK"), Colors.valueOf("RED"));
        bag.setContent(defaultBagsEmpty);
        assertEquals(defaultBagsEmpty, bag.getContent());
    }

    @Test
    public void testSettingContentOfBagThatAlreadyHasContent() {
        Bag bag = new Bag(Patterns.valueOf("DARK"), Colors.valueOf("RED"), defaultBagsEmpty);
        bag.setContent(defaultBagsEmpty);
        assertEquals(defaultBagsEmpty, bag.getContent());
    }
}
