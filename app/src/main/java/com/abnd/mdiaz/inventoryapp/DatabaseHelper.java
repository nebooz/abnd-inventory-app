package com.abnd.mdiaz.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static Context mContext;

    private static final String TEXT_TYPE = " TEXT";
    private static final String REAL_TYPE = " REAL";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseContract.Products.TABLE_NAME + " (" +
                    DatabaseContract.Products._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.Products.COLUMN_NAME_PRODUCT_NAME + TEXT_TYPE + COMMA_SEP +
                    DatabaseContract.Products.COLUMN_NAME_PRODUCT_PRICE + REAL_TYPE + COMMA_SEP +
                    DatabaseContract.Products.COLUMN_NAME_PRODUCT_QUANTITY + INT_TYPE + COMMA_SEP +
                    DatabaseContract.Products.COLUMN_NAME_PRODUCT_SUPPLIER + TEXT_TYPE + COMMA_SEP +
                    DatabaseContract.Products.COLUMN_NAME_PRODUCT_IMAGEID + INT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.Products.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Products.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void deleteDatabase() {
        mContext.deleteDatabase(DATABASE_NAME);
    }

    public boolean addProduct(int id, String name, float price, int quantity, String supplier, int imageId) {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Products._ID, id);
        values.put(DatabaseContract.Products.COLUMN_NAME_PRODUCT_NAME, name);
        values.put(DatabaseContract.Products.COLUMN_NAME_PRODUCT_PRICE, price);
        values.put(DatabaseContract.Products.COLUMN_NAME_PRODUCT_QUANTITY, quantity);
        values.put(DatabaseContract.Products.COLUMN_NAME_PRODUCT_SUPPLIER, supplier);
        values.put(DatabaseContract.Products.COLUMN_NAME_PRODUCT_IMAGEID, imageId);

        long newRowId;
        newRowId = db.insert(
                DatabaseContract.Products.TABLE_NAME,
                DatabaseContract.Products.COLUMN_NAME_NULLABLE,
                values);

        return newRowId != -1;
    }

    //Return all data
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        // Gets the data repository in read mode
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor selectAll = db.rawQuery("SELECT * FROM " + DatabaseContract.Products.TABLE_NAME, null);
        while (selectAll.moveToNext()) {

            String productName = selectAll.getString(selectAll.getColumnIndex("name"));
            float productPrice = selectAll.getFloat(selectAll.getColumnIndex("price"));
            int productQuantity = selectAll.getInt(selectAll.getColumnIndex("quantity"));
            String productSupplier = selectAll.getString(selectAll.getColumnIndex("supplier"));

            int productImageId;
            if (selectAll.isNull(selectAll.getColumnIndex("imageId"))) {
                productImageId = R.drawable.squirtle;
            } else {
                productImageId = selectAll.getInt(selectAll.getColumnIndex("imageId"));
            }

            products.add(new Product(productName, productPrice, productQuantity, productSupplier, productImageId));

        }

        return products;
    }

    public boolean updateQuantity(int id, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Products.COLUMN_NAME_PRODUCT_QUANTITY, quantity);

        String selection = DatabaseContract.Products._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};

        db.update(DatabaseContract.Products.TABLE_NAME, values, selection, selectionArgs);

        return true;
    }

    public int deleteProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = DatabaseContract.Products._ID + " LIKE ?";

        String[] selectionArgs = {String.valueOf(id)};

        return db.delete(DatabaseContract.Products.TABLE_NAME, selection, selectionArgs);
    }

    //To meet the rubric reqs.
    public int deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(DatabaseContract.Products.TABLE_NAME, null, null);
    }
}

