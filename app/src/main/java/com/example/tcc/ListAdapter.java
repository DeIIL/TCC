package com.example.tcc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcc.models.Products;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Products> mData;
    private LayoutInflater mInflater;
    private Context context;
    private ClickedItem clickedItem;


    public ListAdapter(List<Products> itemList, Context context, ClickedItem clickedItem) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.clickedItem = clickedItem;

    }


    @Override
    public int getItemCount() {return mData.size(); }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element, null);
        return  new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int positon) {

        Products products = mData.get(positon);

        holder.bindData(mData.get(positon));
        holder.seeMore_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedProduct(products);
            }
        });

    }

    public void setItems(List<Products> items) {mData = items;}

    public interface ClickedItem{
        public void ClickedProduct(Products products);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView seeMore, price, name;
        ImageButton seeMore_btn;

        public ViewHolder(View itemView){
            super (itemView);
            iconImage = itemView.findViewById(R.id.img_product_1);
            name = itemView.findViewById(R.id.name_product_1);
            seeMore = itemView.findViewById(R.id.see_more_product_1);
            price = itemView.findViewById(R.id.price_product_1);
            seeMore_btn = itemView.findViewById(R.id.btn_seemore);
        }

        void bindData(final Products item) {
            name.setText(item.getProd_name());
            price.setText(item.getProd_price());
        }
    }
}
