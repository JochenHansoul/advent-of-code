package day07;

import java.util.HashMap;
import java.util.Objects;

public class Bag {
    public final String COLOR;
    private HashMap<Bag, Integer> content;

    public Bag(String color) {
        this(color, null);
    }

    public Bag(String color, HashMap<Bag, Integer> bagContent) {
        this.COLOR = color;
        this.content = bagContent;
    }

    public void setContent(HashMap<Bag, Integer> bagContent) {
        if (this.content == null) {
            this.content = bagContent;
        }
    }

    public HashMap<Bag, Integer> getContent() {
        return this.content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bag bag = (Bag) o;
        return Objects.equals(COLOR, bag.COLOR);
    }

    @Override
    public int hashCode() {
        return Objects.hash(COLOR);
    }
}
