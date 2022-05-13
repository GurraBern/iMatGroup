package imatmini;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;


public class listController extends AnchorPane {

    @FXML Label itemOne;
    @FXML Label itemTwo;
    @FXML Label itemThree;
    @FXML Label list;
    @FXML ImageView imgOne;
    @FXML ImageView imgTwo;
    @FXML ImageView imgThree;

    private Model model = Model.getInstance();

    public void shoppingList(){
        model.getShoppingCart();
    }

}
