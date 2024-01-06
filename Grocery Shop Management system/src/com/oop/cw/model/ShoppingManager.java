package com.oop.cw.model;

import com.oop.cw.model.Product;

import java.io.File;
import java.io.IOException;

public interface ShoppingManager {
    void addNewProduct(int type, Product product);
    void deleteProduct(String productID);
    void printProductList();

    void saveData() throws IOException;


}
