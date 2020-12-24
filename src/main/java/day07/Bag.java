package day07;

import java.util.HashMap;

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
}
