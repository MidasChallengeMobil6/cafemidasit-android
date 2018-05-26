package com.midasit.challenge.ui.admin.cafemenu.detail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.midasit.challenge.R;
import com.midasit.challenge.application.ApplicationController;
import com.midasit.challenge.model.ResultObject;
import com.midasit.challenge.network.NetworkService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminMenuDetailModifiActivity extends AppCompatActivity implements View.OnClickListener{

    EditText nameEt;
    ImageView imageView;
    EditText priceEt;
    String imgurl = "";
    Uri data;
    NetworkService service;
    String name;
    int cateogry;
    int price = 0;
    int item_id;

    String newImgUrl = "";
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_detail_modifi);

        Toolbar toolbar = (Toolbar)findViewById(R.id.menu_modifi_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("메뉴 수정화면");

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        cateogry = intent.getIntExtra("category",1);
        price = intent.getIntExtra("price",0);
        item_id = intent.getIntExtra("id",0);
        imgurl = intent.getStringExtra("imgurl");


        mProgressDialog = new ProgressDialog(AdminMenuDetailModifiActivity.this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("등록 중...");
        mProgressDialog.setIndeterminate(true);

        nameEt = (EditText)findViewById(R.id.name_et);
        priceEt = (EditText)findViewById(R.id.price_et);
        imageView = (ImageView)findViewById(R.id.modifi_img);
        Button modifi =(Button)findViewById(R.id.modification_btn);
        modifi.setOnClickListener(this);

        nameEt.setText(name);
        priceEt.setText(String.valueOf(price));
        Glide
                .with(this)
                .load(imgurl)
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.CONTENT_TYPE);
                Intent chooserIntent = Intent.createChooser(intent, "선택해주세요");
                startActivityForResult(chooserIntent, 0);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modification_btn:


                mProgressDialog.show();

                RequestBody priceRueqest = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(price));
                RequestBody nameRequest = RequestBody.create(MediaType.parse("multipart/form-data"), name);
                RequestBody categoryRequest = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(cateogry));
                RequestBody item_idRequest = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(item_id));





                    // 이미지를 리사이징
                    BitmapFactory.Options options = new BitmapFactory.Options();

                    InputStream in = null; // here, you need to get your context.
                    try {
                        in = getContentResolver().openInputStream(data);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    Bitmap bitmap = BitmapFactory.decodeStream(in, null, options);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 20, baos);


//                    RequestBody photoBody = RequestBody.create(MediaType.parse("image/png"), baos.toByteArray());

                    File file = new File(getRealPathFromURI(data));
                    Log.d("myTag", getRealPathFromURI(data));

//                    body = MultipartBody.Part.createFormData("image", photo.getName(), photoBody);

                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                    MultipartBody.Part body =
                            MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);


                    Call<ResultObject> requestImgUpdate = ApplicationController.getInstance().getNetworkService().updateItem(String.valueOf(item_id), body, priceRueqest, nameRequest,categoryRequest);

                    requestImgUpdate.enqueue(new Callback<ResultObject>() {
                        @Override
                        public void onResponse(Call<ResultObject> call, Response<ResultObject> response) {
                            if (response.isSuccessful()){
                                mProgressDialog.dismiss();
                                if(response.body().err == 0){
                                    Toast.makeText(getApplicationContext(), "수정완료", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "잘못된요청", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<ResultObject> call, Throwable t) {
                            mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                            Log.i("myTag",t.toString());
                        }
                    });

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                try {
//                    이미지 데이터를 비트맵으로 받아오기.
                    Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    imageView.setImageBitmap(image_bitmap);
                    this.data = data.getData();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        }
    }



    public String getRealPathFromURI(Uri uri) {

        String result;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            result = uri.getPath();
            cursor.close();
            return result;
        }
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        result = cursor.getString(idx);
        cursor.close();
        return result;
    }

}
