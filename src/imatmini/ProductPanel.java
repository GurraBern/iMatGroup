/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

/**
 *
 * @author oloft
 */
public class ProductPanel extends AnchorPane {

    @FXML ImageView imageView;
    @FXML Label nameLabel;
    @FXML Label prizeLabel;
    @FXML Label ecoLabel;


    //TODO should change
    @FXML
    Button addItemButton;

    @FXML
    Button addSingleItemButton;

    private Model model = Model.getInstance();

    private Product product;
    
    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;

    public ProductPanel(Product product) {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        nameLabel.setText(product.getName());
        prizeLabel.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
        imageView.setImage(model.getImage(product, kImageWidth, kImageWidth*kImageRatio));
        if (!product.isEcological()) {
            ecoLabel.setText("");
        }
    }
    
    @FXML
    private void handleAddAction(ActionEvent event) {

        model.addSingleItem(product);
        /*System.out.println("Add " + product.getName());

        int index = 0;
        double test = 0;
        boolean inCart = false;
        for (ShoppingItem item: model.getShoppingCart().getItems()) {
            if(item.getProduct().getProductId() == product.getProductId()){
                //Already exists
                test = (item.getAmount() + 1);
                System.out.println("Current count===== " + test);
                item.setAmount(test);
                inCart = true;
            }
            else {
                if(index >= model.getShoppingCart().getItems().size()){
                    inCart = false;
                }
            }
            index++;
        }

        if(!inCart){
            model.addToShoppingCart(product);
        }

        //TODO lägg tillbaka knapp om varor är = 0 sätt opacity = 1 visa plus och minus knappar
        //om det redan finns en produkt!

        //addItemButton.setOpacity(0);
        //addItemButton.setDisable(true);

         */
    }


    @FXML
    private void handleRemoveAction(ActionEvent event) {
        System.out.println("Remove " + product.getName());


        Product pr = model.getProduct(product.getProductId());
        model.removeFromShoppingCart(pr);
    }





}
