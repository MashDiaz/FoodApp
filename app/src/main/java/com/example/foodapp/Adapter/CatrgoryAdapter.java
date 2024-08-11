package com.example.foodapp.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import com.example.foodapp.Domain.CategoryDomain;
import com.example.foodapp.R;

import java.time.Instant;
import java.util.ArrayList;

public class CatrgoryAdapter extends RecyclerView.Adapter<CatrgoryAdapter.viewholder> {

    ArrayList<CategoryDomain> items;
    Context context;


    public CatrgoryAdapter(ArrayList<CategoryDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CatrgoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cayegory,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CatrgoryAdapter.viewholder holder, int position) {

        holder.titleTxt.setText(items.get(position).getTitle());
        @SuppressLint("DiscouragedApi") int drawableResourceId=holder.itemView.getResources()
                .getIdentifier(items.get(position).getImgPath(), "drawable",holder.itemView.getContext().getPackageName());

    Glide.with(context)
            .load(drawableResourceId)
            .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView titleTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            pic=itemView.findViewById(R.id.img);

        }
    }
}
