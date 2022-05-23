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
import javafx.scene.control.Button;
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
    @FXML public AnchorPane shopPane;
    @FXML private FlowPane productsFlowPane;
    @FXML public Label currentTab;
    @FXML public ImageView currentImage;
    @FXML public AnchorPane myPages;
    //@FXML public StackPane mainHome;

    @FXML public AnchorPane myorderhistory;
    @FXML public AnchorPane myinfo;


    @FXML public FlowPane previousOrdersFlowpane;
    @FXML public FlowPane receiptFlowPane;

    @FXML public Button closeMyPages;
    @FXML private Label purchasesLabel;


    //Categories
    @FXML private Label kött;
    @FXML private Label fisk;
    @FXML private Label frukt;
    @FXML private Label grönsaker;
    @FXML private Label mejeri;
    @FXML private Label skafferi;
    @FXML private Label bröd;
    @FXML private Label kryddor;
    @FXML private Label dryck;
    @FXML private Label godis;


    //My Pages
    @FXML TextField firstname;
    @FXML TextField surname;
    @FXML TextField mail;
    @FXML TextField phoneNumber;
    @FXML TextField address;
    @FXML TextField postcode;
    @FXML TextField cardnumber;
    @FXML TextField validthru;
    @FXML TextField cvc;

    @FXML Label myName;

    //TODO
    @FXML FlowPane mylistsFlowPane;
    @FXML private AnchorPane dynamicPane;
    @FXML public AnchorPane cartPane;
    @FXML public AnchorPane checkoutPane;
    //@FXML private AnchorPane navbar;

    private Navbar navbarController;
    public CartController cartController;
    public Checkout checkoutController;


    // Other variables
    private final Model model = Model.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //presetsAccount();
        setAccountLabels();
        model.getShoppingCart().addShoppingCartListener(this);

        //Setup Cart
        cartController = new CartController(this, model);
        cartPane.getChildren().add(cartController);

        navbarController = new Navbar(this, cartController, model);
        dynamicPane.getChildren().add(navbarController);

        checkoutController = new Checkout(this, model);
        checkoutPane.getChildren().add(checkoutController);

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

    public void updateCartItems() {
        ShoppingCart cart = model.getShoppingCart();
        cartController.myCartFlowPane.getChildren().clear();
        checkoutController.checkoutCart.getChildren().clear();

        for (ShoppingItem cartItem : cart.getItems()) {
            cartController.myCartFlowPane.getChildren().add(new CartProductPanel(cartItem));
            checkoutController.checkoutCart.getChildren().add(new CartProductPanel(cartItem));
        }
    }

    // Shope pane methods

    public void drawReceipt(Order order){

        List<ShoppingItem> items = order.getItems();


        receiptFlowPane.getChildren().clear();

        for (ShoppingItem item : items) {
            receiptFlowPane.getChildren().add(new ReceiptOverview(item));
        }
/*
        List<ShoppingItem> newList = new ;



        for (ShoppingItem shoppingItem : order.getItems()) {
            if (shoppingItem.getProduct() == p) {
                shoppingItem.setAmount(items.getAmount() + 1);

            }
        }*/
    }


    private void updateHistoryItems() {
        List<Order> orders = model.getOrders();
        previousOrdersFlowpane.getChildren().clear();

        for (int i = orders.size(); i-- > 0; ) {
            previousOrdersFlowpane.getChildren().add(new OrderItemPanel(this, orders.get(i)));
        }
    }
    
    // Shop pane methods
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
    
    private void updateBottomPanel() {

        ShoppingCart shoppingCart = model.getShoppingCart();

        String size = String.valueOf(shoppingCart.getItems().size());
        double totalCost = shoppingCart.getTotal();

        navbarController.updateNavbarInformation(size, totalCost);

    }

    @FXML void closeMyPages(){
        myorderhistory.toBack();
        myinfo.toFront();
        myPages.toBack();
    }

    //TODO kanske inte behövs
    private void presetsAccount(){
        model.getCustomer().setFirstName("Göran");
        model.getCustomer().setLastName("Svensson");
        model.getCustomer().setEmail("göran.svensson@gmail.com");
        model.getCustomer().setAddress("Bråkmakargatan 47");
        model.getCustomer().setPhoneNumber("073411337");
        model.getCustomer().setMobilePhoneNumber("0760300300303");
        model.getCustomer().setPostAddress("Bråkmakargatan 47");
        model.getCustomer().setPostCode("44333");
        model.getCreditCard().setCardNumber("4444 1111 3333 4444");
        model.getCreditCard().setVerificationCode(123);
    }

    public void setAccountLabels(){
        myName.setText(model.getCustomer().getFirstName() + " " + model.getCustomer().getLastName());
        firstname.setText(model.getCustomer().getFirstName());
        surname.setText(model.getCustomer().getLastName());
        mail.setText(model.getCustomer().getEmail());
        phoneNumber.setText(model.getCustomer().getPhoneNumber());
        address.setText(model.getCustomer().getAddress());
        postcode.setText(model.getCustomer().getPostCode());
        cardnumber.setText(model.getCreditCard().getCardNumber());
        validthru.setText(model.getCreditCard().getValidYear() + "/" + model.getCreditCard().getValidMonth());
        cvc.setText(String.valueOf(model.getCreditCard().getVerificationCode()));
    }

    @FXML private void saveCustomerDetails(){
        model.getCustomer().setFirstName(firstname.getText());
        model.getCustomer().setLastName(surname.getText());
        model.getCustomer().setEmail(mail.getText());
        model.getCustomer().setPhoneNumber(phoneNumber.getText());
        model.getCustomer().setAddress(address.getText());
        model.getCustomer().setPostCode(postcode.getText());
        model.getCreditCard().setCardNumber(cardnumber.getText());

        //TODO split
        //card.setValidYear(card.getText());
        model.getCreditCard().setValidMonth(Integer.parseInt(validthru.getText()));
        model.getCreditCard().setVerificationCode(Integer.parseInt(cvc.getText()));

        setAccountLabels();
    }

    @FXML void swapToMyInfo(){
        myinfo.toFront();
    }

    @FXML void swapToHistory(){
        updateHistoryItems();
        myorderhistory.toFront();
    }
}
