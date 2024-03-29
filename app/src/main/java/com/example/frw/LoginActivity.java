package com.example.frw;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frw.request.RetrofitClient;
import com.example.frw.request.SharedPref;
import com.google.gson.JsonObject;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonConfirm;

    private CheckBox cbShowPass;

    private String usernameInput;
    private String passwordInput;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.edit_text_username);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonConfirm = findViewById(R.id.button_confirm);
        cbShowPass = findViewById(R.id.cbShowPass);

        editTextUsername.addTextChangedListener(loginTextWatcher);
        editTextPassword.addTextChangedListener(loginTextWatcher);
        buttonConfirm.setOnClickListener(loginOnClickListener);
        cbShowPass.setOnCheckedChangeListener(showPassOnCheckedChangeListener);

    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            usernameInput = editTextUsername.getText().toString().trim();
            passwordInput = editTextPassword.getText().toString().trim();
            cbShowPass.setVisibility(passwordInput.length() > 0 ? View.VISIBLE : View.INVISIBLE);
            buttonConfirm.setEnabled(usernameInput.length() >= 5 &&
                    passwordInput.matches("^[A-Za-z0-9!@#$%&*?]{8,}$"));
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private View.OnClickListener loginOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Make Collection using JSONObject
            final JsonObject jsonParam = new JsonObject();
            jsonParam.addProperty("username", usernameInput);
            jsonParam.addProperty("password", passwordInput);

            loginPostRequest(jsonParam);
        }
    };

    private CompoundButton.OnCheckedChangeListener showPassOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        int start, end;

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isHidden) {
            if (isHidden) {
                start = editTextPassword.getSelectionStart();
                end = editTextPassword.getSelectionEnd();
                editTextPassword.setTransformationMethod(new PasswordTransformationMethod());
                editTextPassword.setSelection(start, end);
            } else {
                start = editTextPassword.getSelectionStart();
                end = editTextPassword.getSelectionEnd();
                editTextPassword.setTransformationMethod(null);
                editTextPassword.setSelection(start, end);
            }
        }
    };

    private void loginPostRequest(JsonObject jsonParam) {
        RetrofitClient.createApi().auth(jsonParam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            token = responseBody.string();
                            SharedPref.createSharedPref(
                                    getApplicationContext(), "token", token);
                            goProfileActivity();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    private void goProfileActivity() {
        Intent mIntent = new Intent(this, ProfileActivity.class);
        //mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
        //      Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mIntent);
    }
}