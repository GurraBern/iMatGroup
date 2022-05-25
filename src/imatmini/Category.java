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

public class Category extends AnchorPane {

    public ProductCategory mainCategory;
    public List<ProductCategory> underlyingCategories;
    private iMatMiniController mainController;

    @FXML private Label categoryLabel;


    public Category(ProductCategory mainCategory, iMatMiniController mainController){
        this.mainController = mainController;
        this.mainCategory = mainCategory;
        fxmlSetup();

    }

    public Category(ProductCategory mainCategory, List<ProductCategory> underlyingCategories , iMatMiniController mainController){
        this.mainController = mainController;

        this.mainCategory = mainCategory;
        this.underlyingCategories = underlyingCategories;
        fxmlSetup();
    }

    @FXML private void sortByCategory(){
        mainController.updateProductListCategory(mainCategory);
    }

    private void fxmlSetup(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        categoryLabel.setText(mainCategory.name());
    }
}
