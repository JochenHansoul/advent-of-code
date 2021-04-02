package day07.bagutils;

import java.util.HashMap;
import java.util.Objects;

public class Bag {
    public final Color COLOR;
    public final Pattern PATTERN;
    protected HashMap<Bag, Integer> content;

    public Bag(Pattern pattern, Color color) {
        if (pattern != null && color != null) {
            this.COLOR = color;
            this.PATTERN = pattern;
            this.content = new HashMap<>();
        } else {
            throw new IllegalArgumentException("pattern and color may not be null");
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
            return COLOR.equals(bag.COLOR) && PATTERN.equals(bag.PATTERN);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(COLOR, PATTERN);
    }

    public int getAmountOfBags() {
        int counter = 1;
        if (content != null) {
            for (Bag child : content.keySet()) {
                counter += content.get(child) * child.getAmountOfBags();
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        return String.format("%s {%s}", nameToString(), contentToString());
    }

    public String nameToString() {
        return String.format("%s %s", this.PATTERN, this.COLOR)
                .toLowerCase();
    }

    public String contentToString() {
        if (content != null && content.size() != 0) {
            StringBuilder sb = new StringBuilder();
            for (Bag bag : content.keySet()) {
                sb
                        .append(content.get(bag))
                        .append(" ")
                        .append(bag.PATTERN)
                        .append(" ")
                        .append(bag.COLOR)
                        .append(", ");
            }
            return sb.substring(0, sb.length() - 2).toLowerCase();
        } else {
            return "";
        }
    }
}
