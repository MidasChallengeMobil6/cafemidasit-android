package com.midasit.challenge.ui.admin.managemember;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.midasit.challenge.R;
import com.midasit.challenge.application.ApplicationController;
import com.midasit.challenge.model.RegisterRequsetObject;
import com.midasit.challenge.model.RegisterResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterModificationActivity extends AppCompatActivity {

    public final static String EMAIL = "email";
    public final static String PHONE = "phone";

    private TextInputLayout mTextInputEmail;
    private TextInputLayout mTextInputPhone;

    private Button mSignUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_modification);

        mTextInputEmail = findViewById(R.id.text_input_email);
        mTextInputPhone = findViewById(R.id.text_input_phone);
        mSignUpButton = findViewById(R.id.sign_up_button);
        mTextInputPhone = findViewById(R.id.text_input_phone);

        TextInputEditText emailEditText = (TextInputEditText) mTextInputEmail.getEditText();
        TextInputEditText phoneEditText = (TextInputEditText)mTextInputPhone.getEditText();

        emailEditText.addTextChangedListener(emailWatcher);

        mSignUpButton.setOnClickListener(confirmInputs);

        // intent 받기
        Intent intent = getIntent();
        phoneEditText.setText(intent.getStringExtra(PHONE));
    }

    View.OnClickListener confirmInputs = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!validateEmail()) {
                return;
            }

            String email = mTextInputEmail.getEditText().getText().toString().trim();

            String phone = mTextInputPhone.getEditText().getText().toString().trim();

            //Log.d("AAA", name + ":" + email + ":" + username + ":" + password + ":" + birthday + ":"+ phone);

            RegisterRequsetObject registerRequsetObject = new RegisterRequsetObject(email,phone);
            //TODO: 서버통신
            Call<RegisterResponseObject> signUp = ApplicationController.getInstance().getNetworkService().registerUser(registerRequsetObject);
            signUp.enqueue(new Callback<RegisterResponseObject>() {
                @Override
                public void onResponse(Call<RegisterResponseObject> call, Response<RegisterResponseObject> response) {
                    if(response.body().err == 1){
                        Toast.makeText(getApplicationContext(), "아이디가 이미 존재합니다", Toast.LENGTH_LONG).show();
                        Log.d("AAA", "에러" +response.body().toString());
                        return;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "회원가입 되셨습니다", Toast.LENGTH_LONG).show();
                        mTextInputEmail.getEditText().setText("");

                        mTextInputEmail.setError(null);

                        Log.d("AAA", "에러" +response.body().toString());
                        finish();

                        return;
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponseObject> call, Throwable t) {
                    Log.d("AAA", t.getMessage());
                }
            });


        }

    };

    private boolean validateEmail() {
        String emailinput = mTextInputEmail.getEditText().getText().toString().trim();

        if (emailinput.isEmpty()) {
            mTextInputEmail.setError("이메일을 입력해주세요");
            return false;
        } else if(!emailinput.matches("^[_0-9a-zA-Z]+@[0-9a-zA-Z]+\\.[_0-9a-zA-Z-]+$")) {
            mTextInputEmail.setError("형식에 맞지 않는 이메일입니다");
            return false;
        } else {
            mTextInputEmail.setError(null);
            return true;
        }
    }


    TextWatcher nameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            validateEmail();
        }
    };

    TextWatcher usernamewatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };


    TextWatcher passwordwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };


    TextWatcher passwordConfirmWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

}
