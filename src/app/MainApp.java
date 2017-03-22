package app;

import app.model.Product;
import app.view.ProductEditController;
import app.view.ProductOverviewController;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static app.model.ProductType.*;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private final ObservableList<Product> productData = FXCollections.observableArrayList();

    public MainApp() {
        productData.add(new Product("Tacos", 0, new SimpleObjectProperty<>(FOOD), false));
        productData.add(new Product("Pilsner Urquell", 1, new SimpleObjectProperty<>(BEER), true));
        productData.add(new Product("Dom Perignon", 2, new SimpleObjectProperty<>(WINE), false));
        productData.add(new Product("Marlboro Red", 3, new SimpleObjectProperty<>(CIGARETTE), true));
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Product> getProductData() {
        return productData;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Shop inventory");
        initRootLayout();
        showProductOverview();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showProductOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductOverview.fxml"));
            AnchorPane productOverview = loader.load();
            rootLayout.setCenter(productOverview);
            ProductOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showProductEditOverview(Product product) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProductEditOverview.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit product.");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            ProductEditController controller = loader.getController();
            controller.setProductEditStage(dialogStage);
            controller.setProductFields(product);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
