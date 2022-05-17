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

    @FXML public Label costLabel;
    @FXML public Label itemsLabel;


    //private Model model = Model.getInstance();

    private iMatMiniController mainController;
    private Model model;



    public Navbar(iMatMiniController mainController, Model model) {

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
        searchButton.setOnMouseClicked(mouseEvent -> handleSearchAction());

    }

    @FXML
    private void handleBuyItemsAction(ActionEvent event) {
        model.placeOrder();
        costLabel.setText("Köpet klart!");
    }

    public void updateNavbarInformation(String size, String totalCost){
        costLabel.setText(totalCost);
        itemsLabel.setText(size);
    }

    /*@FXML public void closeCart(){
        cartView.toBack();
    }


    @FXML public void openCart() {
        cartView.toFront();
    }

     */

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

}
