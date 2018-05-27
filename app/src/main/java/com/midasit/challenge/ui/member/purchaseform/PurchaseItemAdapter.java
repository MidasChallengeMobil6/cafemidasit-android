package com.midasit.challenge.ui.member.purchaseform;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;


import com.midasit.challenge.R;
import com.midasit.challenge.model.Purchase;

import java.util.ArrayList;

/**
 * Created by ichaeeun on 2018. 5. 26..
 */

public class PurchaseItemAdapter extends RecyclerView.Adapter<PurchaseItemAdapter.ViewHolder>{

    private String[] menus;
    private String[] nums;
    private String[] prices;
    private String[] names;
    private String[] days;

    private ArrayList<Purchase> mDataset;
    private Context mContext;

    PurchaseItemAdapter(String[] menus, String[] nums, String[] prices , String[] names, String[] days){
        this.menus = menus;
        this.nums = nums;
        this.prices = prices;
        this.names = names;
        this.days = days;
    }

    PurchaseItemAdapter(ArrayList<Purchase> dataset) {
        mDataset = dataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ViewHolder(CardView cv){
            super(cv);
            cardView = cv;
        }
    }

    @Override
    public PurchaseItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.monthly_item_cardview, parent, false);
        PurchaseItemAdapter.ViewHolder holder = new PurchaseItemAdapter.ViewHolder(cardView);
        return holder;
    }

    @Override
    public void onBindViewHolder(PurchaseItemAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView menuView = (TextView)cardView.findViewById(R.id.monthly_list_menu);
        TextView numView = (TextView)cardView.findViewById(R.id.monthly_list_num);
        TextView priceView = (TextView)cardView.findViewById(R.id.monthly_list_price);
        TextView dayView = (TextView)cardView.findViewById(R.id.monthly_list_days);

        Purchase purchase = mDataset.get(position);
        menuView.setText(purchase.itemname);
        numView.setText(String.valueOf(purchase.quantity));
        priceView.setText(purchase.price + "원");
        dayView.setText(purchase.paymentDate);


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
