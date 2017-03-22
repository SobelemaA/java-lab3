package app.model;

public enum ProductType {
    FOOD("Food"),
    BEER("Beer"),
    WINE("Wine"),
    CIGARETTE("Cigarette");

    private final String label;

    ProductType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
