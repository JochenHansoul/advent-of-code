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

    @Override
    public String toString() {
        if (content != null && content.size() != 0) {
            StringBuilder sb = new StringBuilder();
            for (Bag bag : content.keySet()) {
                sb.append(content.get(bag)).append(" ");
                sb.append(bag.COLOR).append(", ");
            }
            return String.format("%s {%s}", this.COLOR, sb.substring(0, sb.length() - 2));
        } else {
            return this.COLOR + " {}";
        }
    }
}
