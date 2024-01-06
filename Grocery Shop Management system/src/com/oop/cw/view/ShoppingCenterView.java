package com.oop.cw.view;

import com.oop.cw.model.Clothing;
import com.oop.cw.model.Electronics;
import com.oop.cw.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Stream;

import static com.oop.cw.model.WestminsterShoppingManager.*;


public class ShoppingCenterView extends JFrame implements ActionListener {
    JComboBox comboBox;
    JLabel label;
    JButton button;

    JTable table;
    JPanel p1;
    JPanel p3;

    JButton searchButton;

    JButton viewbutton;
    JLabel selectedProduct;
    JLabel productIDKey ;
    JLabel productIDValue ;
    JLabel productCategoryKey ;
    JLabel productCategoryValue;
    JLabel NameKey ;
    JLabel NameValue ;
    JLabel ColorKey ;
    JLabel ColorValue ;
    JLabel itemsKey ;
    JLabel itemsValue ;

    public ShoppingCenterView() {

        String[] items = {"All", "Electronics", "Cloths"};

        p1 = new JPanel();
        label = new JLabel("Select Product Category");
        p1.add(label);

        comboBox = new JComboBox(items);
        comboBox.addActionListener(this);
        p1.add(comboBox);


        button = new JButton("ShoppingCart");
        p1.add(button, BorderLayout.EAST);


        JPanel p2 = new JPanel();
        table = new JTable();
        p2.add(table);
        viewbutton = new JButton("View Details");
        viewbutton.setVisible(false);
        viewbutton.addActionListener(this::actionPerformed);
        p2.add(viewbutton, BorderLayout.WEST);

        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.WEST);

         p3 = new JPanel();
        selectedProduct = new JLabel("selectedProduct-Details");
        productIDKey = new JLabel("productID:");
         productIDValue = new JLabel();
        productCategoryKey = new JLabel("category:");
         productCategoryValue = new JLabel();
         NameKey = new JLabel("Name:");
         NameValue = new JLabel();
         ColorKey = new JLabel("Color:");
         ColorValue = new JLabel();
         itemsKey = new JLabel("item Available:");
        itemsValue = new JLabel();
        p3.add(selectedProduct);
        p3.add(productIDKey);
        p3.add(productIDValue);
        p3.add(productCategoryKey);
        p3.add(productCategoryValue);
        p3.add(NameKey);
        p3.add(NameValue);
        p3.add(ColorKey);
        p3.add(ColorValue);
        p3.add(itemsKey);
        p3.add(itemsValue);




        setTitle("Westminster Shopping Center");
        setSize(1080, 720);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            if (comboBox.getSelectedItem().equals("All")) {
                String[] columns = {"Product ID", "ProductName", "Category", "ProductPrice", "Other Information"};
                List<Product> shoppingProducts = getShoppingProducts();
                String[][] data = new String[shoppingProducts.size() + 1][columns.length];
                for (int i = 0; i < shoppingProducts.size(); i++) {
                    data[i][0] = shoppingProducts.get(i).getProductID();
                    data[i][1] = shoppingProducts.get(i).getProductName();
                    data[i][3] = String.valueOf(shoppingProducts.get(i).getProductPrice());
                    if (shoppingProducts.get(i).getClass().getName().contains("Electronics")) {
                        data[i][2] = "Electronics";
                        Electronics pro = (Electronics) search(1, shoppingProducts.get(i).getProductID());
                        if (pro != null) {
                            data[i][4] = pro.getBrand() + " , " + pro.getWarrantyPeriod();
                        }

                    } else if (shoppingProducts.get(i).getClass().getName().contains("Clothing")) {
                        data[i][2] = "Clothing";
                        Clothing pro = (Clothing) search(2, shoppingProducts.get(i).getProductID());
                        if (pro != null) {
                            data[i][4] = pro.getSize() + " , " + pro.getColor();
                        }


                    }
                    DefaultTableModel model = new DefaultTableModel(data, columns);
                    table.setModel(model);
                    JScrollPane scroll = new JScrollPane(table);
                    p1.add(scroll);
                }


            } else if (comboBox.getSelectedItem().equals("Electronics")) {
                String[] columns = {"Product ID", "ProductName", "Category", "ProductPrice", "Other Information"};
                List<Product> shoppingProducts = getShoppingProducts();
                String[][] data = new String[elecProductCount][columns.length];
                int j = 0;
                for (int i = 0; i < shoppingProducts.size(); i++) {

                    if (shoppingProducts.get(i).getClass().getName().contains("Electronics")) {
                        data[j][0] = shoppingProducts.get(i).getProductID();
                        data[j][1] = shoppingProducts.get(i).getProductName();
                        data[j][3] = String.valueOf(shoppingProducts.get(i).getProductPrice());
                        data[j][2] = "Electronics";
                        Electronics pro = (Electronics) search(1, shoppingProducts.get(i).getProductID());
                        if (pro != null) {
                            data[j][4] = pro.getBrand() + " , " + pro.getWarrantyPeriod();
                        }
                        j++;
                    }

                    DefaultTableModel model = new DefaultTableModel(data, columns);
                    table.setModel(model);
                    JScrollPane scroll = new JScrollPane(table);
                    p1.add(scroll);
                }
            } else if (comboBox.getSelectedItem().equals("Cloths")) {
                String[] columns = {"Product ID", "ProductName", "Category", "ProductPrice", "Other Information"};
                List<Product> shoppingProducts = getShoppingProducts();
                String[][] data = new String[clothingProductCount][columns.length];
                int j = 0;
                for (int i = 0; i < shoppingProducts.size(); i++) {
                    if (shoppingProducts.get(i).getClass().getName().contains("Clothing")) {
                        data[j][0] = shoppingProducts.get(i).getProductID();
                        data[j][1] = shoppingProducts.get(i).getProductName();
                        data[j][3] = String.valueOf(shoppingProducts.get(i).getProductPrice());
                        data[j][2] = "Clothing";
                        Clothing pro = (Clothing) search(2, shoppingProducts.get(i).getProductID());
                        if (pro != null) {
                            data[j][4] = pro.getSize() +" , " + pro.getColor();
                        }
                        j++;
                    }
                    DefaultTableModel model = new DefaultTableModel(data, columns);
                    table.setModel(model);
                    JScrollPane scroll = new JScrollPane(table);
                    p1.add(scroll);
                }

            }
                viewbutton.setVisible(true);
        }
        if (e.getSource()==viewbutton){
            if(table.getSelectedRowCount() ==1){
                List<Product> shoppingProducts = getShoppingProducts();
                int row = table.getSelectedRow();
                Product pro = null;
                for (Product p : shoppingProducts) {
                    if (p.getProductID().equals(table.getModel().getValueAt(row, 0))) {
                        pro = p;
                    }
                }
                if(pro.getClass().getName().contains("Electronics")){
                   Electronics elec = (Electronics) search(1, pro.getProductID());
                    if(elec != null){
                        productIDValue.setText(elec.getProductID());
                    }

                }

            }
            p3.setVisible(true);
            this.add(p3);
        }


    }
}


