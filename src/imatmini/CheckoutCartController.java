/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

/**
 *
 * @author oloft
 */
public class CheckoutCartController extends AnchorPane {


    @FXML public FlowPane myCartFlowPane;
    private iMatMiniController mainController;
    private final Model model = Model.getInstance();

    public CheckoutCartController(Model model) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cartCheckout.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        //this.model = model;

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        updateCartItems();
    }

    private void updateCartItems() {
        ShoppingCart cart = model.getShoppingCart();
        myCartFlowPane.getChildren().clear();


        for (ShoppingItem cartItem : cart.getItems()) {
            myCartFlowPane.getChildren().add(new CartProductPanel(cartItem));
        }
    }

    public void setController(iMatMiniController controller){
        this.mainController = controller;
    }
}
