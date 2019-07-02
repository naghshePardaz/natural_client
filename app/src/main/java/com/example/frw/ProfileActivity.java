package com.example.frw;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frw.request.ProjectResponse;
import com.example.frw.request.RetrofitClient;
import com.example.frw.request.SharedPref;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ProfileActivity extends AppCompatActivity implements ProjectAdaptor.ItemClickListener {
    private String mToken;
    ProjectAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // read token from SharedPref and execute new Request/Response
        String mToken = SharedPref.getPreferences(getApplicationContext())
                .getString("token",null);
        getProjectList(mToken);


        // data to populate the RecyclerView with
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_projects);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectAdaptor(animalNames, this);
        recyclerView.setAdapter(adapter);

    }


    private void getProjectList(String token) {
        RetrofitClient.createApi().proj(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ProjectResponse pr) {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void decodeProjectList(ProjectResponse pr) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
