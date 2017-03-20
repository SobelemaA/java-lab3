package app.model;

import javafx.beans.property.*;

public class Product {
    private StringProperty name;
    private IntegerProperty amount;
    private ObjectProperty<ProductType> type;
    private BooleanProperty inStock;
    private BooleanProperty freeShipping;

    public Product() {
        this(null, 0, null);
        inStock = new SimpleBooleanProperty(false);
        freeShipping = new SimpleBooleanProperty(false);
    }

    public Product(String name, int amount, ObjectProperty<ProductType> type) {
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleIntegerProperty(amount);
        this.type = type;
        if (amount > 0) {
            inStock = new SimpleBooleanProperty(true);
        } else {
            inStock = new SimpleBooleanProperty(false);
        }
        freeShipping = new SimpleBooleanProperty(false);
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

    public IntegerProperty getAmount() {
        return new SimpleIntegerProperty(amount.getValue());
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public boolean isInStock() {
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

    public void setType(ObjectProperty<ProductType> type) {
        this.type = type;
    }

    public StringProperty typeProperty() {
        return new SimpleStringProperty(type.toString());
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping.set(freeShipping);
    }
    public String isFreeShipToString(){
        if(freeShipping.getValue()){
            return "Yes";
        }
        else{
            return "No";
        }
    }
}
