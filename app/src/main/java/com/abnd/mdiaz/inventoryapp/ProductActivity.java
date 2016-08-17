package com.abnd.mdiaz.inventoryapp;

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

        mProductList.add(new Product("Product A", 9.99f, 10, "Supplier 1", R.drawable.bulbasaur));
        mProductList.add(new Product("Product B", 19.99f, 20, "Supplier 2", R.drawable.charmander));

        // Initialize recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ProductAdapter(ProductActivity.this, mProductList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
