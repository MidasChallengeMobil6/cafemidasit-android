package com.midasit.challenge.ui.member.cafemenu.tabs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midasit.challenge.R;
import com.midasit.challenge.model.Item;
import com.midasit.challenge.ui.member.cafemenu.reserve.MemberMenuDetailActivity;

import java.util.ArrayList;

/**
 * Created by ichaeeun on 2018. 5. 26..
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
                intent.putExtra("imgurl", item.imgurl);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
