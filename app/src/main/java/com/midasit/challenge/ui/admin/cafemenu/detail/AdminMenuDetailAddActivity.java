package com.midasit.challenge.ui.admin.cafemenu.detail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.midasit.challenge.R;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AdminMenuDetailAddActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String RESID = "resId";

    EditText name;
    ImageView img;
    EditText price;
    String food_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_detail_add);

        Toolbar toolbar = (Toolbar)findViewById(R.id.menu_modifi_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("메뉴 추가화면");

        Intent intent = getIntent();

        name= (EditText)findViewById(R.id.name_et);
        price= (EditText)findViewById(R.id.price_et);
        img= (ImageView)findViewById(R.id.modifi_img);
        Button modifi =(Button)findViewById(R.id.modification_btn);
        modifi.setOnClickListener(this);

        name.setText(intent.getStringExtra(NAME));
        price.setText(intent.getStringExtra(PRICE));
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.CONTENT_TYPE);
                Intent chooserIntent = Intent.createChooser(intent, "선택해주세요");
                startActivityForResult(chooserIntent, 0);
            }
        });


        Spinner spinner = (Spinner)findViewById(R.id.food_type_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.food_type));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        food_type= "커피";
                        break;
                    case 1:
                        food_type = "푸드";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modification_btn:
                Toast.makeText(getApplicationContext(), "추가완료 종류: " +food_type, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==0){
            if(resultCode== Activity.RESULT_OK){
                try{
                    //이미지 데이터를 비트맵으로 받아오기.
                    Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                    img.setImageBitmap(image_bitmap);
                }catch(FileNotFoundException e) {e.printStackTrace(); }
                catch (IOException e) {e.printStackTrace();}
                catch(Exception e) {e.printStackTrace();}

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

