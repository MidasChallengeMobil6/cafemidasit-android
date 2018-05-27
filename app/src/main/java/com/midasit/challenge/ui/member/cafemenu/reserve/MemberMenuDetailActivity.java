package com.midasit.challenge.ui.member.cafemenu.reserve;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.midasit.challenge.R;
import com.midasit.challenge.application.ApplicationController;
import com.midasit.challenge.model.OrderRequestObject;
import com.midasit.challenge.model.ResultObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberMenuDetailActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView addButton;
    ImageView minusButton;
    TextView countNumView;
    private int countNum = 1;
    private int price;
    private int total;
    TextView totalView;
    TextView priceView;
    String size;
    Button orderButton;

    int itemId;
    /*
    *
    int userId;
    int quantity;
    String size;
    int itemId;
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_menu_detail);

        addButton = (ImageView)findViewById(R.id.add_img);
        minusButton = (ImageView)findViewById(R.id.minus_img);
        countNumView =(TextView)findViewById(R.id.countnum);
        totalView = (TextView)findViewById(R.id.total);
        priceView = (TextView)findViewById(R.id.price_tv);
        orderButton = findViewById(R.id.modification_btn);
        price =Integer.valueOf(priceView.getText().toString());

        Intent intent = getIntent();

        itemId = intent.getIntExtra("id",0);
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
                        size = "M";
                        totalCount();
                        break;
                    case 1:
                        priceView.setText(String.valueOf(price + 500));
                        totalCount();
                        size = "L";
                        break;
                    case 2:
                        priceView.setText(String.valueOf(price + 1000));
                        totalCount();
                        size="XL";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        orderButton.setOnClickListener(this);

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

            case R.id.modification_btn:
                Toast.makeText(getApplicationContext(), "Toask", Toast.LENGTH_SHORT).show();
                SharedPreferences test = getSharedPreferences("token", MODE_PRIVATE);

                int userId = test.getInt("userId", 0);



                OrderRequestObject item = new OrderRequestObject(userId, countNum, size, itemId);
                Log.d("AAA", userId + ":" + countNum + ":" + size + ":" + itemId);
                Call<ResultObject> orderItem = ApplicationController.getInstance().getNetworkService().orderItem(item);
                orderItem.enqueue(new Callback<ResultObject>() {
                    @Override
                    public void onResponse(Call<ResultObject> call, Response<ResultObject> response) {
                        if (response.isSuccessful()) {

                            if (response.body().err == 0) {
                                Toast.makeText(getApplicationContext(), "예약했습니다", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "서버연결만 했습니다", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ResultObject> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "서버연결 실패 했습니다", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    private void totalCount(){
        total = Integer.valueOf(priceView.getText().toString()) * countNum;
        totalView.setText(String.valueOf(total));
    }
}
