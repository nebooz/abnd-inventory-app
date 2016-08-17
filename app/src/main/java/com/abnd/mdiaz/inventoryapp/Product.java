package com.abnd.mdiaz.inventoryapp;

import android.graphics.Bitmap;

/**
 * Created by neboo on 17-Aug-16.
 */
public class Product {

    private String mName;
    private float mPrice;
    private int mQuantity;
    private String mSupplier;
    private Bitmap mImage;

    public Product(String name, float price, int quantity, String supplier, Bitmap image) {
        mName = name;
        mPrice = price;
        mQuantity = quantity;
        mSupplier = supplier;
        mImage = image;
    }

    public String getName(){
        return mName;
    }

    public void setName(String name){
        mName = name;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public String getSupplier() {
        return mSupplier;
    }

    public void setSupplier(String supplier) {
        mSupplier = supplier;
    }

    public Bitmap getImage() {
        return mImage;
    }

    public void setImage(Bitmap image) {
        mImage = image;
    }
}
