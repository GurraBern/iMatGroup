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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author oloft
 */
public class OrderItemPanel extends AnchorPane {


    private iMatMiniController mainController;
    private Model model;
    private Order order;

    @FXML public Label totalCost;
    @FXML private Button showReciept;
    @FXML private Label orderItemCost;
    @FXML private Label dateLabel;

    public OrderItemPanel(iMatMiniController mainController,Order order) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("orderHistoryItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.mainController = mainController;
        this.order = order;


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.mainController = mainController;

        orderItemCost.setText(calcTotalCost() + "kr");

        String newstring = new SimpleDateFormat("yyyy-mm-dd").format(order.getDate());
        dateLabel.setText(newstring);



        showReciept.setOnAction((event) -> {
            System.out.println("Button clicked");
            showReciept();
        });
    }

    private void showReciept() {
        mainController.drawReceipt(this.order);
        System.out.println("Works");
    }

    private double calcTotalCost(){
        double totalCost = 0;
        for (ShoppingItem item: this.order.getItems()) {
            totalCost += item.getTotal();
        }

        return totalCost;
    }


}
