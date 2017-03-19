package app;

import app.model.Product;
import app.model.ProductType;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import app.view.ProductOverviewController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Product> productData = FXCollections.observableArrayList();

    public MainApp() {
        productData.add(new Product("Parówczan", 1, ProductType.FOOD));
        productData.add(new Product("Zimny Leszek", 2, ProductType.BEER));
        productData.add(new Product("Amarena", 3, ProductType.WINE));
        productData.add(new Product("Marlboro Red", 4, ProductType.CIGARETTE));
    }

    public static void main(String[] args) {
        launch(args);
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

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
