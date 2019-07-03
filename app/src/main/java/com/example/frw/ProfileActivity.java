package com.example.frw;

import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
=======
import android.view.View;
import android.widget.Toast;
>>>>>>> d358e88ee8a29c1eab92aff85f8e06db0ecfa142

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

<<<<<<< HEAD
        // Create adapter and passing project data
        Log.e("INSIDE", "ERRRRRRRRRRRRRR" + sLists);
        ProjectAdaptor adapter = new ProjectAdaptor(sLists);

        // Attach adaptor to recycler view to populate data
        rvProjects.setAdapter(adapter);
=======
>>>>>>> d358e88ee8a29c1eab92aff85f8e06db0ecfa142
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

<<<<<<< HEAD
    private void decodeProjectList(ProjectResponse pr) {
        List<ProjectsList> pLists = pr.getProjecList();
        sLists = new ArrayList<>();
        Log.e("INSIDE", "ERRRRRRRRRRRRRR" + sLists);
=======
>>>>>>> d358e88ee8a29c1eab92aff85f8e06db0ecfa142

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getApplicationContext(), adapter.getItem(position) + "salammmmm", Toast.LENGTH_SHORT).show();
    }
}
