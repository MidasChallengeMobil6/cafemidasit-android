package com.midasit.challenge.ui.admin.managemember;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.midasit.challenge.R;
import com.midasit.challenge.model.User;

import java.util.ArrayList;

/**
 * Created by ichaeeun on 2018. 5. 26..
 */

public class UserItemAdapter extends RecyclerView.Adapter<UserItemViewHolder>{

    private ArrayList<User> mDataset;
    public LinearLayout linearLayout;

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
        linearLayout = holder.linearLayout;

        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(linearLayout.getContext());
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(linearLayout.getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(linearLayout.getContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setTitle("삭제");
                builder.setMessage("삭제하시겠습니까?");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
