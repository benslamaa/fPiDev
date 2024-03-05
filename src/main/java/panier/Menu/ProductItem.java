package panier.Menu;

public class ProductItem {
    private String productName;
    private int productQuantity;
    private double price;

    public ProductItem(String productName, int productQuantity, double price) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.price = price;
    }

    // Getters and setters...

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
