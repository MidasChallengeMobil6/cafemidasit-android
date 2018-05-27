package com.midasit.challenge.ui.admin.reserve;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.midasit.challenge.R;
import com.midasit.challenge.application.ApplicationController;
import com.midasit.challenge.model.Reserve;

import java.util.ArrayList;

public class RecyclerReserveAdapter extends RecyclerView.Adapter<RecyclerReserveAdapter.ViewHolder> {

    private ArrayList<Reserve> mDataset;

    int order_id;
    private String[] menus;
    private String[] nums;
    private String[] prices;
    private String[] names;
    private String[] days;

    RecyclerReserveAdapter(String[] menus, String[] nums, String[] prices , String[] names, String[] days){
        this.menus = menus;
        this.nums = nums;
        this.prices = prices;
        this.names = names;
        this.days = days;
    }

    RecyclerReserveAdapter(ArrayList<Reserve> dataset) {
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.reserve_cardview, parent, false);
        ViewHolder holder = new ViewHolder(cardView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView menuView = (TextView)cardView.findViewById(R.id.reserve_list_menu);
        TextView numView = (TextView)cardView.findViewById(R.id.reserve_list_num);
        TextView nameView = (TextView)cardView.findViewById(R.id.reserve_list_name);
        TextView priceView = (TextView)cardView.findViewById(R.id.reserve_list_price);
        Button checkoutBtn = (Button)cardView.findViewById(R.id.checkout_done_btn);
        TextView dayView = (TextView)cardView.findViewById(R.id.reserve_list_days);

        Reserve reserve = mDataset.get(position);


        order_id = reserve.orderId;
        menuView.setText(reserve.itemname);
        numView.setText(String.valueOf(reserve.quantity));
        nameView.setText(reserve.username);
        priceView.setText(String.valueOf(reserve.price));
        dayView.setText(reserve.paymentDate);

        // 예약 확인 완료 버튼 리스너 설정
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView)v;
                if(reserve.status.equals("new")){
                    //v1/orders/order_id/ready
                    textView.setText("준비");
                    ApplicationController.getInstance().getNetworkService().updateStatus(String.valueOf(order_id), "ready");
                    textView.setBackgroundColor(v.getResources().getColor(R.color.yellow));
                }else if(reserve.status.equals("ready")){
                    textView.setText("완료");
                    ApplicationController.getInstance().getNetworkService().updateStatus(String.valueOf(order_id), "completed");
                    textView.setBackgroundColor(v.getResources().getColor(R.color.dodger_blue));
                }else if(reserve.status.equals("completed")){
                    textView.setText("예약");
                    ApplicationController.getInstance().getNetworkService().updateStatus(String.valueOf(order_id), "new");
                    textView.setBackgroundColor(v.getResources().getColor(R.color.greenish_teal));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size() ;
    }
}
