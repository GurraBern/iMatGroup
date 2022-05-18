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
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;

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

}