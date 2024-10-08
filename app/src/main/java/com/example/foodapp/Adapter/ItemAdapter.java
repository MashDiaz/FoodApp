package com.example.foodapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Activity.DBHelper;
import com.example.foodapp.Activity.Item;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private Context context;
    private List<Item> itemList;
    private ArrayList<String> itemNames = new ArrayList<>();
    private ArrayList<String> itemPrices = new ArrayList<>();

    public ItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemName.setText(item.getFoodName());
        holder.itemPrice.setText("Rs." + item.getPrice());

        // Set the image for the ImageView
        Bitmap bitmap = BitmapFactory.decodeByteArray(item.getImage(), 0, item.getImage().length);
        holder.foodImage.setImageBitmap(bitmap);

        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add item name and price to the arrays
                itemNames.add(item.getFoodName());
                itemPrices.add(String.valueOf(item.getPrice()));

                // Optionally, you can log or show a message
                Toast.makeText(context, "Added: " + item.getFoodName() + " - Rs." + item.getPrice(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(context);
                boolean isAdded = dbHelper.addFavourite("currentUsername", item.getFoodId()); // Replace "currentUsername" with the actual logged-in username
                if (isAdded) {
                    Toast.makeText(context, item.getFoodName() + " added to favourites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to add to favourites", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public ArrayList<String> getItemNames() {
        return itemNames;
    }

    public ArrayList<String> getItemPrices() {
        return itemPrices;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, addItem;
        ImageView foodImage,favoriteIcon ;// Added ImageView for the food image

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            addItem = itemView.findViewById(R.id.addItem);
            foodImage = itemView.findViewById(R.id.itemImage); // Initialize the ImageView
            favoriteIcon = itemView.findViewById(R.id.favoriteIcon); // Initialize the favoriteIcon
        }
    }
}
