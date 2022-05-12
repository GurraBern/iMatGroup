package imatmini;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class CartProductPanel extends AnchorPane {
        @FXML ImageView productImage;
        @FXML Label productName;
        @FXML Label productAmount;


        private Model model = Model.getInstance();
        private Product product;

        private final static double kImageWidth = 100.0;
        private final static double kImageRatio = 0.75;

        public CartProductPanel(ShoppingItem cartItem) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCartOverviewItem.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            try {
                fxmlLoader.load();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }

            this.product = cartItem.getProduct();
            productName.setText(product.getName());


            double newAmount = 0;
            for (ShoppingItem item : model.getShoppingCart().getItems()) {
                if(item.getProduct().getProductId() == product.getProductId()){
                    newAmount += item.getAmount();
                }
            }




            productAmount.setText(String.valueOf(newAmount));

            productImage.setImage(model.getImage(product, kImageWidth, kImageWidth*kImageRatio));
            /*if (!product.isEcological()) {
                ecoLabel.setText("");
            }

             */
        }

        @FXML
        private void handleAddAction(ActionEvent event) {
            System.out.println("Add " + product.getName());
            model.addToShoppingCart(product);
        }


}
