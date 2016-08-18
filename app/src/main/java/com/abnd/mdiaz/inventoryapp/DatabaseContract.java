package com.abnd.mdiaz.inventoryapp;

import android.provider.BaseColumns;

/**
 * Created by neboo on 16-Aug-16.
 */
public final class DatabaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DatabaseContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class Products implements BaseColumns {
        public static final String TABLE_NAME = "products";
        public static final String COLUMN_NAME_NULLABLE = null;
        public static final String COLUMN_NAME_PRODUCT_NAME = "name";
        public static final String COLUMN_NAME_PRODUCT_PRICE = "price";
        public static final String COLUMN_NAME_PRODUCT_QUANTITY = "quantity";
        public static final String COLUMN_NAME_PRODUCT_SUPPLIER = "supplier";
        public static final String COLUMN_NAME_PRODUCT_IMAGEID = "imageId";
    }
}
