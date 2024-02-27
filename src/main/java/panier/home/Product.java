package panier.home;

public enum Product {
    APPLE("apple.jpg",0.55f),
    MILK("milk.jpg",0.78f),
    JUICE("juice.jpg",0.56f),
    LETTUCE("lettuce.jpg",0.78f);
    //EMPTY("",0.0f);

    private String imageFile;
    private float price;

    Product(String imageFile,float price){
        this.imageFile = imageFile;
        this.price = price;
    }

    public String getImageFile() {
        return imageFile;
    }

    public float getPrice() {
        return price;
    }
}