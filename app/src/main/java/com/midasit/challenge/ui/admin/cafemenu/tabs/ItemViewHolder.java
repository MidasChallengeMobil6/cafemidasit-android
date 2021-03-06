package com.midasit.challenge.ui.admin.cafemenu.tabs;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midasit.challenge.R;

/**
 * Created by ichaeeun on 2018. 5. 26..
 */

public class ItemViewHolder extends RecyclerView.ViewHolder{

    ImageView item_img;

    LinearLayout containerLl;
    TextView nameTv;
    TextView priceTv;

    public ItemViewHolder(View itemView) {
        super(itemView);
        nameTv = itemView.findViewById(R.id.title_tv);
        priceTv = itemView.findViewById(R.id.price_tv);
        containerLl = itemView.findViewById(R.id.container_ll);
        item_img = itemView.findViewById(R.id.item_img);

    }


}
