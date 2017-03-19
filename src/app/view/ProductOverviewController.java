package app.view;

import app.MainApp;
import app.model.Product;
import javafx.fxml.FXML;
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


    public void onClickButtonNew() {
        System.out.println("New");
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        productTable.setItems(mainApp.getProductData());
    }

    private void showProductDetails(Product product) {
        if (product != null) {
            nameLabel.setText(product.getName());
            typeLabel.setText(product.getType().toString());
        } else {
            nameLabel.setText("");
            typeLabel.setText("");
        }
    }
}
