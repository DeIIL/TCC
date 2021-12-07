package com.example.tcc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ProductsGET> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<ProductsGET> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;

    }

    @Override
    public int getItemCount() {return mData.size(); }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element, null);
        return  new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int positon) {
        holder.bindData(mData.get(positon));
    }

    public void setItems(List<ProductsGET> items) {mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView seeMore, price, name;
        ImageButton seeMore_btn;

        ViewHolder(View itemView){
            super (itemView);
            iconImage = itemView.findViewById(R.id.img_product_1);
            name = itemView.findViewById(R.id.name_product_1);
            seeMore = itemView.findViewById(R.id.see_more_product_1);
            price = itemView.findViewById(R.id.price_product_1);
            seeMore_btn = itemView.findViewById(R.id.btn_seemore);
        }

        void bindData(final ProductsGET item) {
            name.setText(item.getProd_name());
            price.setText(item.getProd_price());
        }

    }
}
