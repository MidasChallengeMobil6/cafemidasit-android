package com.midasit.challenge.ui.admin.cafemenu.tabs;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.midasit.challenge.R;

/**
 * Created by ichaeeun on 2018. 5. 26..
 */

public class ItemViewHolder extends RecyclerView.ViewHolder{


    TextView nameTv;
    TextView priceTv;

    public ItemViewHolder(View itemView) {
        super(itemView);
        nameTv = itemView.findViewById(R.id.title_tv);
        priceTv = itemView.findViewById(R.id.price_tv);

    }


}
