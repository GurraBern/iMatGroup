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


    @FXML AnchorPane CheckoutPage;
    @FXML AnchorPane CustomerInfo;
    @FXML AnchorPane DeliveryPage;
    @FXML AnchorPane ControlPage;

    public void bringCheckoutFront() {
        CheckoutPage.toFront();
    }

    public void bringInformationFront() {
        CustomerInfo.toFront();
    }

    public void bringDeliveryFront() {
        DeliveryPage.toFront();
    }

    public void bringControlFront() {
        ControlPage.toFront();
    }

    public void continueShopping() {

    }
    public void completeOrder() {}
}
