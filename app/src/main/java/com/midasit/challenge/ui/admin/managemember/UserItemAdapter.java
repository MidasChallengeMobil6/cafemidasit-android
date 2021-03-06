package com.midasit.challenge.ui.admin.managemember;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.midasit.challenge.R;
import com.midasit.challenge.application.ApplicationController;
import com.midasit.challenge.model.DeleteUserResponseObject;
import com.midasit.challenge.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ichaeeun on 2018. 5. 26..
 */

public class UserItemAdapter extends RecyclerView.Adapter<UserItemViewHolder>{

    private ArrayList<User> mDataset;
    Context mContext;
    public LinearLayout linearLayout;

    public UserItemAdapter(ArrayList<User> mDataSet, Context context) {
        mDataset = mDataSet;
        mContext = context;
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
        holder.birhhdayTv.setText("생일 : " + user.birthday);
        holder.nameTv.setText(user.name + " (" + user.username + ")");
        holder.phoneTv.setText("핸드폰 번호 : " + user.phone);
        holder.joinTv.setText("가입일 : " + user.joinDate);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=  new Intent(mContext, RegisterModificationActivity.class);
                intent.putExtra(RegisterModificationActivity.PHONE, user.phone);
                mContext.startActivity(intent);
            }
        });


        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Call<DeleteUserResponseObject> deleteUser = ApplicationController.getInstance().getNetworkService().deleteUser(String.valueOf(user.id));
                        deleteUser.enqueue(new Callback<DeleteUserResponseObject>() {
                            @Override
                            public void onResponse(Call<DeleteUserResponseObject> call, Response<DeleteUserResponseObject> response) {
                                if(response.body().err == 0){
                                    Toast.makeText(mContext, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                    mDataset.remove(user);
                                    notifyItemRemoved(position);
                                    return;
                                }
                            }

                            @Override
                            public void onFailure(Call<DeleteUserResponseObject> call, Throwable t) {
                                Toast.makeText(mContext, "삭제못했습니다", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        });


                    }
                });
                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(mContext, "취소되었습니다.", Toast.LENGTH_SHORT).show();
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
