package com.midasit.challenge.ui.admin.managemember;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.midasit.challenge.model.SignUpRequsetObject;
import com.midasit.challenge.model.SignUpResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout mTextInputName;
    private TextInputLayout mTextInputEmail;
    private TextInputLayout mTextInputUsername;
    private TextInputLayout mTextInputPassword;
    private TextInputLayout mTextInputPasswordConfirm;
    private TextInputLayout mTextInputBirthday;
    private TextInputLayout mTextInputPhone;

    private Button mSignUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mTextInputName = findViewById(R.id.text_input_name);
        mTextInputEmail = findViewById(R.id.text_input_email);
        mTextInputUsername = findViewById(R.id.text_input_username);
        mTextInputPassword = findViewById(R.id.text_input_password);
        mTextInputPasswordConfirm = findViewById(R.id.text_input_password_confirm);
        mTextInputPhone = findViewById(R.id.text_input_phone);
        mSignUpButton = findViewById(R.id.sign_up_button);
        mTextInputBirthday = findViewById(R.id.text_input_birthday);
        mTextInputPhone = findViewById(R.id.text_input_phone);

        TextInputEditText NameEditText = (TextInputEditText) mTextInputName.getEditText();
        TextInputEditText emailEditText = (TextInputEditText) mTextInputEmail.getEditText();
        TextInputEditText usernameEditText = (TextInputEditText) mTextInputUsername.getEditText();
        TextInputEditText passwordEditText = (TextInputEditText) mTextInputPassword.getEditText();
        TextInputEditText passwordConfirmEditText = (TextInputEditText) mTextInputPasswordConfirm.getEditText();
        TextInputEditText phoneEditText = (TextInputEditText)mTextInputPhone.getEditText();

        NameEditText.addTextChangedListener(nameWatcher);
        emailEditText.addTextChangedListener(emailWatcher);
        usernameEditText.addTextChangedListener(usernamewatcher);
        passwordEditText.addTextChangedListener(passwordwatcher);
        passwordConfirmEditText.addTextChangedListener(passwordConfirmWatcher);

        mSignUpButton.setOnClickListener(confirmInputs);
    }

    View.OnClickListener confirmInputs = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!validateEmail() | !validateUsername() | !validatePassword() | !validatePassowrdConfirm()) {
                return;
            }

            String name = mTextInputName.getEditText().getText().toString().trim();
            String email = mTextInputEmail.getEditText().getText().toString().trim();
            String username = mTextInputUsername.getEditText().getText().toString().trim();
            String password = mTextInputPassword.getEditText().getText().toString().trim();

            String birthday = mTextInputBirthday.getEditText().getText().toString().trim();
            String phone = mTextInputPhone.getEditText().getText().toString().trim();

            Log.d("AAA", name + ":" + email + ":" + username + ":" + password + ":" + birthday + ":"+ phone);

            RegisterRequsetObject registerRequsetObject = new RegisterRequsetObject(username,password,email,phone,birthday, name);
            //TODO: 서버통신
            Call<RegisterResponseObject> signUp = ApplicationController.getInstance().getNetworkService().registerUser(registerRequsetObject);
            signUp.enqueue(new Callback<RegisterResponseObject>() {
                @Override
                public void onResponse(Call<RegisterResponseObject> call, Response<RegisterResponseObject> response) {
                    if(response.body().err == 1){
                        Toast.makeText(getApplicationContext(), "아이디가 이미 존재합니다", Toast.LENGTH_LONG).show();
                        mTextInputUsername.getEditText().setText("");
                        mTextInputUsername.getEditText().requestFocus();
                        Log.d("AAA", "에러" +response.body().toString());
                        return;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "회원가입 되셨습니다", Toast.LENGTH_LONG).show();
                        mTextInputName.getEditText().setText("");
                        mTextInputEmail.getEditText().setText("");
                        mTextInputUsername.getEditText().setText("");
                        mTextInputPassword.getEditText().setText("");
                        mTextInputPasswordConfirm.getEditText().setText("");

                        mTextInputName.setError(null);
                        mTextInputEmail.setError(null);
                        mTextInputUsername.setError(null);
                        mTextInputPassword.setError(null);
                        mTextInputPasswordConfirm.setError(null);

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


    private boolean validateName() {
        String nameInput = mTextInputName.getEditText().getText().toString().trim();

        if (nameInput.isEmpty()) {
            mTextInputName.setError("이름을 입력해주세요");
            return false;
        } else {
            mTextInputName.setError(null);
            return true;
        }
    }

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


    private boolean validateUsername() {
        String usernameInput = mTextInputUsername.getEditText().getText().toString().trim();

        if(usernameInput.isEmpty()) {
            mTextInputUsername.setError("아이디를 입력해주세요");
            return false;
        } else if (usernameInput.length() > 12) {
            mTextInputUsername.setError("아이디는 12자 이내로 해주세요");
            return false;
        } else if (!usernameInput.matches("^[0-9a-z]+$")) {
            mTextInputUsername.setError("알파벳 소문자와 숫자의 조합으로 해주세요");
            return false;
        } else if (!usernameInput.matches("^(([0-9]+[a-z]+)|([a-z]+[0-9]+))[0-9a-z]*$")) {
            mTextInputUsername.setError("알파벳 소문자와 숫자가 한 개 이상씩 있어야 합니다");
            return false;
        } else if (usernameInput.length() < 5) {
            mTextInputUsername.setError("아이디는 5자 이상으로 해주세요");
            return false;
        } else {
            mTextInputUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String Passwordinput = mTextInputPassword.getEditText().getText().toString().trim();

        if(Passwordinput.isEmpty()) {
            mTextInputPassword.setError("비밀번호를 입력해주세요");
            return false;
        } else if (Passwordinput.length() > 18) {
            mTextInputPassword.setError("비밀번호는 12자 이내로 해주세요");
            return false;
        } else if (Passwordinput.length() < 6) {
            mTextInputPassword.setError("비밀번호는 6자 이상으로 해주세요");
            return false;
        } else {
            mTextInputPassword.setError(null);
            return true;
        }
    }

    private boolean validatePassowrdConfirm() {
        String Passwordinput = mTextInputPassword.getEditText().getText().toString().trim();
        String PasswordConfirminput = mTextInputPasswordConfirm.getEditText().getText().toString().trim();

        if (PasswordConfirminput.isEmpty()) {
            mTextInputPasswordConfirm.setError("비밀번호를 한번 더 입력해주세요");
            return false;
        } else if (Passwordinput.isEmpty()){
            mTextInputPasswordConfirm.setError("비밀번호를 먼저 입력해주세요");
            return false;
        } else if(!Passwordinput.equals(PasswordConfirminput)) {
            mTextInputPasswordConfirm.setError("일치하지 않습니다");
            return false;
        } else {
            mTextInputPasswordConfirm.setError(null);
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
            validateName();
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
            validateUsername();
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
            validatePassword();
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
            validatePassowrdConfirm();
        }
    };

}
