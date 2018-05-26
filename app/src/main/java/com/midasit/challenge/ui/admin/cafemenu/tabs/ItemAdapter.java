package com.midasit.challenge.ui.admin.cafemenu.tabs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midasit.challenge.R;
import com.midasit.challenge.model.Item;

import java.util.ArrayList;

/**
 * Created by ichaeeun on 2018. 5. 26..
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder>{

    private ArrayList<Item> mDataset;

    public ItemAdapter(ArrayList<Item> mDataSet) {
        this.mDataset = mDataSet;
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
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
