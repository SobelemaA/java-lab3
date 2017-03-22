package app.view;

import app.model.Product;
import app.model.ProductType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProductEditController {
    private Stage productEditStage;
    private Product product;
    private boolean okClicked = false;

    @FXML
    private TextField nameField;
    @FXML
    private TextField amountField;
    @FXML
    private ComboBox<ProductType> typeField;
    @FXML
    private CheckBox freeShippingField;


    @FXML
    private void initialize() {
        typeField.getItems().addAll(ProductType.values());
    }

    public void setProductEditStage(Stage productEditStage) {
        this.productEditStage = productEditStage;
    }

    public void setProductFields(Product product) {
        this.product = product;
        nameField.setText(product.getName());
        amountField.setText(product.amountProperty().asObject().getValue().toString());
        product.checkAvailability();
        typeField.setValue(product.getProductType());
        freeShippingField.setSelected(product.isFreeShipping());
    }


    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOkButton() {
        if (isInputValid()) {
            product.setName(nameField.getText());
            product.setAmount(Integer.parseInt(amountField.getText()));
            product.setType((typeField.getSelectionModel().getSelectedItem()));
            product.setFreeShipping(freeShippingField.isSelected());
            okClicked = true;
            productEditStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name\n";
        }
        if (amountField.getText() == null) {
            errorMessage += "No valid amount\n";
        } else {
            try {
                Integer.parseInt(amountField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid amount, must be integer number.\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(productEditStage);
            alert.setTitle("Invalid fields.");
            alert.setHeaderText("Please correct invalid fields.");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void handleCancelButton() {
        productEditStage.close();
    }
}
