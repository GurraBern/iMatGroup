/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

/**
 *
 * @author oloft
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;


/**
 * Wrapper around the IMatDataHandler. The idea is that it might be useful to
 * add an extra layer that can provide special features
 *
 */
public class Model {

    public List<ShoppingCart> savedShoppingCarts;
    private static Model instance = null;
    private IMatDataHandler iMatDataHandler;

     private final ArrayList<String> availableCardTypes = new ArrayList<String>(Arrays.asList("MasterCard", "Visa"));
     private final ArrayList<String> months = new ArrayList<String>(Arrays.asList("1", "2","3", "4", "5", "6"));
     private final ArrayList<String> years = new ArrayList<String>(Arrays.asList("19", "20", "21", "22", "23", "24", "25"));
    /**
     * Constructor that should never be called, use getInstance() instead.
     */
    protected Model() {
        // Exists only to defeat instantiation.
    }

    /**
     * Returns the single instance of the Model class.
     */
    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
            instance.init();
        }
        return instance;
    }

    private void init() {

        iMatDataHandler = IMatDataHandler.getInstance();

    }

    public void saveShoppingCart(ShoppingCart shoppingCart){
        this.savedShoppingCarts.add(shoppingCart);
    }

    public List<Product> getProducts() {
        return iMatDataHandler.getProducts();
    }

    public Product getProduct(int idNbr) {
        return iMatDataHandler.getProduct(idNbr);
    }
    
    public List<Product> findProducts(java.lang.String s) {
        return iMatDataHandler.findProducts(s);
    }

    public Image getImage(Product p) {
        return iMatDataHandler.getFXImage(p);
    }

    public Image getImage(Product p, double width, double height) {
        return iMatDataHandler.getFXImage(p, width, height);
    }

    public void addToShoppingCart(Product p) {
        ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();

        ShoppingItem item = new ShoppingItem(p);

        //for (Product item : shoppingCart.getItems()) {

        //}


        Model.getInstance().getShoppingCart().addItem(item);
        //shoppingCart.addProduct(p);
    }

    public void addSingleItem(Product product) {

        System.out.println("Add " + product.getName());

        int index = 0;
        double test = 0;
        boolean inCart = false;
        for (ShoppingItem item: this.getShoppingCart().getItems()) {
            if(item.getProduct().getProductId() == product.getProductId()){
                //Already exists
                test = (item.getAmount() + 1);
                System.out.println("Current count===== " + test);
                item.setAmount(test);
                inCart = true;
            }
            else {
                if(index >= this.getShoppingCart().getItems().size()){
                    inCart = false;
                }
            }
            index++;
        }
        this.addToShoppingCart(product);

        //if(!inCart){
        //}

        //TODO lägg tillbaka knapp om varor är = 0 sätt opacity = 1 visa plus och minus knappar
        //om det redan finns en produkt!

        //addItemButton.setOpacity(0);
        //addItemButton.setDisable(true);


    }

    //TODO cant remove if still zero, more work needed, basic funciton works
    public void removeFromShoppingCart(Product p) {
        int index = 0;
        ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();
        for (ShoppingItem item: shoppingCart.getItems()) {
            if(item.getProduct().getProductId() == p.getProductId()){
                shoppingCart.removeItem(index);
                break;
            }
            index++;
        }
    }

    public List<String> getCardTypes() {
        return availableCardTypes;
    }
    
    public List<String> getMonths() {
        return months;
    }
    
    public List<String> getYears() {
        return years;
    }
    
    public CreditCard getCreditCard() {
        return iMatDataHandler.getCreditCard();
    }
    
    public Customer getCustomer() {
        return iMatDataHandler.getCustomer();
    }

    public ShoppingCart getShoppingCart() {
        return iMatDataHandler.getShoppingCart();
    }

    public void clearShoppingCart() {

        iMatDataHandler.getShoppingCart().clear();
        getCustomer().getAddress();
    }

    public void placeOrder() {

        iMatDataHandler.placeOrder();

    }

    
    public int getNumberOfOrders() {

        return iMatDataHandler.getOrders().size();
    }



    public void shutDown() {
        iMatDataHandler.shutDown();
    }
}
