package com.midasit.challenge.ui.admin.cafemenu.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.midasit.challenge.R;


public class AdminMenuDetailActivity extends AppCompatActivity implements Button.OnClickListener{

    TextView name;
    TextView des;
    ImageView img;
    TextView price;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_detail);
        Toolbar toolbar = (Toolbar)findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("메뉴 상세화면");

        Button delete_button = (Button)findViewById(R.id.delete_btn);
        delete_button.setOnClickListener(this);
        Button modifi_button = (Button)findViewById(R.id.modification_btn);
        modifi_button.setOnClickListener(this);


        name = (TextView)findViewById(R.id.name_tv);
        img = (ImageView)findViewById(R.id.image_img);
        price = (TextView)findViewById(R.id.price_tv);

        Intent intent = getIntent();
        if(intent!=null){
            Bundle eta = intent.getExtras();
            itemId = eta.getInt("itemId");
            name.setText(eta.getString("name"));
            price.setText(eta.getInt("price")+"원");
        }

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.delete_btn:
                Toast.makeText(getApplicationContext(), "삭제", Toast.LENGTH_SHORT).show();
                break;
            case R.id.modification_btn:
                Intent intent = new Intent(getApplicationContext(), AdminMenuDetailModifiActivity.class);
                intent.putExtra(AdminMenuDetailModifiActivity.NAME, name.getText().toString());
//                intent.putExtra(AdminMenuDetailModifiActivity.DESCRIPTION, des.getText().toString());
               // BitmapDrawable bitmapDrawable = (BitmapDrawable)img.getDrawable();
               // Bitmap b = bitmapDrawable.getBitmap();
               // intent.putExtra(AdminMenuDetailModifiActivity.RESID, (Bitmap)b);
                intent.putExtra(AdminMenuDetailModifiActivity.PRICE, price.getText().toString());
                startActivity(intent);
                break;
        }
    }
}
