package com.example.frw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        Intent intent = getIntent();
        String projectId = intent.getStringExtra("projectId");
        Toast.makeText(this, projectId, Toast.LENGTH_SHORT).show();

        /*
        *
        * https://natural.liara.run/api/file/upload/:pId
        *
        * @Header JWT
        *
        * @Part file: image
        *
        * */

    }
}
