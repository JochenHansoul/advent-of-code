package day12;

public enum CardinalDirections {
    NORTH(0), EAST(90), SOUTH(180), WEST(270);

    private final int degrees;

    CardinalDirections(int degrees) {
        this.degrees = degrees;
    }

    public int getDegrees() {
        return degrees;
    }
}
