package com.example.frw;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frw.request.ProjectResponse;
import com.example.frw.request.RetrofitClient;
import com.example.frw.request.SharedPref;

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

        // Read token from SharedPref and execute new Request/Response
        String mToken = SharedPref.getPreferences(getApplicationContext())
                .getString("token", null);

        getProjectList(mToken, this);
        // set layout manager to position item
        rvProjects.setAdapter(new SimpleAdapter());
        rvProjects.setLayoutManager(new LinearLayoutManager(this));
    }


    private void getProjectList(String token, final ProfileActivity profileActivity) {
        RetrofitClient.createApi().getProjects(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ProjectResponse pr) {
                        adapter = new ProjectAdaptor(pr);
                        adapter.setClickListener(profileActivity);
                        rvProjects.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
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
        Intent intent = new Intent(this, ShowDataActivity.class);
        intent.putExtra("data", adapter.getProjectData(position));
        startActivity(intent);
        Toast.makeText(getApplicationContext(), adapter.getProjectData(position).get(1).getDataID(), Toast.LENGTH_LONG).show();
    }
}
