package com.oop.cw.model;

public class Clothing extends Product{
    private float size;
    private String color;

    public Clothing(String productID, String productName, int numberOfAvailableItem, float productPrice, float size, String color) {
        super(productID,productName,numberOfAvailableItem,productPrice);
        setSize(size);
        setColor(color);
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Clothing"+" "+super.toString()+" "+"Size="+size+" "+"Color="+color;

    }
}
