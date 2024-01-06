package com.oop.cw.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {

    public int availableStock = 50;
    public final int maxProducts = 50;

    public static WestminsterShoppingManager shoppingManager = null;

    public static List<Product> getShoppingProducts() {
        return shoppingProducts;
    }

    private static final List<Product> shoppingProducts = new ArrayList<Product>();

    public static int productCount = 0;

    public static final Product[] sortedElectronics = new Product[50];


    public static int elecProductCount = 0;
    public static int clothingProductCount = 0;
    public static final Product[] sortedClothing = new Product[50];


    private WestminsterShoppingManager() {
    }

    public static WestminsterShoppingManager getInstance() {

        if (shoppingManager == null) {
            synchronized (WestminsterShoppingManager.class) {
                if (shoppingManager == null) {
                    shoppingManager = new WestminsterShoppingManager();
                }
            }
        }
        return shoppingManager;
    }

    @Override
    public void addNewProduct(int type, Product product) {
        if (shoppingProducts.contains(product)) {
            System.out.println("Sorry this product is already there");
        } else if (maxProducts < shoppingProducts.size()) {
            System.out.println("Sorry already stock is full ");
        } else {
            shoppingProducts.add(product);
            availableStock -= 1;
            productCount++;
            sortedView(type, product);
        }
        System.out.println(availableStock > 0 ? "The stock left is running low: " + availableStock : "No spaces available");
    }

    @Override
    public void deleteProduct(String productID) {
        boolean elecItemDelete = false;
        if (!shoppingProducts.isEmpty()) {


            for (int i = 0; i < shoppingProducts.size(); i++) {
                if (shoppingProducts.get(i).getProductID().equals(productID)) {
                    shoppingProducts.remove(i);
                    productCount--;
                    availableStock += 1;

                    for (int j = 0; j < elecProductCount; j++) {
                        if (sortedElectronics[j].getProductID().equals(productID)) {
                            for (int k = j; k < elecProductCount; k++) {
                                sortedElectronics[k] = sortedElectronics[k + 1];
                            }
                            sortedElectronics[elecProductCount - 1] = null;
                            elecProductCount--;
                            elecItemDelete = true;
                            break;
                        }
                    }

                    if (!elecItemDelete) {
                        for (int j = 0; j < clothingProductCount; j++) {
                            if (sortedClothing[j].getProductID().equals(productID)) {
                                for (int k = j; k < clothingProductCount; k++) {
                                    sortedClothing[k] = sortedClothing[k + 1];
                                }
                                sortedClothing[clothingProductCount - 1] = null;
                                clothingProductCount--;
                                break;

                            }
                        }

                    }
                    System.out.printf("The removed product is %s product ", shoppingProducts instanceof Clothing ? "Clothing" : "Electronic");
                    System.out.println("\nThe new remaining stock: " + availableStock);

                } else if (!shoppingProducts.get(i).getProductID().equals(productID)) {
                    System.out.println("The given Product ID does not exist");
                }
            }
        } else {
            System.out.println("Sorry there is Empty stock");
        }
    }

    @Override
    public void printProductList() {

        System.out.println("--The list of products--");

        for (Product product : shoppingProducts) {
            System.out.println(product);
        }

    }

    public static void sortedView(int type, Product p) {

        String id;
        boolean enterMiddle = false;
        int prevElecProdCount = elecProductCount;
        int prevClothProdCount = clothingProductCount;
        id = p.getProductID().toUpperCase();
        if (type == 1) {
            if (elecProductCount > 0) {
                for (int j = 0; j < prevElecProdCount; j++) {
                    String existingProductID = sortedElectronics[j].getProductID().toUpperCase();
                    int shortestWordLength = Math.min(id.length(), existingProductID.length());

                    for (int z = 0; z < shortestWordLength; z++) {

                        if ((int) existingProductID.charAt(z) > (int) id.charAt(z)) {
                            for (int k = elecProductCount; k > j; k--) {
                                sortedElectronics[k] = sortedElectronics[k - 1];
                            }
                            sortedElectronics[j] = p;
                            elecProductCount++;
                            enterMiddle = true;
                            break;
                        }
                    }
                    if (enterMiddle) {
                        break;
                    }

                }

                if (!enterMiddle) {
                    sortedElectronics[prevElecProdCount] = p;
                    elecProductCount++;

                }
            } else {
                sortedElectronics[0] = p;
                elecProductCount++;
            }

        } else {
            if (clothingProductCount > 0) {
                for (int j = 0; j < prevClothProdCount; j++) {
                    String existingProductID = sortedClothing[j].getProductID().toUpperCase();
                    int shortestWordLength = Math.min(id.length(), existingProductID.length());

                    for (int z = 0; z < shortestWordLength; z++) {

                        if ((int) existingProductID.charAt(z) > (int) id.charAt(z)) {
                            for (int k = clothingProductCount; k > j; k--) {
                                sortedClothing[k] = sortedClothing[k - 1];
                            }
                            sortedClothing[j] = p;
                            clothingProductCount++;
                            enterMiddle = true;
                            break;
                        }
                    }
                    if (enterMiddle) {
                        break;
                    }

                }

                if (!enterMiddle) {

                    sortedClothing[prevClothProdCount] = p;
                    clothingProductCount++;

                }
            } else {
                sortedClothing[0] = p;
                clothingProductCount++;
            }
        }

    }
    public void saveData() {   //file writing  method
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Text.txt"));
            for (Product j : shoppingProducts) {
                if (j != null) {
                    writer.write(j.toString());
                    writer.newLine();
                }
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("File not Found ");
        }
    }

    public static void loadData() {
        try {
            FileReader file = new FileReader("Text.txt");
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String objectLine = input.nextLine();
                String[] keys = objectLine.split(" ");
                int type = keys[0].equals("Electronics") ? 1 : 2;
                if (type == 1) {
                    Electronics product = new Electronics(keys[2].split("=")[1], keys[3].split("=")[1], Integer.parseInt(keys[4].split("=")[1]),
                            Float.parseFloat(keys[5].split("=")[1]), keys[6].split("=")[1], Integer.parseInt(keys[7].split("=")[1]));
                    shoppingProducts.add(product);
                    productCount++;
                    sortedView(1, product);
                } else {
                    Clothing product = new Clothing(keys[2].split("=")[1], keys[3].split("=")[1], Integer.parseInt(keys[4].split("=")[1]), Float.parseFloat(keys[5].split("=")[1]), Float.parseFloat(keys[6].split("=")[1]), keys[7].split("=")[1]);
                    shoppingProducts.add(product);
                    productCount++;
                    sortedView(2, product);
                }


            }
            input.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public static Product search(int type, String productID) {

        if (type == 1) {
            for (Product p : sortedElectronics)
                if (p.getProductID().equals(productID)) {
                    return p;
                }


        } else if (type == 2) {
            for (Product p : sortedClothing)
                if (p.getProductID().equals(productID)) {
                    return p;
                }

        }

        return null;
    }


    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public static Product[] getSortedElectronics() {
        return sortedElectronics;
    }

    public static Product[] getSortedClothing() {
        return sortedClothing;
    }
}
