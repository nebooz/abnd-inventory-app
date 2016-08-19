package com.abnd.mdiaz.inventoryapp;

import java.io.Serializable;

/**
 * Created by neboo on 17-Aug-16.
 */
public class Product implements Serializable{

    private int mId;
    private String mName;
    private float mPrice;
    private int mQuantity;
    private String mSupplier;
    private int mImageId;

    public Product(String name, float price, int quantity, String supplier, int imageId) {
        mId = 0;
        mName = name;
        mPrice = price;
        mQuantity = quantity;
        mSupplier = supplier;
        mImageId = imageId;
    }

    public Product(int id, String name, float price, int quantity, String supplier, int imageId) {
        mId = id;
        mName = name;
        mPrice = price;
        mQuantity = quantity;
        mSupplier = supplier;
        mImageId = imageId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
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

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        mImageId = imageId;
    }
}
