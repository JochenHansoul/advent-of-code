package day07;

import java.util.HashMap;
import java.util.Objects;

public class Bag {
    public final Colors COLOR;
    public final Patterns PATTERN;
    private HashMap<Bag, Integer> content;

    public Bag(Patterns pattern, Colors color) {
        this(pattern, color, null);
    }

    public Bag(Patterns pattern, Colors color, HashMap<Bag, Integer> bagContent) {
        this.COLOR = color;
        this.PATTERN = pattern;
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
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            Bag bag = (Bag) o;
            return COLOR == bag.COLOR && PATTERN == bag.PATTERN;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(COLOR, PATTERN);
    }

    @Override
    public String toString() {
        if (content != null && content.size() != 0) {
            StringBuilder sb = new StringBuilder();
            for (Bag bag : content.keySet()) {
                sb
                        .append(bag.PATTERN)
                        .append(" ")
                        .append(bag.COLOR)
                        .append(", ");
            }
            return String.format("%s %s {%s}", this.PATTERN, this.COLOR, sb.substring(0, sb.length() - 2));
        } else {
            return String.format("%s %s {}", this.PATTERN, this.COLOR);
        }
    }
}
