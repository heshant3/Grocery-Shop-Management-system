package com.oop.cw.model;

import java.io.Serializable;

public abstract class Product implements Serializable{
    private String productID;
    private String productName;

    private int numberOfAvailableItem;

    private float productPrice;

    public Product(String productID, String productName, int numberOfAvailableItem, float productPrice){
        setProductID(productID);
        setProductName(productName);
        setNumberOfAvailableItem(numberOfAvailableItem);
        setProductPrice(productPrice);
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNumberOfAvailableItem() {
        return numberOfAvailableItem;
    }

    public void setNumberOfAvailableItem(int numberOfAvailableItem) {
        this.numberOfAvailableItem = numberOfAvailableItem;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product: " + "productID=" + productID
                + " " + "productName=" + productName
                + " " + "numberOfAvailableItem=" + numberOfAvailableItem
                +" "+ "productPrice=" + productPrice ;
    }
//    @Override
//    public int compareTo(Product p){
//        return (this.productID.compareTo(p.productID));
//    }
}
