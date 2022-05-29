/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import java.io.IOException;

import imatmini.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import javax.swing.*;

/**
 *
 * @author oloft
 */
public class ProductPanel extends AnchorPane {

    @FXML
    ImageView imageView;
    @FXML
    Label nameLabel;
    @FXML
    Label prizeLabel;
    @FXML
    Label ecoLabel;
    @FXML
    Label amount;


    //TODO should change
    @FXML
    AnchorPane addButton;

    @FXML
    AnchorPane addremoveButton;

    @FXML
    ToggleButton favoriteButton;

    private Model model = Model.getInstance();

    private Product product;


    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;

    public ProductPanel(Product product) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        nameLabel.setText(product.getName());

        prizeLabel.setText(String.format("%.2f", product.getPrice()) + product.getUnit());

        imageView.setImage(model.getImage(product, kImageWidth, kImageWidth * kImageRatio));
        if (!product.isEcological()) {
            ecoLabel.setText("");
        }
        setAmountOfProduct();
        favoriteButton.setOnMouseClicked(mouseEvent -> addFavorites(product));
        addButton.setOnMouseClicked(mouseEvent -> handleAddAction());

        addremoveButton.visibleProperty().setValue(false);
        addButton.visibleProperty().setValue(false);

        checkFavorite(product);
        checkButton();

    }

    private void checkButton(){
        for (ShoppingItem item: model.getShoppingCart().getItems()) {
            if(item.getProduct().getProductId() == product.getProductId()){
                addremoveButton.visibleProperty().setValue(true);
                addButton.visibleProperty().setValue(false);
                return;
            }
        }
        addremoveButton.visibleProperty().setValue(false);
        addButton.visibleProperty().setValue(true);
    }

    @FXML
    private void handleAddAction() {
        model.addToShoppingCart(product);

        setAmountOfProduct();

        addremoveButton.visibleProperty().setValue(true);
        addButton.visibleProperty().setValue(false);
    }

    @FXML private void handleRemoveAction() {
        model.removeItemFromShoppingCart(this, product);
        setAmountOfProduct();
    }

    public void setAddRemoveButton (){
        addremoveButton.visibleProperty().setValue(true);
        addButton.visibleProperty().setValue(false);
    }

    public void setAddButton (){
        addremoveButton.visibleProperty().setValue(false);
        addButton.visibleProperty().setValue(true);
    }

    @FXML
    public void addFavorites(Product product) {
        if (model.isFavorite(product)) {
            model.removeFromFavorites(product);
            favoriteButton.getStyleClass().remove("like");
            favoriteButton.getStyleClass().add("hearth");
        }
        else {

            model.addToFavorites(product);
            favoriteButton.getStyleClass().remove("hearth");
            favoriteButton.getStyleClass().add("like");
        }
    }

    public void checkFavorite(Product product) {
        if (!model.isFavorite(product)) {
            favoriteButton.getStyleClass().remove("like");
            favoriteButton.getStyleClass().add("hearth");
        }
        else {
            favoriteButton.getStyleClass().remove("hearth");
            favoriteButton.getStyleClass().add("like");
        }
    }

    @FXML
    public void removeFavorite(Product product) {
        model.removeFromFavorites(product);
        favoriteButton.getStyleClass().add("hearth");
    }

    private void setAmountOfProduct() {
        ShoppingCart shoppingCart = model.getShoppingCart();
        for (ShoppingItem item : shoppingCart.getItems()) {
            if (item.getProduct() == product) {
                amount.setText(String.valueOf((int) item.getAmount()));
            }
        }
    }
/*
    @FXML
    private void handleRemoveAction(ActionEvent event) {
        System.out.println("Remove " + product.getName());


        Product pr = model.getProduct(product.getProductId());
        model.removeFromShoppingCart(pr);


    }

 */




}




