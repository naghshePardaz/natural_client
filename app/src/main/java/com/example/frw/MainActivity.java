package com.example.frw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonConfirm;
    private LinearLayout linearLayout;

    private String usernameInput;
    private String passwordInput;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.edit_text_username);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonConfirm = findViewById(R.id.button_confirm);
        linearLayout = findViewById(R.id.linear_login_form);

        editTextUsername.addTextChangedListener(loginTextWatcher);
        editTextPassword.addTextChangedListener(loginTextWatcher);
        buttonConfirm.setOnClickListener(loginOnClickListener);

        boolean isJwtExists = SaveSharedPreferences.isValueExists(getApplicationContext(), "token");
        if (isJwtExists) {
            loginPageHandler();
        } else {
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            usernameInput = editTextUsername.getText().toString().trim();
            passwordInput = editTextPassword.getText().toString().trim();

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

    private void loginPostRequest(JsonObject jsonParam) {
        RetrofitClient.createApi().auth(jsonParam).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            token = responseBody.string();
                            SaveSharedPreferences.createSharedPref(getApplicationContext(), "token", token);
                            loginPageHandler();
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

    private void loginPageHandler() {
        Intent profileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
        profileIntent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(profileIntent);
    }
}
