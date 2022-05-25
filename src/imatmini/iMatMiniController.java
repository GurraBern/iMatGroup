/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML public StackPane mainHome;

    @FXML public AnchorPane myorderhistory;
    @FXML public AnchorPane myinfo;


    @FXML public FlowPane previousOrdersFlowpane;
    @FXML public FlowPane receiptFlowPane;

    @FXML public Button closeMyPages;
    @FXML private Label purchasesLabel;

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

    @FXML private AnchorPane dynamicPane;
    @FXML public AnchorPane cartPane;

    @FXML public AnchorPane checkoutPane;

    private Navbar navbarController;
    public CartController cartController;

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

    @FXML RadioButton cardRadio;

    @FXML Label navInfo;
    @FXML Label navCheckout;
    @FXML Label navDelivery;
    @FXML Label navControl;

    @FXML AnchorPane CheckoutPage;
    @FXML AnchorPane CustomerInfo;
    @FXML AnchorPane DeliveryPage;
    @FXML AnchorPane ControlPage;

    @FXML TextField cardNumberCheckout;
    @FXML TextField cardCVCCheckout;
    @FXML TextField cardValidCheckout;


    @FXML TextField addressCheckout;
    @FXML TextField postcodeCheckout;
    @FXML TextField cityCheckout;


    @FXML Label checkoutTotalPrice;
    @FXML Label checkoutTotalPrice2;


    private String timeofDay;
    private String timeinterval;



    //private CheckoutCartController cartController;
    private iMatMiniController mainController;
    @FXML public FlowPane checkoutCart;
    @FXML public FlowPane cartControl;
    @FXML public FlowPane browseFlowpane;


    //@FXML private AnchorPane cartPane;



    // Other variables
    private final Model model = Model.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //presetsAccount();
        setAccountLabels();
        model.getShoppingCart().addShoppingCartListener(this);

        createCategories();

        //Setup Cart
        cartController = new CartController(this, model);
        cartPane.getChildren().add(cartController);

        navbarController = new Navbar(this, cartController, model);
        dynamicPane.getChildren().add(navbarController);

        updateProductList(model.getProducts());
        updateBottomPanel();
        updateCartItems();
    }

    private void createCategories(){
        Category kott = new Category(ProductCategory.MEAT, this);
        Category fisk = new Category(ProductCategory.FISH, this);
        Category berry = new Category(ProductCategory.BERRY, this);
        Category bread = new Category(ProductCategory.BREAD, this);
        Category cabbage = new Category(ProductCategory.CABBAGE, this);
        Category citrus = new Category(ProductCategory.CITRUS_FRUIT, this);
        Category exoticfruit = new Category(ProductCategory.EXOTIC_FRUIT, this);



        List<Category> browseCategories = new ArrayList<>();
        browseCategories.add(kott);
        browseCategories.add(fisk);
        browseCategories.add(berry);
        browseCategories.add(bread);
        browseCategories.add(cabbage);
        browseCategories.add(citrus);
        browseCategories.add(exoticfruit);

        browseFlowpane.getChildren().clear();

        for (Category category : browseCategories) {
            browseFlowpane.getChildren().add(category);
        }
    }

    public void open(){
         cartPane.toFront();
    }

    public void updateCartItems() {
        ShoppingCart cart = model.getShoppingCart();
        cartController.myCartFlowPane.getChildren().clear();
        checkoutCart.getChildren().clear();
        cartControl.getChildren().clear();

        for (ShoppingItem cartItem : cart.getItems()) {
            cartController.myCartFlowPane.getChildren().add(new CartProductPanel(cartItem));
            checkoutCart.getChildren().add(new CartProductPanel(cartItem));
            cartControl.getChildren().add(new ReceiptOverview(cartItem));
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

    public void updateProductListCategory(ProductCategory category) {
        productsFlowPane.getChildren().clear();
        List<Product> categorizedProducts = model.getProductCategories(category);

        for (Product product : categorizedProducts) {
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

        addressCheckout.setText(model.getCustomer().getAddress());
        postcodeCheckout.setText(model.getCustomer().getPostCode());
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
        //model.getCreditCard().setValidMonth(Integer.parseInt(validthru.getText()));
        model.getCreditCard().setVerificationCode(Integer.parseInt(cvc.getText()));
        //System.out.println(model.getCreditCard().getVerificationCode());
        setAccountLabels();
    }

    @FXML void swapToMyInfo(){
        myinfo.toFront();
    }

    @FXML void swapToHistory(){
        updateHistoryItems();
        myorderhistory.toFront();
    }

    //Reroute


    public void stylingReset() {
        navInfo.setUnderline(false);
        navCheckout.setUnderline(false);
        navDelivery.setUnderline(false);
        navControl.setUnderline(false);
    }


    @FXML private Label timeintervalLabel;

    public void bringCheckoutFront() {

        cardNumberCheckout.setText(model.getCreditCard().getCardNumber());
        cardCVCCheckout.setText(String.valueOf(model.getCreditCard().getVerificationCode()));
        String test = model.getCreditCard().getValidYear() + "/" + model.getCreditCard().getValidMonth();
        cardValidCheckout.setText(test);
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

    @FXML
    private void handleBuyItemsAction(ActionEvent event) {
        model.placeOrder();
        //TODO add more text
    }

    public void bringControlFront() {
        String totCost = String.format("%.2f",model.getShoppingCart().getTotal());
        checkoutTotalPrice.setText("Summa: " + totCost + "kr");
        checkoutTotalPrice2.setText("Summa: " + totCost + "kr");
        timeintervalLabel.setText(timeinterval);


        stylingReset();
        navControl.setUnderline(true);
        ControlPage.toFront();
    }

    @FXML private void goToHome(){
        mainHome.toFront();
        cartPane.toBack();
        System.out.println("runs");

    }

    @FXML void continueShopping() {
        stylingReset();
        navCheckout.setUnderline(true);
        cartPane.toBack();
        checkoutPane.toBack();

    }

    public void completeOrder() {
        stylingReset();
    }

    /*
    public void drawCart(Model model) {
        ShoppingCart cart = model.getShoppingCart();
        checkoutCart.getChildren().clear();

        for (ShoppingItem cartItem : cart.getItems()) {
            checkoutCart.getChildren().add(new CartProductPanel(cartItem));
        }
    }*/


    @FXML private AnchorPane timeslot1;
    @FXML void chooseBefore(){
        timeinterval = "07:00-12:00";

       /* for (Label label: timeslot1.getChildren()) {
            label.styleProperty().;
        }

        */

    }


    @FXML void chooseAfter(){
        timeinterval = "13:00-17:00";
    }

    @FXML void clickDate(){

    }



}
