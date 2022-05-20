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
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.Product;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Checkout extends AnchorPane {

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

    public Checkout() {
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

    @FXML public void continueShopping() throws IOException {
        stylingReset();
        navCheckout.setUnderline(true);
        Stage primaryStage = (Stage) this.getScene().getWindow();
        primaryStage.close();
        primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("iMatMini.fxml"));
        primaryStage.setTitle("main");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void completeOrder() {
        stylingReset();
    }
}
