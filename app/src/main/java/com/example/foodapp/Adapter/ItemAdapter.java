package com.example.foodapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Activity.Item;
import com.example.foodapp.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private List<Item> itemList;

    public ItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.foodName.setText(item.getFoodName());
        holder.price.setText("Rs." + item.getPrice());

        Bitmap bitmap = BitmapFactory.decodeByteArray(item.getImage(), 0, item.getImage().length);
        holder.foodImage.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, price;
        ImageView foodImage;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.itemName); // Title
            price = itemView.findViewById(R.id.itemPrice); // Price
            foodImage = itemView.findViewById(R.id.itemImage); // Image
        }
    }
}
