package com.oop.cw.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.oop.cw.model.ConsoleApplication.selectOptions;
import static com.oop.cw.model.ConsoleApplication.setLoginInfo;

public class WelcomePage extends JFrame implements ActionListener {

    JLabel label;
    JButton button;
    JButton button2;


    public WelcomePage() {


        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        label = new JLabel("Westminster shopping center");
        p1.add(label);
        button= new JButton("Go to login Page");
        p2.add(button);
        button2= new JButton("Go to shopping center page");
        p2.add(button2);
        button.addActionListener(this::actionPerformed);
        button2.addActionListener(this::actionPerformed);


        FlowLayout f1 = new FlowLayout();
        p1.setLayout(f1);
        f1.setHgap(60);
        f1.setVgap(50);
        FlowLayout f2 = new FlowLayout();
        p2.setLayout(f2);
        f2.setHgap(100);
        f2.setVgap(10);

        add(p1);
        add(p2);
        setTitle("Westminster Shopping Center");
        setSize(600,500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(button)){
            setVisible(false);
            setLoginInfo();
            selectOptions();
        }else if(e.getSource().equals(button2)){
            ShoppingCenterView shoppingCenterView = new ShoppingCenterView();
            setVisible(false);

        }

    }
}
