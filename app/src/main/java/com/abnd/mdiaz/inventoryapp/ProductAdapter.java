package com.abnd.mdiaz.inventoryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neboo on 17-Aug-16.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mContext;
    private List<Product> mProductList = new ArrayList<>();

    public ProductAdapter(Context context, List<Product> productList) {
        mContext = context;
        mProductList = productList;
    }

    //Not sure if this one will work on a Recycler View
    public void setProductList(List<Product> productList) {
        mProductList.clear();
        mProductList.addAll(productList);
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list, parent, false);

        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        Product currentProduct = mProductList.get(position);

        holder.proImage.setImageBitmap(ImageTools.imageProcess(currentProduct.getImageUri()));
        holder.proName.setText(currentProduct.getName());
        holder.proSupplier.setText(mContext.getString(R.string.supplied_by) + currentProduct.getSupplier());
        holder.proQuantity.setText(mContext.getString(R.string.quantity) + String.valueOf(currentProduct.getQuantity()));
        holder.proPrice.setText(mContext.getString(R.string.unit_price) + String.valueOf(currentProduct.getPrice()));

    }

    @Override
    public int getItemCount() {
        return (null != mProductList ? mProductList.size() : 0);
    }

    private void productSale(int position) {
        Product product = mProductList.get(position);
        int quantity = product.getQuantity();

        if (quantity > 0) {
            DatabaseHelper dbHelp = new DatabaseHelper(mContext);
            dbHelp.updateQuantity(product.getId(), quantity - 1);

            mProductList.clear();
            mProductList.addAll(dbHelp.getAllProducts());
            notifyDataSetChanged();

        } else {
            Toast.makeText(mContext, "There are no items left to sell.", Toast.LENGTH_SHORT).show();
        }
    }

    private void openDetailView(int position) {
        Intent intent = new Intent(mContext, ProductDetail.class);
        Product product = mProductList.get(position);
        intent.putExtra("product", product);
        ((Activity) mContext).startActivityForResult(intent, 2);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView proImage;
        protected TextView proName;
        protected TextView proSupplier;
        protected TextView proQuantity;
        protected TextView proPrice;
        protected Button sellButton;

        public ProductViewHolder(View view) {
            super(view);
            sellButton = (Button) view.findViewById(R.id.sell_button);
            proImage = (ImageView) view.findViewById(R.id.pro_image);
            proName = (TextView) view.findViewById(R.id.pro_name);
            proSupplier = (TextView) view.findViewById(R.id.pro_supplier);
            proQuantity = (TextView) view.findViewById(R.id.pro_quantity);
            proPrice = (TextView) view.findViewById(R.id.pro_price);
            sellButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    productSale(getLayoutPosition());
                }
            });
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            openDetailView(getLayoutPosition());
        }
    }

}
