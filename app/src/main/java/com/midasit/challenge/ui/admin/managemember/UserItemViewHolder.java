package com.midasit.challenge.ui.admin.managemember;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.midasit.challenge.R;

/**
 * Created by ichaeeun on 2018. 5. 26..
 */

public class UserItemViewHolder extends RecyclerView.ViewHolder{

    TextView nameTv;
    TextView joinTv;
    TextView birhhdayTv;
    TextView phoneTv;

    public UserItemViewHolder(View itemView) {
        super(itemView);
        nameTv = itemView.findViewById(R.id.name_tv);
        joinTv = itemView.findViewById(R.id.joindate_tv);
        birhhdayTv = itemView.findViewById(R.id.birthday_tv);
        phoneTv = itemView.findViewById(R.id.phone_tv);

    }


}
