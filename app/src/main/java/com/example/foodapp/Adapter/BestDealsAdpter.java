package com.example.foodapp.Adapter;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Domain.ItemDomain;
import com.example.foodapp.R;

import java.util.ArrayList;

public class BestDealsAdpter extends RecyclerView.Adapter<BestDealsAdpter.viewholder> {
    ArrayList<ItemDomain> item;
    Context context;

    public BestDealsAdpter(ArrayList<ItemDomain> item) {
        this.item = item;
    }

    @NonNull
    @Override
    public BestDealsAdpter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bestdeals,parent,false);

        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getTitle());
        holder.priceTxt.setText(items.get(position).getPrice());

        int drawableResourceId=holder.itemView.getResources()
                .getIdentifier(items.get(position).getImgPath(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewholder extends  RecyclerView.ViewHolder {
        TextView titleTxt,priceTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            priceTxt= ItemView.findViewById(R.id.priceTxt);
            pic=itemView.findViewById(R.id.img);
        }
    }
}
