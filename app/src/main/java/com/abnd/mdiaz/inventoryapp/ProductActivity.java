package com.abnd.mdiaz.inventoryapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private List<Product> mProductList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Bitmap temp = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.ic_launcher);

        mProductList.add(new Product("Product A", 9.99f, 10, "Supplier 1", temp));
        mProductList.add(new Product("Product B", 19.99f, 20, "Supplier 2", temp));

        // Initialize recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ProductAdapter(ProductActivity.this, mProductList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
