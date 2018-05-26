package com.midasit.challenge.ui.admin.reserve;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.midasit.challenge.R;

public class RecyclerReserveAdapter extends RecyclerView.Adapter<RecyclerReserveAdapter.ViewHolder> {

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

        menuView.setText(menus[position]);
        numView.setText(nums[position]);
        nameView.setText(names[position]);
        priceView.setText(prices[position]);
        dayView.setText(days[position]);

        // 예약 확인 완료 버튼 리스너 설정
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView)v;
                if(textView.getText().toString().equals("예약")){
                    textView.setText("준비");
                    textView.setBackgroundColor(v.getResources().getColor(R.color.yellow));
                }else if(textView.getText().toString().equals("준비")){
                    textView.setText("완료");
                    textView.setBackgroundColor(v.getResources().getColor(R.color.dodger_blue));
                }else if(textView.getText().toString().equals("완료")){
                    textView.setText("예약");
                    textView.setBackgroundColor(v.getResources().getColor(R.color.greenish_teal));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return menus.length;
    }
}
