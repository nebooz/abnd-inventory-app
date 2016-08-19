package com.abnd.mdiaz.inventoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();
        Product currentProduct = (Product) intent.getSerializableExtra("product");

        ImageView detailProductImage = (ImageView) findViewById(R.id.dt_pro_image);
        TextView detailProductName = (TextView) findViewById(R.id.dt_pro_name);
        TextView detailProductPrice = (TextView) findViewById(R.id.dt_pro_price);
        TextView detailProductQuantity = (TextView) findViewById(R.id.dt_pro_quantity);
        TextView detailProductSupplier = (TextView) findViewById(R.id.dt_pro_supplier);

        detailProductImage.setImageResource(currentProduct.getImageId());
        detailProductName.setText(currentProduct.getName());
        detailProductSupplier.setText(this.getString(R.string.supplied_by) + currentProduct.getSupplier());
        detailProductQuantity.setText(this.getString(R.string.quantity) + String.valueOf(currentProduct.getQuantity()));
        detailProductPrice.setText("$" + String.valueOf(currentProduct.getPrice()));

    }
}
