package day07;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BagTest {
    private Bag[] bagColors;
    private int[] bagAmounts;

    @Before
    public void init() {
        bagColors = new Bag[]{
                new Bag("bright yellow"),
                new Bag("bright blue"),
                new Bag("bright pink"),
                new Bag("bright orange")
        };
        bagAmounts = new int[]{
                1,
                5,
                2,
                7
        };
    }

    @Test
    public void testConstructorWithoutParameters() {
        Bag bag = new Bag("dark red");
        assertEquals("dark red", bag.COLOR);
        assertNull(bag.childColors());
        assertNull(bag.childAmounts());
    }

    @Test
    public void testConstructorWithParameters() {
        Bag bag = new Bag("dark red");
        assertEquals("dark red", bag.COLOR);
        assertNull(bag.childColors());
        assertNull(bag.childAmounts());
    }

    @Test
    public void testConstructorWithChildBags() {
        Bag bag = new Bag("dark black", bagColors, bagAmounts);
        assertEquals("dark black", bag.COLOR);
        assertArrayEquals(bagColors, bag.childColors());
        assertArrayEquals(bagAmounts, bag.childAmounts());
    }
}
