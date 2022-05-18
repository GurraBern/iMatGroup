/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.*;

import javax.swing.text.html.ImageView;


public class iMatMiniController implements Initializable, ShoppingCartListener {

    // Shopping Pane
    @FXML
    public AnchorPane shopPane;
    @FXML
    private FlowPane productsFlowPane;

    @FXML public Label currentTab;

    @FXML public ImageView currentImage;

    
    // Account Pane
    /*
    @FXML
    private AnchorPane accountPane;
    @FXML ComboBox cardTypeCombo;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField nameTextField;
    @FXML 
    private ComboBox monthCombo;
    @FXML
    private ComboBox yearCombo;
    @FXML
    private TextField cvcField;

     */
    @FXML
    private Label purchasesLabel;
    @FXML
    private Label kött;
    @FXML
    private Label fisk;
    @FXML
    private Label frukt;
    @FXML
    private Label grönsaker;
    @FXML
    private Label mejeri;
    @FXML
    private Label skafferi;
    @FXML
    private Label bröd;
    @FXML
    private Label kryddor;
    @FXML
    private Label dryck;
    @FXML
    private Label godis;

    //TODO
    @FXML FlowPane mylistsFlowPane;
    @FXML private AnchorPane dynamicPane;
    @FXML public AnchorPane cartPane;
    @FXML private AnchorPane navbar;

    private Navbar navbarController;
    private CartController cartController;
    
    // Other variables
    private final Model model = Model.getInstance();

    // Shop pane actions
    /*
    @FXML
    private void handleShowAccountAction(ActionEvent event) {
        openAccountView();
    }

     */

    // Account pane actions
    /*
     @FXML
    private void handleDoneAction(ActionEvent event) {
        closeAccountView();
    }

     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        model.getShoppingCart().addShoppingCartListener(this);

        //Setup Cart
        cartController = new CartController(this, model);
        cartPane.getChildren().add(cartController);

        navbarController = new Navbar(this, cartController, model);
        dynamicPane.getChildren().add(navbarController);

        updateProductList(model.getProducts());

        updateBottomPanel();

        //setupAccountPane();
        updateCartItems();
        //TODO fixa en myList som har en shopping cart i sig!!!
        //updateMyLists(model.saveShoppingCart());

    }

    public void open(){
         cartPane.toFront();
    }
/*
    // Navigation
    public void openAccountView() {
        updateAccountPanel();
        accountPane.toFront();
    }

    public void closeAccountView() {
        updateCreditCard();
        shopPane.toFront();
    }*/

    private void updateCartItems() {
        ShoppingCart cart = model.getShoppingCart();
        cartController.myCartFlowPane.getChildren().clear();

        for (ShoppingItem cartItem : cart.getItems()) {
            cartController.myCartFlowPane.getChildren().add(new CartProductPanel(cartItem));
        }
    }
    
    // Shope pane methods
    @Override
     public void shoppingCartChanged(CartEvent evt) {
        updateBottomPanel();
        updateCartItems();
    }


    public void updateProductList(List<Product> products) {

        productsFlowPane.getChildren().clear();

        for (Product product : products) {

            productsFlowPane.getChildren().add(new ProductPanel(product));

        }
    }


    @FXML public void addFavorites(Product product){
        model.addToFavorites(product);
    }

    /*
    private void updateMyLists(List<MyList> myLists) {

        saveShoppingCart
        mylistsFlowPane.getChildren().clear();

        for (MyList list : myLists) {

            productsFlowPane.getChildren().add(new ListPanel(list));
        }

    }

     */
    
    private void updateBottomPanel() {

        ShoppingCart shoppingCart = model.getShoppingCart();

        String size = String.valueOf(shoppingCart.getItems().size());
        double totalCost = shoppingCart.getTotal();

        navbarController.updateNavbarInformation(size, totalCost);

    }

    /*
    private void updateAccountPanel() {
        
        CreditCard card = model.getCreditCard();
        
        numberTextField.setText(card.getCardNumber());
        nameTextField.setText(card.getHoldersName());
        
        cardTypeCombo.getSelectionModel().select(card.getCardType());
        monthCombo.getSelectionModel().select(""+card.getValidMonth());
        yearCombo.getSelectionModel().select(""+card.getValidYear());

        cvcField.setText(""+card.getVerificationCode());
        
        purchasesLabel.setText(model.getNumberOfOrders()+ " tidigare inköp hos iMat");
        
    }
    
    private void updateCreditCard() {
        
        CreditCard card = model.getCreditCard();
        
        card.setCardNumber(numberTextField.getText());
        card.setHoldersName(nameTextField.getText());
        
        String selectedValue = (String) cardTypeCombo.getSelectionModel().getSelectedItem();
        card.setCardType(selectedValue);
        
        selectedValue = (String) monthCombo.getSelectionModel().getSelectedItem();
        card.setValidMonth(Integer.parseInt(selectedValue));
        
        selectedValue = (String) yearCombo.getSelectionModel().getSelectedItem();
        card.setValidYear(Integer.parseInt(selectedValue));
        
        card.setVerificationCode(Integer.parseInt(cvcField.getText()));
    }
    
    private void setupAccountPane() {
                
        cardTypeCombo.getItems().addAll(model.getCardTypes());
        
        monthCombo.getItems().addAll(model.getMonths());
        
        yearCombo.getItems().addAll(model.getYears());
    }

    @FXML
    public void mouseTrap(Event event){
        event.consume();
    }

     */

}
