/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author oloft
 */
public class Navbar extends AnchorPane {

    @FXML private TextField searchField;
    @FXML private AnchorPane cartView;
    @FXML public Button searchButton;
    @FXML public Label homeNav;
    @FXML public Label favoritesNav;
    @FXML public Label myPages;
    @FXML public Label logo;

    @FXML public Label costLabel;
    @FXML public Label itemsLabel;
    @FXML private AnchorPane cartPane;


    //private Model model = Model.getInstance();

    private iMatMiniController mainController;
    private Model model;
    private CartController cartController;

    public Navbar(iMatMiniController mainController, CartController cartController, Model model) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("navbar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        this.model = model;


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mainController = mainController;
        this.cartController = cartController;
        searchButton.setOnMouseClicked(mouseEvent -> handleSearchAction());
        favoritesNav.setOnMouseClicked(mouseEvent -> switchToFavorites());
        homeNav.setOnMouseClicked(mouseEvent -> switchToHome());
        myPages.setOnMouseClicked(mouseEvent -> swapMyPages());
        logo.setOnMouseClicked(mouseEvent -> switchToHome());
        mainController.closeMyPages.setOnMouseClicked(mouseEvent -> swapBack());
    }

    @FXML
    private void handleBuyItemsAction(ActionEvent event) {
        model.placeOrder();
        costLabel.setText("Köpet klart!");
    }

    public void updateNavbarInformation(String size, double totalCost){
        costLabel.setText("Kostnad: " + String.format("%.2f",totalCost));
        itemsLabel.setText(size);
        cartController.totalCost.setText(String.format("%.2f",totalCost) + "kr");


    }

    @FXML public void closeCart(){
        mainController.cartPane.toBack();
    }


    @FXML public void openCart() {
        mainController.open();
    }

    @FXML
    //private void handleSearchAction(ActionEvent event) {
    private void handleSearchAction() {
        List<Product> matches = model.findProducts(searchField.getText());
        mainController.updateProductList(matches);
        System.out.println("# matching products: " + matches.size());
    }

    @FXML
    private void handleClearCartAction(ActionEvent event) {
        model.clearShoppingCart();
    }


    void switchToHome(){
        mainController.currentTab.setText("Home");
        //mainController.currentImage.getImage();
        mainController.updateProductList(model.getProducts());
    }

    void switchToFavorites(){
        mainController.currentTab.setText("Favorites");
        mainController.updateProductList(model.getFavorites());
    }
    @FXML void swapMyPages(){
        mainController.setAccountLabels();
        mainController.myPages.toFront();
    }
    @FXML void swapBack() {
        mainController.myPages.toBack();
    }
}
