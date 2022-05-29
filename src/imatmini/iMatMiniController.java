/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import se.chalmers.cse.dat216.project.*;



public class iMatMiniController implements Initializable, ShoppingCartListener {

    // Shopping Pane
    @FXML public AnchorPane shopPane;
    @FXML private FlowPane productsFlowPane;
    @FXML public Label currentTab;
    @FXML public ImageView currentTabImage;
    @FXML public ImageView homeIcon;
    @FXML public ImageView favIcon;
    @FXML public AnchorPane myPages;
    @FXML public StackPane mainHome;

    @FXML public AnchorPane myorderhistory;
    @FXML public AnchorPane myinfo;


    @FXML public FlowPane previousOrdersFlowpane;
    @FXML public FlowPane receiptFlowPane;

    @FXML public Button closeMyPages;
    @FXML private Label purchasesLabel;
    @FXML public Label deliveryDate;

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

    public List<SubCategory> subCategoriesActive = new ArrayList<>();
    private List<Category> browseCategories = new ArrayList<>();



    //private CheckoutCartController cartController;
    private iMatMiniController mainController;
    @FXML public FlowPane checkoutCart;
    @FXML public FlowPane cartControl;
    @FXML public FlowPane browseFlowpane;
    @FXML public FlowPane browseSubFlowpane;



    @FXML public AnchorPane boughtPane;

    @FXML public List<ProductCategory> selectedSub;

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


