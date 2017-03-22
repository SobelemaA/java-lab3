package app.model;

import javafx.beans.property.*;

public class Product {
    private final StringProperty name;
    private final IntegerProperty amount;
    private ObjectProperty<ProductType> type;
    private BooleanProperty inStock;
    private final BooleanProperty freeShipping;

    public Product() {
        this("", 0, null, false);
        inStock = new SimpleBooleanProperty(false);
    }

    public Product(String name, int amount, ObjectProperty<ProductType> type, Boolean freeShipping) {
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleIntegerProperty(amount);
        this.type = type;
        if (amount > 0) {
            inStock = new SimpleBooleanProperty(true);
        } else {
            inStock = new SimpleBooleanProperty(false);
        }
        this.freeShipping = new SimpleBooleanProperty(freeShipping);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty amountProperty() {
        return new SimpleIntegerProperty(amount.getValue());
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    private boolean isInStock() {
        return inStock.get();

    }

    public String isInStockToString() {
        if (isInStock()) {
            return "Available";
        } else {
            return "Not available";
        }
    }

    public void checkAvailability() {
        if (amount.asObject().getValue() > 0) {
            inStock.setValue(true);
        } else {
            inStock.setValue(false);
        }
    }

    public String getType() {
        return this.type.get().toString();
    }

    public void setType(ProductType type) {
        this.type = new SimpleObjectProperty<>(type);
    }

    public ProductType getProductType() {
        if (type != null) {
            return type.get();
        }
        return null;
    }

    public boolean isFreeShipping() {
        return freeShipping.get();
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping.set(freeShipping);
    }

    public String isFreeShippingToString() {
        if (freeShipping.getValue()) {
            return "Yes";
        } else {
            return "No";
        }
    }
}
