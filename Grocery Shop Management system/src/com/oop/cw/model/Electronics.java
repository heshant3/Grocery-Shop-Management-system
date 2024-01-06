package com.oop.cw.model;

public class Electronics extends Product{
    private String brand;
    private int warrantyPeriod;

    public Electronics(String productID,String productName,int numberOfAvailableItem,float productPrice,String brand,int warrantyPeriod){
        super(productID,productName,numberOfAvailableItem,productPrice);
        setBrand(brand);
        setWarrantyPeriod(warrantyPeriod);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String toString(){
        return "Electronics "+super.toString()+" "+"brand="+brand+" "+"warrantyPeriod="+warrantyPeriod;
    }
}