        navbarController.homeNav.setStyle("-fx-text-fill: orange");
        String iconPath = "bilder/home.png";
        Image icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPath));
        currentTabImage.setImage(icon);
    }

    public void resetSubCategories(){
        for (SubCategory subCategory :subCategoriesActive) {
            subCategory.subCategoryLabel.getStyleClass().clear();
            subCategory.subCategoryLabel.getStyleClass().add("minknapp");
            subCategory.subCategoryLabel.getStyleClass().add("inderPog");
        }
    }

    public void resetCategories(){
        for (Category item: browseCategories) {
            item.clearStyle();
            item.categoryLabel.getStyleClass().add("minknapp");
            item.categoryLabel.getStyleClass().add("inderPog");
        }
    }

    private void createCategories(){
        List<ProductCategory> subVeggie = new ArrayList<>();
        subVeggie.add(ProductCategory.VEGETABLE_FRUIT);
        subVeggie.add(ProductCategory.ROOT_VEGETABLE);
        subVeggie.add(ProductCategory.CABBAGE);

        List<ProductCategory> fruitBerries = new ArrayList<>();
        fruitBerries.add(ProductCategory.FRUIT);
        fruitBerries.add(ProductCategory.BERRY);
        fruitBerries.add(ProductCategory.CITRUS_FRUIT);
        fruitBerries.add(ProductCategory.EXOTIC_FRUIT);
        fruitBerries.add(ProductCategory.MELONS);

        List<ProductCategory> breads = new ArrayList<>();
        breads.add(ProductCategory.BREAD);

        List<ProductCategory> drinks = new ArrayList<>();
        drinks.add(ProductCategory.COLD_DRINKS);
        drinks.add(ProductCategory.HOT_DRINKS);

        List<ProductCategory> dairies = new ArrayList<>();
        dairies.add(ProductCategory.DAIRIES);

        List<ProductCategory> meats = new ArrayList<>();
        meats.add(ProductCategory.MEAT);
        meats.add(ProductCategory.FISH);

        List<ProductCategory> skafferi = new ArrayList<>();
        skafferi.add(ProductCategory.FLOUR_SUGAR_SALT);
        skafferi.add(ProductCategory.PASTA);
        skafferi.add(ProductCategory.POTATO_RICE);
        skafferi.add(ProductCategory.POTATO_RICE);

        List<ProductCategory> spices = new ArrayList<>();
        spices.add(ProductCategory.HERB);

        List<ProductCategory> candy = new ArrayList<>();
        candy.add(ProductCategory.SWEET);

        Category showAll = new Category("Visa Alla", this);
        Category gronsaker = new Category("Grönsaker", subVeggie, this);
        Category fruitandberries = new Category("Frukt och Bär", fruitBerries, this);
        Category bread = new Category("Bröd", breads, this);
        Category drink = new Category("Drycker", drinks, this);
        Category dairy = new Category("Mejeri", dairies, this);
        Category meat = new Category("Kött och Fisk", meats, this);
        Category skafferiProdukter = new Category("Skafferi", skafferi, this);
        Category kryddor = new Category("Kryddor", spices, this);
        Category godis = new Category("Godis", candy, this);

        browseCategories = new ArrayList<>();
        browseCategories.add(showAll);
        browseCategories.add(gronsaker);
        browseCategories.add(fruitandberries);
        browseCategories.add(bread);
        browseCategories.add(drink);
        browseCategories.add(dairy);
        browseCategories.add(meat);
        browseCategories.add(skafferiProdukter);
        browseCategories.add(kryddor);
        browseCategories.add(godis);

        browseFlowpane.getChildren().clear();
        resetSubCategories();

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

    public void updateSubCategories(List<ProductCategory> categories) {
        resetSubCategories();
        browseSubFlowpane.getChildren().clear();
        subCategoriesActive = new ArrayList<>();

        for (ProductCategory category : categories) {

            SubCategory addedCategory= new SubCategory(category, this);
            subCategoriesActive.add(addedCategory);
            browseSubFlowpane.getChildren().add(addedCategory);
        }

    }

    public void updateProductList() {

        productsFlowPane.getChildren().clear();

        for (Product product : model.getProducts()) {
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

    public void updateProductListCategory(ProductCategory category, List<ProductCategory> subCategories) {
        productsFlowPane.getChildren().clear();
        List<Product> categorizedProducts = model.getProductCategories(category);

        if(subCategories != null) {
            for (ProductCategory subCategory: subCategories) {
                categorizedProducts.addAll(model.getProductCategories(subCategory));
            }

        }

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

        String validText = validthru.getText();
        String[] validSplit;
        validSplit = validText.split("/");

        model.getCreditCard().setValidYear(Integer.parseInt(validSplit[0]));
        model.getCreditCard().setValidMonth(Integer.parseInt(validSplit[1]));
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

        cartPane.toBack();
        mainHome.toFront();
        boughtPane.toFront();
    }

    public void bringControlFront() {
        String totCost = String.format("%.2f",model.getShoppingCart().getTotal());
        checkoutTotalPrice.setText("Summa: " + totCost + "kr");
        checkoutTotalPrice2.setText("Summa: " + totCost + "kr");
        timeintervalLabel.setText(timeinterval);
        deliveryDate.setText(timeofDay);

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

    @FXML AnchorPane date1;
    @FXML AnchorPane date2;
    @FXML AnchorPane date3;
    @FXML AnchorPane date4;
    @FXML AnchorPane date5;
    @FXML AnchorPane date6;
    @FXML AnchorPane date7;
    @FXML AnchorPane date8;


    private void resetDates(){
        date1.getStyleClass().clear();
        date1.getStyleClass().add("buttonPressed");
        date1.getStyleClass().add("inderPog");



        //subCategoryLabel.getStyleClass().add("buttonPressed");
        //subCategoryLabel.getStyleClass().add("inderPog");
        //subCategoryLabel.setStyle("-fx-font-size: 35");
    }

    @FXML private AnchorPane timeslot1;
    @FXML private AnchorPane timeslot2;
    @FXML void chooseBefore(){
        timeinterval = "07:00-12:00";

        resetTimeslots();
        timeslot1.setStyle("-fx-background-color:  #332f68; -fx-background-radius: 10");
        timeslot1.getChildren().get(0).setStyle("-fx-text-fill: white");
        timeslot1.getChildren().get(1).setStyle("-fx-text-fill: white");
    }

    @FXML void chooseAfter(){
        timeinterval = "13:00-17:00";

        resetTimeslots();
        timeslot2.setStyle("-fx-background-color:  #332f68; -fx-background-radius: 10");
        timeslot2.getChildren().get(0).setStyle("-fx-text-fill: white");
        timeslot2.getChildren().get(1).setStyle("-fx-text-fill: white");
    }

    private void resetTimeslots(){
        timeslot1.setStyle("-fx-background-color:  white; -fx-background-radius: 10");
        timeslot1.getChildren().get(0).setStyle("-fx-text-fill: black");
        timeslot1.getChildren().get(1).setStyle("-fx-text-fill: black");


        timeslot2.setStyle("-fx-background-color:  white; -fx-background-radius: 10");
        timeslot2.getChildren().get(0).setStyle("-fx-text-fill: black");
        timeslot2.getChildren().get(1).setStyle("-fx-text-fill: black");
    }

    private void resetAllDatesCss(){
        date1.setStyle("-fx-background-color:  white; -fx-background-radius: 10");
        date1.getChildren().get(0).setStyle("-fx-text-fill: black");
        date1.getChildren().get(1).setStyle("-fx-text-fill: black");

        date2.setStyle("-fx-background-color:  white; -fx-background-radius: 10");
        date2.getChildren().get(0).setStyle("-fx-text-fill: black");
        date2.getChildren().get(1).setStyle("-fx-text-fill: black");

        date3.setStyle("-fx-background-color:  white; -fx-background-radius: 10");
        date3.getChildren().get(0).setStyle("-fx-text-fill: black");
        date3.getChildren().get(1).setStyle("-fx-text-fill: black");

        date4.setStyle("-fx-background-color:  white; -fx-background-radius: 10");
        date4.getChildren().get(0).setStyle("-fx-text-fill: black");
        date4.getChildren().get(1).setStyle("-fx-text-fill: black");

        date5.setStyle("-fx-background-color:  white; -fx-background-radius: 10");
        date5.getChildren().get(0).setStyle("-fx-text-fill: black");
        date5.getChildren().get(1).setStyle("-fx-text-fill: black");

        date6.setStyle("-fx-background-color:  white; -fx-background-radius: 10");
        date6.getChildren().get(0).setStyle("-fx-text-fill: black");
        date6.getChildren().get(1).setStyle("-fx-text-fill: black");

        date7.setStyle("-fx-background-color:  white; -fx-background-radius: 10");
        date7.getChildren().get(0).setStyle("-fx-text-fill: black");
        date7.getChildren().get(1).setStyle("-fx-text-fill: black");

        date8.setStyle("-fx-background-color:  white; -fx-background-radius: 10");
        date8.getChildren().get(0).setStyle("-fx-text-fill: black");
        date8.getChildren().get(1).setStyle("-fx-text-fill: black");
    }

    @FXML private void clickDateFirst(){
        timeofDay = "Måndag 30 juni";
        resetAllDatesCss();
        date1.setStyle("-fx-background-color:  #332f68; -fx-background-radius: 10");
        date1.getChildren().get(0).setStyle("-fx-text-fill: white");
        date1.getChildren().get(1).setStyle("-fx-text-fill: white");
    }

    @FXML private void clickDateSecond(){
        timeofDay = "Tisdag 31 juni";
        resetAllDatesCss();
        date2.setStyle("-fx-background-color:  #332f68; -fx-background-radius: 10");
        date2.getChildren().get(0).setStyle("-fx-text-fill: white");
        date2.getChildren().get(1).setStyle("-fx-text-fill: white");
    }

    @FXML private void clickDateThird(){
        timeofDay = "Onsdag 01 juli";
        resetAllDatesCss();
        date3.setStyle("-fx-background-color:  #332f68; -fx-background-radius: 10");
        date3.getChildren().get(0).setStyle("-fx-text-fill: white");
        date3.getChildren().get(1).setStyle("-fx-text-fill: white");
    }

    @FXML private void clickDateFourth(){
        timeofDay = "Torsdag 02 juli";
        resetAllDatesCss();
        date4.setStyle("-fx-background-color:  #332f68; -fx-background-radius: 10");
        date4.getChildren().get(0).setStyle("-fx-text-fill: white");
        date4.getChildren().get(1).setStyle("-fx-text-fill: white");
    }

    @FXML private void clickDateFith(){
        timeofDay = "Fredag 03 juli";
        resetAllDatesCss();
        date5.setStyle("-fx-background-color:  #332f68; -fx-background-radius: 10");
        date5.getChildren().get(0).setStyle("-fx-text-fill: white");
        date5.getChildren().get(1).setStyle("-fx-text-fill: white");
    }

    @FXML private void clickDateSixth(){
        timeofDay = "Lördag 04 juli";
        resetAllDatesCss();
        date6.setStyle("-fx-background-color:  #332f68; -fx-background-radius: 10");
        date6.getChildren().get(0).setStyle("-fx-text-fill: white");
        date6.getChildren().get(1).setStyle("-fx-text-fill: white");
    }

    @FXML private void clickDateSeventh(){
        timeofDay = "Söndag 05 juli";
        resetAllDatesCss();
        date7.setStyle("-fx-background-color:  #332f68; -fx-background-radius: 10");
        date7.getChildren().get(0).setStyle("-fx-text-fill: white");
        date7.getChildren().get(1).setStyle("-fx-text-fill: white");
    }

    @FXML private void clickDateEight(){
        timeofDay = "Måndag 06 juli";
        resetAllDatesCss();
        date8.setStyle("-fx-background-color:  #332f68; -fx-background-radius: 10");
        date8.getChildren().get(0).setStyle("-fx-text-fill: white");
        date8.getChildren().get(1).setStyle("-fx-text-fill: white");
    }


    @FXML private void closeBought(){
        boughtPane.toBack();
    }
}
