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

        DatabaseHelper dbHelp = new DatabaseHelper(this);

        //Funny how the database was not regenerated everytime...
        dbHelp.deleteAllData();

        dbHelp.addProduct(1, "Bulbasaur", 9.99f, 10, "Supplier 1", R.drawable.bulbasaur);
        dbHelp.addProduct(2, "Charmander", 19.99f, 20, "Supplier 2", R.drawable.charmander);
        dbHelp.addProduct(3, "Jigglypuff", 29.99f, 20, "Supplier 3", R.drawable.jigglypuff);
        dbHelp.addProduct(4, "Pikachu", 69.99f, 20, "Supplier 4", R.drawable.pikachu);

        mProductList = dbHelp.getAllProducts();

        // Initialize recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ProductAdapter(ProductActivity.this, mProductList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
