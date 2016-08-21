package com.abnd.mdiaz.inventoryapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetail extends AppCompatActivity {

    Product currentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        currentProduct = (Product) getIntent().getSerializableExtra("product");

        ImageView detailProductImage = (ImageView) findViewById(R.id.dt_pro_image);
        TextView detailProductName = (TextView) findViewById(R.id.dt_pro_name);
        TextView detailProductPrice = (TextView) findViewById(R.id.dt_pro_price);
        TextView detailProductQuantity = (TextView) findViewById(R.id.dt_pro_quantity);
        TextView detailProductSupplier = (TextView) findViewById(R.id.dt_pro_supplier);

        detailProductImage.setImageBitmap(ImageTools.imageProcess(currentProduct.getImageUri()));
        detailProductName.setText(currentProduct.getName());
        detailProductSupplier.setText(this.getString(R.string.supplied_by) + currentProduct.getSupplier());
        detailProductQuantity.setText(this.getString(R.string.quantity) + String.valueOf(currentProduct.getQuantity()));
        detailProductPrice.setText(getString(R.string.unit_price) + String.valueOf(currentProduct.getPrice()));

    }

    public void deleteProduct(View view) {
        DatabaseHelper dbHelp = new DatabaseHelper(this);
        dbHelp.deleteProduct(currentProduct.getId());
        this.finish();
    }

    public void orderMore(View view) {
        String[] emails = new String[]{getString(R.string.email_address)};
        String subject = getString(R.string.request_for_a_new) + currentProduct.getName()
                + getString(R.string.shipment);
        String text = getString(R.string.hello) + getString(R.string.request_main)
                + currentProduct.getName() + getString(R.string.units) + currentProduct.getSupplier() + ".";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, emails);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void backToList(View view) {
        this.finish();
    }
}
