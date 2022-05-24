/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Checkout extends AnchorPane {


    @FXML AnchorPane checkoutPane;
    @FXML Button FirstNext;
    @FXML Button SecondNext;
    @FXML Button ThirdNext;
    @FXML Button CompleteOrder;

    @FXML Button FirstBack;
    @FXML Button SecondBack;
    @FXML Button ThirdBack;
    @FXML Button FourthBack;

    @FXML Button continueShopping1;
    @FXML Button continueShopping2;
    @FXML Button continueShopping3;
    @FXML Button continueShopping4;

    @FXML Label navInfo;
    @FXML Label navCheckout;
    @FXML Label navDelivery;
    @FXML Label navControl;

    @FXML AnchorPane CheckoutPage;
    @FXML AnchorPane CustomerInfo;
    @FXML AnchorPane DeliveryPage;
    @FXML AnchorPane ControlPage;

    private CheckoutCartController cartController;
    private iMatMiniController mainController;
    @FXML public FlowPane checkoutCart;

    @FXML private AnchorPane cartPane;

    private Model model;

    public Checkout(iMatMiniController mainController, Model model) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Checkout.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.mainController = mainController;
        this.model = model;


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //FirstBack.setOnAction(continueShopping());

/*
        //Setup Cart
        cartController = new CheckoutCartController(model);
        cartPane.getChildren().add(cartController);
        FirstNext.setOnMouseClicked(mouseEvent -> bringInformationFront());

 */

    }

    public void stylingReset() {
        navInfo.setUnderline(false);
        navCheckout.setUnderline(false);
        navDelivery.setUnderline(false);
        navControl.setUnderline(false);
    }

    public void bringCheckoutFront() {
        stylingReset();
        navCheckout.setUnderline(true);
        CheckoutPage.toFront();
    }

    public void bringInformationFront() {
        stylingReset();
        navInfo.setUnderline(true);
        CustomerInfo.toFront();
    }

    public void bringDeliveryFront() {
        stylingReset();
        navDelivery.setUnderline(true);
        DeliveryPage.toFront();
    }

    public void bringControlFront() {
        stylingReset();
        navControl.setUnderline(true);
        ControlPage.toFront();
    }

    @FXML void continueShopping() {
        stylingReset();
        navCheckout.setUnderline(true);

        //mainController.mainHome.toFront();
        //checkoutPane.toBack();
        mainController.cartPane.toBack();
        mainController.checkoutPane.toBack();

        /*Stage primaryStage = (Stage) this.getScene().getWindow();
        primaryStage.close();
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("iMatMini.fxml"));
        primaryStage.setTitle("main");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

         */
    }

    public void completeOrder() {
        stylingReset();
    }

    /*
    public void drawCart(Model model) {
        ShoppingCart cart = model.getShoppingCart();
        checkoutCart.getChildren().clear();

        for (ShoppingItem cartItem : cart.getItems()) {
            checkoutCart.getChildren().add(new CartProductPanel(cartItem));
        }
    }*/
}
