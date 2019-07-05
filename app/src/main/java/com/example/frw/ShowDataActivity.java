package com.example.frw;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frw.request.SendData;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        Intent intent = getIntent();
        ArrayList<SendData> projectData = (ArrayList<SendData>) intent.getSerializableExtra("data");

        RecyclerView rvProjectData = findViewById(R.id.rvProjectData);
        rvProjectData.setAdapter(new DataAdapter(projectData));
        rvProjectData.setLayoutManager(new LinearLayoutManager(this));

    }
}
