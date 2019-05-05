package com.raju.onlineclothing;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.MyViewHolder> {
    private List<Item> items;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout layout;
        private TextView itemName;
        private ImageView itemImage;

        public MyViewHolder(@NonNull LinearLayout itemView){
            super(itemView);
            layout = itemView;
            itemImage = layout.findViewById(R.id.item_imageName);
            itemName = layout.findViewById(R.id.item_itemName);
        }
    }

    public MyViewAdapter(List<Item> items1){
        items = items1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(layout);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int i) {
        final Item item = items.get(i);
        viewHolder.itemName.setText(item.getItemName());
        viewHolder.itemImage.setImageResource(R.drawable.prana_1);

        viewHolder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ItemDetails.class);
                intent.putExtra("itemName", item.getItemName());
                intent.putExtra("itemPrice", item.getItemPrice());
                intent.putExtra("itemImageName", item.getItemImageName());
                intent.putExtra("itemDescription", item.getItemDescription());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
