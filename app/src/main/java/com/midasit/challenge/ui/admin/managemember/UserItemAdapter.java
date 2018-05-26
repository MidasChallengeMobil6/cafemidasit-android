package com.midasit.challenge.ui.admin.managemember;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midasit.challenge.R;
import com.midasit.challenge.model.Item;
import com.midasit.challenge.model.User;
import com.midasit.challenge.ui.admin.cafemenu.tabs.ItemViewHolder;

import java.util.ArrayList;

/**
 * Created by ichaeeun on 2018. 5. 26..
 */

public class UserItemAdapter extends RecyclerView.Adapter<UserItemViewHolder>{

    private ArrayList<User> mDataset;

    public UserItemAdapter(ArrayList<User> mDataSet) {
        this.mDataset = mDataSet;
    }

    @Override
    public UserItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        UserItemViewHolder viewHolder = new UserItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserItemViewHolder holder, int position) {

        User user = mDataset.get(position);
        holder.birhhdayTv.setText(user.birthday);
        holder.nameTv.setText(user.name + "(" + user.username + ")");
        holder.phoneTv.setText(user.phone);
        holder.joinTv.setText(user.joinDate);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
