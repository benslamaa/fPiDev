package panier.cart;

import panier.home.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private static ShoppingCart INSTANCE;

    private Map<String,CartEntry>entries;
    private ShoppingCart() {
        this.entries = new HashMap<>();
    }
    public static ShoppingCart getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShoppingCart();
        }
        return INSTANCE;
    }




    public void addProduct(String productName) {
        CartEntry productEntry = entries.get(productName.toUpperCase());
        if (productEntry != null) {
            productEntry.increaseQuantity();
        } else {
            try {
                Product product = Product.valueOf(productName.toUpperCase());
                CartEntry entry = new CartEntry(product, 1);
                entries.put(productName.toUpperCase(), entry);
            } catch (IllegalArgumentException e) {
                // Handle the case where Product.valueOf throws an exception (invalid product name)
                System.err.println("Invalid product name: " + productName);
            }
        }
    }

    public void removeProduct(String productName) {
        CartEntry productEntry = entries.get(productName.toUpperCase());
        if (productEntry != null) {
            productEntry.decreaseQuantity();
        }
    }

    public int getQuantity(String productName) {
        CartEntry entry = entries.get(productName.toUpperCase());
        if (entry!=null){
            return entry.getQuantity();
        }
        return 0;
    }

    public float calculTotal() {
        float total = 0;
        for (CartEntry entry : entries.values()) {
            float entryCost = entry.getProduct().getPrice()*entry.getQuantity();
            total = total + entryCost;
        }
        return total;
    }
    public List<CartEntry> getEntries(){
        return new ArrayList<>(entries.values());
    }
}


