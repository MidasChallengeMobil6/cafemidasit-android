package com.midasit.challenge.ui.member.cafemenu.tabs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.midasit.challenge.R;
import com.midasit.challenge.application.ApplicationController;
import com.midasit.challenge.model.Item;
import com.midasit.challenge.ui.member.cafemenu.reserve.MemberMenuDetailActivity;

import java.util.ArrayList;

/**
 * Created by ichaeeun on 2018. 5. 27..
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder>{

    private ArrayList<Item> mDataset;
    private Context mContext;

    public ItemAdapter(ArrayList<Item> mDataSet, Context context) {
        this.mDataset = mDataSet;
        this.mContext = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        Item item = mDataset.get(position);
        holder.nameTv.setText(item.name);
        holder.priceTv.setText(item.price + "원");

        holder.containerLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MemberMenuDetailActivity.class);
                intent.putExtra("id", item.id);
                intent.putExtra("name", item.name);
                intent.putExtra("price", item.price);
                intent.putExtra("id", item.price);
                String imgurl = ApplicationController.baseUrl + "/images/" + item.id + ".png";
                intent.putExtra("imgurl", imgurl);
                intent.putExtra("category", item.category);
                mContext.startActivity(intent);
            }
        });

        int id = item.id;


        String url = ApplicationController.baseUrl + "/images/" + id + ".png";

        Log.d("ItemAdapter", url);
        Glide
                .with(mContext)
                .load(url)
                .into(holder.item_img);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
