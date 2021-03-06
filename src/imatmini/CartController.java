/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.User;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author oloft
 */
public class CartController extends AnchorPane {


    @FXML public FlowPane myCartFlowPane;
    private iMatMiniController mainController;
    private Model model;

    @FXML public Label totalCost;
    @FXML private Button toCheckout;

    public CartController(iMatMiniController mainController, Model model) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cartComponent.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.model = model;

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainController = mainController;
    }

    @FXML public void closeCart(){
        mainController.cartPane.toBack();
    }

    @FXML public void toCheckout() throws IOException {
        mainController.bringCheckoutFront();
        mainController.checkoutPane.toFront();
        mainController.updateCartItems();
    }

    @FXML private void clearCart(){
        model.getShoppingCart().clear();
    }

    @FXML
    public void mouseTrap(Event event){
        event.consume();
    }


}
