package com.oop.cw.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> listOfProducts;
    private float totalCost;
    public ShoppingCart(){
        this.listOfProducts = new ArrayList<>();
    }

    public void addProduct(Product product){
        listOfProducts.add(product);
    }

    public void removeProduct(Product product){
        listOfProducts.remove(product);
    }

    public void calculateTotal(){

        for (Product product: listOfProducts){
            this.totalCost=this.totalCost+product.getProductPrice();
        }

    }


}
