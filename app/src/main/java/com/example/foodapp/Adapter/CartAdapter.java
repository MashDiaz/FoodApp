package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.R;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<String> cartNames;
    private List<String> cartPrices;

    public CartAdapter(Context context, List<String> cartNames, List<String> cartPrices) {
        this.context = context;
        this.cartNames = cartNames;
        this.cartPrices = cartPrices;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.itemNameTextView.setText(cartNames.get(position));
        holder.itemPriceTextView.setText(cartPrices.get(position));
    }

    @Override
    public int getItemCount() {
        return cartNames.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView, itemPriceTextView;

        public CartViewHolder(View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemName);
            itemPriceTextView = itemView.findViewById(R.id.itemPrice);
        }
    }
}
