package com.abnd.mdiaz.inventoryapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ProductCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_creation);
    }

    public void addProduct(View view) {

        DatabaseHelper dbHelp = new DatabaseHelper(this);

        EditText productName = (EditText) findViewById(R.id.edit_pro_name);
        EditText productPrice = (EditText) findViewById(R.id.edit_pro_price);
        EditText productQuantity = (EditText) findViewById(R.id.edit_pro_quantity);
        EditText productSupplier = (EditText) findViewById(R.id.edit_pro_supplier);

        dbHelp.addProduct(-1, productName.getText().toString(),
                Float.valueOf(productPrice.getText().toString()),
                Integer.valueOf(productQuantity.getText().toString()),
                productSupplier.getText().toString(),
                R.drawable.squirtle);

        this.finish();
    }
}
