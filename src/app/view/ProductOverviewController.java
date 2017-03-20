package app.view;

import app.MainApp;
import app.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProductOverviewController {

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> typeColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label amountLabel;
    @FXML
    private Label inStockLabel;

    private MainApp mainApp;

    public ProductOverviewController() {
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        showProductDetails(null);
        productTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showProductDetails(newValue)));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        productTable.setItems(mainApp.getProductData());
    }

    private void showProductDetails(Product product) {
        if (product != null) {
            nameLabel.setText(product.getName());
            typeLabel.setText(product.getType());
            amountLabel.setText(product.getAmount().asObject().getValue().toString());
            inStockLabel.setText(product.isInStockToString());
        } else {
            nameLabel.setText("");
            typeLabel.setText("");
            amountLabel.setText("");
            inStockLabel.setText("");
        }
    }

    @FXML
    private void handleNewButton() {
        Product tempProduct = new Product();
        boolean okClicked = mainApp.showProductEditOverview(tempProduct);
        if (okClicked) {
            mainApp.getProductData().add(tempProduct);
        }
    }

    @FXML
    private void handleEditButton() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            boolean okClicked = mainApp.showProductEditOverview(selectedProduct);
            if (okClicked) {
                showProductDetails(selectedProduct);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No product Selected");
            alert.setContentText("Please select a product in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteButton() {
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            productTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection.");
            alert.setHeaderText("No product selected.");
            alert.setContentText("Please select a product in the table.");
            alert.showAndWait();
        }
    }
}
