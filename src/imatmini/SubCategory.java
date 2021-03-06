package imatmini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class SubCategory extends AnchorPane {

    public ProductCategory category;
    private iMatMiniController mainController;

    @FXML public Label subCategoryLabel;


    public SubCategory(ProductCategory category, iMatMiniController mainController){
        this.mainController = mainController;
        this.category = category;
        fxmlSetup();
    }

    private void fxmlSetup(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subCategoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        subCategoryLabel.setText(category.name());
    }

    public void clearStyle(){
        subCategoryLabel.getStyleClass().clear();

    }


    @FXML private void sortByCategory(){
        mainController.resetSubCategories();
        mainController.updateProductListCategory(category);

        subCategoryLabel.getStyleClass().add("buttonPressed");
        subCategoryLabel.getStyleClass().add("inderPog");
        subCategoryLabel.setStyle("-fx-font-size: 35");

    }
}
