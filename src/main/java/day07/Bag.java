package day07;

public class Bag {
    public final String COLOR;
    private Bag[] childColors;
    private int[] childAmounts;

    public Bag(String color) {
        this(color, null, null);
    }

    public Bag(String color, Bag[] containsColors, int[] containsAmounts) {
        this.COLOR = color;
        this.childColors = containsColors;
        this.childAmounts = containsAmounts;
    }

    public void setContent(Bag[] containsColors, int[] containsAmounts) {
        this.childColors = containsColors;
        this.childAmounts = containsAmounts;
    }

    public Bag[] childColors() {
        return childColors;
    }

    public int[] childAmounts() {
        return childAmounts;
    }
}
