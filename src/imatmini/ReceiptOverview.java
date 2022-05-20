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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

public class ReceiptOverview extends AnchorPane {


    @FXML Label productName;
    @FXML Label productAmount;
    @FXML ImageView productImage;
    private ShoppingItem item;

    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;
    private Model model = Model.getInstance();

    public ReceiptOverview(ShoppingItem item) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("receiptOverviewItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.item = item;
        productName.setText(item.getProduct().getName());
        productAmount.setText(String.valueOf(item.getAmount()));
        productImage.setImage(model.getImage(item.getProduct(), kImageWidth, kImageWidth*kImageRatio));
    }

}
