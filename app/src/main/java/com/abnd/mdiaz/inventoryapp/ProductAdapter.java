package com.abnd.mdiaz.inventoryapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        holder.proImage.setImageBitmap(currentProduct.getImage());
        holder.proName.setText(currentProduct.getName());
        holder.proSupplier.setText(currentProduct.getSupplier());
        holder.proQuantity.setText(String.valueOf(currentProduct.getQuantity()));
        holder.proPrice.setText(String.valueOf(currentProduct.getPrice()));

    }

    @Override
    public int getItemCount() {
        return (null != mProductList ? mProductList.size() : 0);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        protected ImageView proImage;
        protected TextView proName;
        protected TextView proSupplier;
        protected TextView proQuantity;
        protected TextView proPrice;

        public ProductViewHolder(View view) {
            super(view);
            proImage = (ImageView) view.findViewById(R.id.pro_image);
            proName = (TextView) view.findViewById(R.id.pro_name);
            proSupplier = (TextView) view.findViewById(R.id.pro_supplier);
            proQuantity = (TextView) view.findViewById(R.id.pro_quantity);
            proPrice = (TextView) view.findViewById(R.id.pro_price);
        }
    }
}
