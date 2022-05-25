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
    private String categoryName;

    @FXML private Label categoryLabel;




    public Category(String categoryName, iMatMiniController mainController){
        this.mainController = mainController;
        this.categoryName = categoryName;
        fxmlSetup();

    }

    public Category(String categoryName, List<ProductCategory> underlyingCategories , iMatMiniController mainController){
        this.mainController = mainController;

        this.underlyingCategories = underlyingCategories;
        this.categoryName = categoryName;

        fxmlSetup();
    }

    @FXML private void sortByCategory(){
        mainController.resetSubCategories();
        if(categoryName != "Visa Alla"){
            mainController.updateProductListCategory(mainCategory, underlyingCategories);
        } else {
            mainController.updateProductList();
        }
        if(underlyingCategories != null)
            mainController.updateSubCategories(underlyingCategories);
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

        categoryLabel.setText(categoryName);
    }
}
