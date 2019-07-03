package com.example.frw;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frw.request.ProjectResponse;
import com.example.frw.request.ProjectsList;
import com.example.frw.request.RetrofitClient;
import com.example.frw.request.SharedPref;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ProfileActivity extends AppCompatActivity implements ProjectAdaptor.ItemClickListener {
    private RecyclerView rvProjects;
    ProjectAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Lookup recycler view in activity layout
        rvProjects = findViewById(R.id.rvProjects);

        String mToken = SharedPref.getPreferences(getApplicationContext())
                .getString("token", null);
        getProjectList(mToken, this);

        // set layout manager to position item
        rvProjects.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    private void getProjectList(String token, final ProjectAdaptor.ItemClickListener t) {
        RetrofitClient.createApi().proj(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ProjectResponse pr) {
                        adapter = new ProjectAdaptor(pr);
                        adapter.setClickListener(t);
                        rvProjects.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getApplicationContext(), adapter.getItem(position) + "salammmmm", Toast.LENGTH_SHORT).show();
    }
}
