package com.oop.cw.view;

import javax.swing.*;

public class ShoppingCenterViewe extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public ShoppingCenterViewe() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }
}
