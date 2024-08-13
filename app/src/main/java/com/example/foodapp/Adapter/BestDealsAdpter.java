package com.example.foodapp.Adapter;

import android.content.ClipData;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;

import java.util.ArrayList;

public class BestDealsAdpter extends RecyclerView.Adapter<BestDealsAdpter.viewholder> {
    ArrayList<ClipData.Item>
    @NonNull
    @Override
    public BestDealsAdpter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BestDealsAdpter.viewholder holder, int position) {

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
