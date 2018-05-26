package com.midasit.challenge.ui.member.cafemenu.reserve;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.midasit.challenge.R;

public class MemberMenuDetailActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView addButton;
    ImageView minusButton;
    TextView countNumView;
    private int countNum = 1;
    private int price;
    private int total;
    TextView totalView;
    TextView priceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_menu_detail);

        addButton = (ImageView)findViewById(R.id.add_img);
        minusButton = (ImageView)findViewById(R.id.minus_img);
        countNumView =(TextView)findViewById(R.id.countnum);
        totalView = (TextView)findViewById(R.id.total);
        priceView = (TextView)findViewById(R.id.price_tv);
        price =Integer.valueOf(priceView.getText().toString());

        Toolbar toolbar = (Toolbar)findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("주문하기");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner)findViewById(R.id.cupsize_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.cup_size));
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        priceView.setText(String.valueOf(price));
                        totalCount();
                        break;
                    case 1:
                        priceView.setText(String.valueOf(price + 500));
                        totalCount();
                        break;
                    case 2:
                        priceView.setText(String.valueOf(price + 1000));
                        totalCount();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_img:
                if(countNum < 100){
                    countNum++;
                }
                totalCount();
                countNumView.setText(String.valueOf(countNum));
                break;
            case R.id.minus_img:
                if(countNum > 1){
                    countNum--;
                }
                totalCount();
                countNumView.setText(String.valueOf(countNum));
                break;
        }
    }

    private void totalCount(){
        total = Integer.valueOf(priceView.getText().toString()) * countNum;
        totalView.setText(String.valueOf(total));
    }
}
