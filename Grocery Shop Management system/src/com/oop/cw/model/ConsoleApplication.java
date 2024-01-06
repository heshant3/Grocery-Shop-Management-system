package com.oop.cw.model;



import com.oop.cw.view.WelcomePage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import static com.oop.cw.model.WestminsterShoppingManager.loadData;

public class ConsoleApplication {
    static User user = null;
    static HashMap<String, String> loginInfo = new HashMap<String, String>();
    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new WelcomePage();
        loadData();

    }

    public  static void selectOptions(){
        while (true) {

            System.out.println("\n **********************************************\n * Welcome to the Westminster Shopping Center *\n **********************************************\n");

            String userName = getStringInput("Please enter your username in the field provided below: ");
            String userPass = getStringInput("Please enter your Password in the field provided below: ");
            if (checkLoginInfo(userName, userPass)) {
                break;
            }
        }

        if (user != null) {
            String option = "";
            boolean conditionLoop = true;
            while (conditionLoop) {

                displayMenu();
                System.out.print("Please enter your option: ");
                option = userInput.next();
                option = option.toUpperCase();

                switch (option) {
                    case "1":
                    case "A":
                        addProduct();
                        break;
                    case "2":
                    case "D":
                        deleteProduct();
                        break;
                    case "3":
                    case "P":
                        printProducts();
                        break;
                    case "4":
                    case "E":
                        System.out.println("Exit");
                        user.getShoppingManager().saveData();
                        conditionLoop = false;
                        break;
                    default:
                        System.out.println("Invalid option please enter again.");
                }
            }
        }
    }
    public static void displayMenu() {


        System.out.println("Please enter (1 or A) to Add a Products");
        System.out.println("Please enter (2 or D) to Delete a Products");
        System.out.println("Please enter (3 or P) to Print the list of the Products");
        System.out.println("Please enter (4 or E) to Exit from the System");
    }

    public static void setLoginInfo() {
        loginInfo.put("Center", "Center123");
        loginInfo.put("center", "center123");
    }

    public static boolean checkLoginInfo(String userName, String userPass) {
        boolean check = false;
        if (loginInfo.containsKey(userName)) {
            if (loginInfo.get(userName).equals(userPass)) {
                user = User.getInstance();
                user.setUserName(userName);
                user.setPassword(userPass);
                user.setShoppingManager();
                check = true;
                System.out.println("\nThe login process has been completed successfully\n");
            } else {
                System.out.println("\nLogin UnSuccessful Please Enter Username and Password Again");
            }
        } else {

            System.out.println("\nUser name not found Please  Enter correct Username");
        }
        return check;
    }

    private static boolean stringValidation(String data) {
        boolean valid = true;
        if (!data.matches("^[a-zA-Z]*$")) {
            valid = false;
        }
        return valid;
    }

    private static String getStringInput(String prompt) {
        String text;
        while (true) {
            System.out.println(prompt);
            text = userInput.next();
            break;
        }
        return text;
    }

    private static String getOnlyStringInput(String prompt) {
        String text;
        while (true) {
            System.out.println(prompt);
            text = userInput.next();
            if (stringValidation(text)) {
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        return text;
    }

    private static int getIntegerInput(String prompt) {
        String data;
        char character;
        boolean valid = true;
        int intInput;

        while (true) {
            valid = true;
            System.out.println(prompt);
            data = userInput.next();

            for (int i = 0; i < data.length(); i++) {
                character = data.charAt(i);
                if (!Character.isDigit(character)) {
                    System.out.println("Invalid input");
                    valid = false;
                    break;
                }
            }
            if (valid) {
                intInput = Integer.parseInt(data);
                break;
            }
        }

        return intInput;

    }

    private static float getFloatInput(String prompt) {
        String data;
        char character;
        boolean valid = true;
        float intFloat;

        while (true) {
            valid = true;
            System.out.println(prompt);
            data = userInput.next();

            for (int i = 0; i < data.length(); i++) {
                character = data.charAt(i);
                if (!Character.isDigit(character)) {
                    System.out.println("Invalid input");
                    valid = false;
                    break;
                }
            }
            if (valid) {
                intFloat = Float.parseFloat(data);
                break;
            }
        }

        return intFloat;
    }

    public static void addProduct() {
        int productType;
        String brand = "";
        int warrantyPeriod = 0;
        float size = 0;
        String color = null;

        String ProductID = getStringInput("\nPlease enter the ProductID");
        String ProductName = getOnlyStringInput("Please enter the ProductName");
        int ProductItem = getIntegerInput("Please enter the number Of Available item");
        float ProductPrice = getFloatInput("Please enter the ProductPrice");
        productType = getIntegerInput("If you would like to add an electronic press 1 or clothing item press 2");

        if (productType == 1) {
            brand = getStringInput("Please enter the Brand");
            warrantyPeriod = getIntegerInput("Please enter the Warranty Period");
            Electronics eProduct = new Electronics(ProductID, ProductName, ProductItem, ProductPrice, brand, warrantyPeriod);
            if (user != null) {
                user.getShoppingManager().addNewProduct(productType, eProduct);
            }
        } else if (productType == 2) {
            size = getFloatInput("Please enter the size");
            color = getOnlyStringInput("Please enter the Color");
            Clothing cProduct = new Clothing(ProductID, ProductName, ProductItem, ProductPrice, size, color);
            if (user != null) {
                user.getShoppingManager().addNewProduct(productType,cProduct);
            }
        } else {
            System.out.println("Invalid input please enter again");
        }
    }

    public static void deleteProduct() {
        int productType;

        productType = getIntegerInput("If you would like to Delete an electronic press 1 or clothing item press 2");

        if (productType == 1) {
            String eProduct = getStringInput("Please enter the ProductID");
            if (user != null) {
                user.getShoppingManager().deleteProduct(eProduct);
            }
        } else if (productType == 2) {
            String cProduct = getStringInput("Please enter the ProductID");
            if (user != null) {
                user.getShoppingManager().deleteProduct(cProduct);
            }
        } else {
            System.out.println("Invalid input please enter again");
        }
    }

    public static void printProducts() {
        int productType;
        if (user.getShoppingManager() != null) {
            user.getShoppingManager().printProductList();
        }

        productType= getIntegerInput("If you would like to Print an electronic press 1 or clothing item press 2");

        if (productType == 1){
            if (user.getShoppingManager() != null) {


                for (int i = 0; i < WestminsterShoppingManager.elecProductCount; i++){
                    System.out.println(WestminsterShoppingManager.sortedElectronics[i]);
                }
            }
        } else if(productType ==2 ) {

            if (user!=null) {

                for (int i = 0; i < WestminsterShoppingManager.clothingProductCount; i++){
                    System.out.println(WestminsterShoppingManager.sortedClothing[i]);
                }
            }
        }else {
            System.out.println("Invalid input please enter again");
        }

    }


    }
