package app.model;

import javafx.beans.property.*;

public class Product {
    private StringProperty name;
    private IntegerProperty amount;
    private ProductType type;
    private BooleanProperty inStock;

    public Product() {
        this(null, 0, null);
        inStock = new SimpleBooleanProperty(false);
    }

    public Product(String name, int amount, ProductType type) {
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleIntegerProperty(amount);
        this.type = type;
        if (amount > 0) {
            inStock = new SimpleBooleanProperty(true);
        } else {
            inStock = new SimpleBooleanProperty(false);
        }
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

    public int getAmount() {
        return amount.get();
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public boolean isInStock() {
        return inStock.get();
    }

    public void setInStock(boolean inStock) {
        this.inStock.set(inStock);
    }

    public BooleanProperty inStockProperty() {
        return inStock;
    }

    public StringProperty typeProperty() {
        return new SimpleStringProperty(type.toString());
    }
}
