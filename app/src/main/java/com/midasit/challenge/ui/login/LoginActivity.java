package com.midasit.challenge.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.midasit.challenge.R;
import com.midasit.challenge.application.ApplicationController;
import com.midasit.challenge.model.LoginRequestObject;
import com.midasit.challenge.model.LoginResponseObject;
import com.midasit.challenge.ui.admin.AdminActivity;
import com.midasit.challenge.ui.member.MemberActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout mTextInputUsername;
    private TextInputLayout mTextInputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        Button loginButton = findViewById(R.id.login_button);
        mTextInputUsername = findViewById(R.id.text_input_username);
        mTextInputPassword = findViewById(R.id.text_input_password);

        loginButton.setOnClickListener(v -> {

            String username = mTextInputUsername.getEditText().getText().toString().trim();
            String password = mTextInputPassword.getEditText().getText().toString().trim();

            LoginRequestObject loginRequsetObject = new LoginRequestObject(username,password);
            //TODO: 서버통신
            Call<LoginResponseObject> signUp = ApplicationController.getInstance().getNetworkService().login(loginRequsetObject);
            signUp.enqueue(new Callback<LoginResponseObject>() {
                @Override
                public void onResponse(Call<LoginResponseObject> call, Response<LoginResponseObject> response) {

                    if (response.isSuccessful()) {
                        LoginResponseObject responseObject = response.body();

                        mTextInputUsername.getEditText().setText("");
                        mTextInputPassword.getEditText().setText("");

                        mTextInputUsername.setError(null);
                        mTextInputPassword.setError(null);

                        String token = responseObject.data.getToken();
                        String authority = responseObject.data.getAdmin();


                        Log.d("AAA", "로그인 성공" + token + authority);

                        SharedPreferences pref = getSharedPreferences("token", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("token", responseObject.data.getToken());
                        editor.putString("authority",responseObject.data.getAdmin() );
                        editor.commit();

                        if (authority.equals("member")) {
                            startActivity(new Intent(getApplicationContext(), MemberActivity.class));
                        } else if (authority.equals("admin")) {
                            startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                        }

                        //TODO:


                        return;



                    } else {

                        int code = response.code();
                        if (code == 401) {
                            Toast.makeText(getApplicationContext(), "비밀번호가 틀렸습니다", Toast.LENGTH_LONG).show();
                            mTextInputUsername.getEditText().setText("");
                            mTextInputPassword.getEditText().setText("");
                            mTextInputPassword.getEditText().clearFocus();
                            Log.d("AAA", "로그인 아이디 비번 틀렸습니다");
                            return;
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseObject> call, Throwable t) {
                    Log.d("AAA", t.getMessage());
                }
            });

        });

    }
}
