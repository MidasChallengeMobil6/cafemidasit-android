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

import com.bumptech.glide.Glide;
import com.midasit.challenge.R;


public class AdminMenuDetailActivity extends AppCompatActivity implements Button.OnClickListener{

    TextView nameTv;
    TextView des;
    ImageView imageView;
    TextView priceTv;
    int itemId;
    String name;
    int cateogry;
    int price;
    String imgurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_detail);
        Toolbar toolbar = findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("메뉴 상세화면");

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        cateogry = intent.getIntExtra("category",1);
        price = intent.getIntExtra("price", 0);
        itemId = intent.getIntExtra("id", 0);
        imgurl = intent.getStringExtra("imgurl");

        Button delete_button = (Button)findViewById(R.id.delete_btn);
        delete_button.setOnClickListener(this);
        Button modifi_button = (Button)findViewById(R.id.modification_btn);
        modifi_button.setOnClickListener(this);


        nameTv = (TextView)findViewById(R.id.name_tv);
        imageView = (ImageView)findViewById(R.id.image_img);
        priceTv = (TextView)findViewById(R.id.price_tv);


        if(intent!=null){
            nameTv.setText(name);
            priceTv.setText(price +"원");
            Glide
                    .with(this)
                    .load(imgurl)
                    .into(imageView);
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
                intent.putExtra("name", name);
//                intent.putExtra(AdminMenuDetailModifiActivity.DESCRIPTION, des.getText().toString());
               // BitmapDrawable bitmapDrawable = (BitmapDrawable)imageView.getDrawable();
               // Bitmap b = bitmapDrawable.getBitmap();
               // intent.putExtra(AdminMenuDetailModifiActivity.RESID, (Bitmap)b);
                intent.putExtra("price", price);
                intent.putExtra("category", cateogry);
                intent.putExtra("imgurl", imgurl);
                intent.putExtra("itemId", itemId);
                startActivity(intent);
                break;
        }
    }
}
