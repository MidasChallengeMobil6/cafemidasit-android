package com.midasit.challenge.ui.admin.cafemenu.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.midasit.challenge.R;

public class AdminMenuDetailModifiActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String RESID = "resId";

    EditText name;
    ImageView img;
    EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_detail_modifi);

        Toolbar toolbar = (Toolbar)findViewById(R.id.menu_modifi_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("메뉴 수정화면");

        Intent intent = getIntent();

        name= (EditText)findViewById(R.id.name_et);
        price= (EditText)findViewById(R.id.price_et);
        img= (ImageView)findViewById(R.id.modifi_img);
        Button modifi =(Button)findViewById(R.id.modification_btn);
        modifi.setOnClickListener(this);

        name.setText(intent.getStringExtra(NAME));
        price.setText(intent.getStringExtra(PRICE));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modification_btn:
                Toast.makeText(getApplicationContext(), "수정완료", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
